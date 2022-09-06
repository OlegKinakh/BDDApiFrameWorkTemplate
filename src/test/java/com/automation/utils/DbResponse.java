package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DbResponse{

    public static Response postResponse(String jsonBody, String endPoint) {

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .request()
                .headers("Authorization", ConfigReader.getProperty("gr.apiToken"))
                .when()
                .accept(ContentType.JSON)
                .body(jsonBody)
                .post(endPoint);
    }
    public static Response putResponse(String jsonBody, String endPoint){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .request()
                .headers("Authorization", ConfigReader.getProperty("gr.apiToken"))
                .when()
                .accept(ContentType.JSON)
                .body(jsonBody)
                .put(endPoint);
    }
    public static Response getResponse(String id){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization",ConfigReader.getProperty("gr.apiToken"))
                .accept(ContentType.JSON)
                .when()
                .pathParam("userId", id)
                .get("/users/{userId}");
    }
    public static Response deleteResponse(String id){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization",ConfigReader.getProperty("gr.apiToken"))
                .accept(ContentType.JSON)
                .when()
                .pathParam("userId", id)
                .delete("/users/{userId}");
    }

    public static Response patchResponse(){
        return null;
    }











}
