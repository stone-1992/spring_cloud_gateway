package com.stone.common.poi;


import model.exption.BusinessException;

import java.util.Collection;

/**
 * @classname Assert
 * @description 断言
 * @date 2020/3/18 9:49
 */
public class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }

    public static void notNull(Object object, String message,RuntimeException e) {
        if (object == null) {
            throw e;
        }
    }

    public static void notBlank(String str, String message) {
        if (str == null || str.length() == 0) {
            throw new BusinessException(message);
        }
    }

    public static void notBlank(String str, String message,RuntimeException e) {
        if (str == null || str.length() == 0) {
            throw e;
        }
    }

    public static void notEmpty(Collection<?> coll, String message) {
        if (coll == null || coll.size() == 0) {
            throw new BusinessException(message);
        }
    }

    public static void notEmpty(Collection<?> coll, String message,RuntimeException e) {
        if (coll == null || coll.size() == 0) {
            throw e;
        }
    }

    public static void isTrue(Boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(message);
        }
    }

    public static void isTrue(Boolean expression, String message,RuntimeException e) {
        if (!expression) {
            throw e;
        }
    }
}
