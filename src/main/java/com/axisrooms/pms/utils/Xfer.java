package com.axisrooms.pms.utils;

import org.springframework.beans.BeanUtils;

public class Xfer {
    public static <T> T copyProperties(Object source, T destination) {
        BeanUtils.copyProperties(source, destination);
        return destination;
    }
}