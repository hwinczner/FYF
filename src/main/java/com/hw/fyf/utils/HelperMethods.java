package com.hw.fyf.utils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class HelperMethods {

    public boolean hasNullFields(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    return true; // field is missing/null
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false; // all fields non-null
    }
}
