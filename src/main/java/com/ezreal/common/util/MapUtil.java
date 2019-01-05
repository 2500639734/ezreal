package com.ezreal.common.util;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: shenke
 * @date: 2019/1/5 21:05
 * @description: map工具类
 */
public class MapUtil {

    private MapUtil(){

    }

    /**
     * map key-value对调(支持HashMap,LinkedHashMap)
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<V, K> reforeKeyValue(Map<K, V> map){
        if(CollectionUtils.isEmpty(map)){
            return null;
        }

        Map<V, K> resultMap = null;
        if(map instanceof HashMap){
            resultMap = new HashMap<V, K>();
        } else if(map instanceof LinkedHashMap){
            resultMap = new LinkedHashMap<V, K>();
        }

        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<K, V> entry = iterator.next();
            K key = entry.getKey();
            V value = entry.getValue();
            resultMap.put(value, key);
        }
        return resultMap;
    }

}
