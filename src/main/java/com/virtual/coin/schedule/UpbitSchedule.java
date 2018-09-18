package com.virtual.coin.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.virtual.coin.domain.CacheData;

@Component
public class UpbitSchedule {

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0/1 * * * * *")
    public void getUpbitBtcTimer() {
        String url = "https://api.upbit.com/v1/ticker?markets=KRW-BTC";
        List<Map<String,Object>> restResult = restTemplate.getForObject(url, List.class);
        CacheData.UpbitCoin = restResult.get(0);
    }
}
