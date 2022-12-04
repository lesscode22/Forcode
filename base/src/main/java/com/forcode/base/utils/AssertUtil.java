package com.forcode.base.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.forcode.base.common.exception.BizException;

import java.util.Collection;

/**
 * @description: 断言
 * 
 * @author: TJ
 * @date:  2022-03-25
 **/
public class AssertUtil {

    public static void isTrue(boolean expression, String errorMsg, Object... params) {
        if (!expression) alert(errorMsg, params);
    }

    public static void isFalse(boolean expression, String errorMsg, Object... params) {
        if (expression) alert(errorMsg, params);
    }

    public static void isNull(Object obj, String errorMsg, Object... params) {
        if (obj != null) alert(errorMsg, params);
    }

    public static void notNull(Object obj, String errorMsg, Object... params) {
        if (obj == null) alert(errorMsg, params);
    }

    public static void isEmpty(String text, String errorMsg, Object... params) {
        if (StrUtil.isNotBlank(text)) alert(errorMsg, params);
    }

    public static void notEmpty(String text, String errorMsg, Object... params) {
        if (StrUtil.isBlank(text)) alert(errorMsg, params);
    }

    // ============================ Collection

    public static void notEmpty(Collection<?> collection, String errorMsg, Object... params) {
        if (CollUtil.isEmpty(collection)) alert(errorMsg, params);
    }

    public static void isEmpty(Collection<?> collection, String errorMsg, Object... params) {
        if (CollUtil.isNotEmpty(collection)) alert(errorMsg, params);
    }

    public static void noNullElements(Collection<?> collection, String errorMsg, Object... params) {
        if (collection == null) return;
        for (Object element : collection) {
            if (element == null) alert(errorMsg, params);
        }
    }

    private static void alert(String errorMsg, Object... params) {
        throw new BizException(StrUtil.format(errorMsg, params));
    }
}
