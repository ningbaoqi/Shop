package com.shop.ningbaoqi.ningbaoqi_shop.icon;

import com.joanzapata.iconify.Icon;

public enum Icons implements Icon {
    icon_scan('\ue606'),
    icon_ali_pay('\ue606');

    private char character;

    Icons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace("_", "-");
    }

    @Override
    public char character() {
        return character;
    }
}
