package com.automation.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class Serializers <T>{
    public  String serialize(T object){
        //Create json Body from pojo
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = null;
        try {
            jsonBody = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonBody;
    }

    public T deserialize(T object, Response response){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            object = (T)objectMapper.readValue(response.asString(), object.getClass());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return object;
    }



}
