package com.shop.ningbaoqi.shop.generators;

import com.shop.ningbaoqi.ningbaoqi_annotations.annotations.PayEntryGenerator;
import com.shop.ningbaoqi.ningbaoqi_core.wechat.template.WXPayEntryTemplate;

@PayEntryGenerator(
        packageName = "com.shop.ningbaoqi.shop",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
