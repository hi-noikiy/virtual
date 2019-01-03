package com.virtual.coin.schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.virtual.coin.utils.CommonConstant;

@Component
public class OkenSchedule {

    public static Map<String,Object> oken_cache = new HashMap<String,Object>();

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0/2 * * * * *")
    public void timer() {
        System.out.println(new Date());
        String url = getUrl(CommonConstant.BTC_TYPE);
        Object data = restTemplate.getForObject(url, Object.class);
        oken_cache.put(CommonConstant.BTC_TYPE, data);
    }


    private String getUrl(String type) {
        return "https://www.okex.com/v3/c2c/tradingOrders/book?side=all&baseCurrency=" + type
               + "&quoteCurrency=cny&userType=certified&paymentMethod=all";
    }
}
