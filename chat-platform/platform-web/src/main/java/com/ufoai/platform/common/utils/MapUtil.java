package com.ufoai.platform.common.utils;

import java.util.Map;


public class MapUtil {

	/**
     * 转换空值为0
     * @param
     * @return
     */
    public static String get(Map map, String key){
    	if((null == map) || !map.containsKey(key)){
    		return "";
    	}    	
    	return map.get(key).toString(); 
    }
    
}
