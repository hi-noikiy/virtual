package com.virtual.coin.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.virtual.coin.utils.CommonConstant;

//@Component
public class OkenSchedule {

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0/10 * * * * *")
    public void timer() {
        System.out.println(new Date());
        String url = getUrl(CommonConstant.BTC_TYPE);
        Object obj = restTemplate.getForObject(url, Object.class);
        System.out.println(obj);
    }


    private String getUrl(String type) {
        return "https://www.okex.com/v3/c2c/tradingOrders/book?side=all&baseCurrency=" + type
               + "&quoteCurrency=cny&userType=certified&paymentMethod=all";
    }
}
