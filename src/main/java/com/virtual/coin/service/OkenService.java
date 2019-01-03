package com.virtual.coin.service;

import static com.virtual.coin.utils.CommonConstant.BTC_TYPE;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.virtual.coin.schedule.OkenSchedule;

@Service
public class OkenService {

    /**
     * 根据nickName获取卖单的publicId
     * @param nickName
     * @return
     */
    public String getPublicId(String nickName) {
        if (null == OkenSchedule.oken_cache.get(BTC_TYPE)) {
            return null;
        }

        if (StringUtils.isEmpty(nickName)) {
            return null;
        }
        JSONObject btcJson = JSON.parseObject((String) OkenSchedule.oken_cache.get(BTC_TYPE));
        JSONArray btcSellJsonArray = btcJson.getJSONObject("data").getJSONArray("sell");
        for (Object btcSell : btcSellJsonArray) {
            JSONObject btcSellJson = JSONObject.parseObject((String) btcSell);
            if (nickName.equals(btcSellJson.get("nickName"))) {
                return (String) btcSellJson.get("publicId");
            }
        }

        return null;
    }
}
