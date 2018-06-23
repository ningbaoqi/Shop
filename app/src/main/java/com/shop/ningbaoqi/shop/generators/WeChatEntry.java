package com.shop.ningbaoqi.shop.generators;

import com.shop.ningbaoqi.ningbaoqi_annotations.annotations.EntryGenerator;
import com.shop.ningbaoqi.ningbaoqi_core.wechat.template.WXEntryTemplate;

@EntryGenerator(
        packageName = "com.shop.ningbaoqi.shop",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
