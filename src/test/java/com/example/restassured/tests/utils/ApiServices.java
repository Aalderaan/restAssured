package com.example.restassured.tests.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiServices {

    /**
     * Here first we create the constant with base url
     */
   private static final String BASE_URL = "https://ws-test.keepit.com";

    /**
     * In the 'getRequest' method we take to String arguments, witch we use for
     * the basic authorization, and we send the request to a specific URL (BASE_URL + endpoint)
     */
   public Response getRequest(String username, String password, String endpoint) {
        RequestSpecification requestSpecification = RestAssured.given().auth().basic(username, password);

        Response resp = requestSpecification.get(BASE_URL + endpoint);

        return resp;
    }
}
