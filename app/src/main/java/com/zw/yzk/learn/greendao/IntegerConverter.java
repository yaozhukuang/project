package com.zw.yzk.learn.greendao;

import android.text.TextUtils;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 将整数数组转化为String，用于数据库存储
 */
public class IntegerConverter implements PropertyConverter<List<Integer>, String>{

    private static final String SPLIT = ",";
    @Override
    public List<Integer> convertToEntityProperty(String databaseValue) {
        List<Integer> list = new ArrayList<>();
        if(TextUtils.isEmpty(databaseValue)) {
            return list;
        }
        String[] tmp = databaseValue.split(SPLIT);
        for (String s: tmp) {
            try {
                list.add(Integer.parseInt(s));
            }catch (NumberFormatException e) {
                list.add(0);
            }
        }
        return list;
    }

    @Override
    public String convertToDatabaseValue(List<Integer> entityProperty) {
        if(entityProperty == null || entityProperty.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Integer integer: entityProperty) {
            builder.append(SPLIT).append(String.valueOf(integer));
        }
        return builder.toString().substring(1);
    }
}
