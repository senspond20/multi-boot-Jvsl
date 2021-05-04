package com.semod.lib.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

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

    public static String test(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try{
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonString;
    }


}
