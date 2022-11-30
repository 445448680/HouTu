package me.zhyd.houtu.util;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * list操作工具类
 *
 * @author GHS
 * @version 1.0.0
 * @since 1.0.0
 */
public class ListUtil {

    public static boolean isEmpty(List<?> list) {
        return null == list || list.isEmpty();
    }

    public static <T> List<T> deepClone(List<T> original, Class<T> clazz) {
        List<T> res = Lists.newArrayList();
        for (T t : original) {
            res.add(BeanUtil.copyProperties(t, clazz));
        }
        return res;
    }
}
