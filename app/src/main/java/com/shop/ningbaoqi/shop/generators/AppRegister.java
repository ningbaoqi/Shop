package com.shop.ningbaoqi.shop.generators;

import com.shop.ningbaoqi.ningbaoqi_annotations.annotations.AppRegisterGenerator;
import com.shop.ningbaoqi.ningbaoqi_core.wechat.template.AppRegisterTemplate;

@AppRegisterGenerator(
        packageName = "com.shop.ningbaoqi.shop",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
