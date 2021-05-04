package com.semod.lib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * https://codechacha.com/ko/java-gson/ 참고
 */
public class JsonUtils {

    public static String getJsonString(Object obj){
        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);
        gson = null;
        return jsonString;
    }

    public static String getPrettyJsonString(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(obj);
        gson = null;
        return jsonString;
    }
}
