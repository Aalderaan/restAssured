package com.example.restassured.tests.steps;

import com.example.restassured.tests.endpoints.Endpoints;
import com.example.restassured.tests.utils.ApiServices;
import io.restassured.response.Response;

import static com.example.restassured.tests.endpoints.Endpoints.SIGNIN;

public class UsersInfoSteps {
    private final ApiServices apiServices = new ApiServices();

    /**
     * In the 'getUsersId' method we take to String arguments, witch we use for
     * the basic authorization, and we send the request to a specific URL (BASE_URL + Endpoints.USERS),
     * and from the response we extract user's id in the String format
     */

    public String getUsersId(String username, String password) {
        return apiServices.getRequest(username, password, Endpoints.USERS)
                .then().extract().path("user.id");
    }

    /**
     * In the 'getUsersInfo' method we take two String arguments, witch we use for
     * the basic authorization, also pass static endpoint for Users and extracted user's info,
     */

    public Response getUsersInfo(String username, String password, String usersId) {
        return apiServices.getRequest(username, password, Endpoints.USERS + usersId);
    }

    /**
     * In the 'signIn' method we take two String arguments, witch we use for
     * the basic authorization, also pass static endpoint for sigIn and return Response.
     */

    public Response signIn(String username, String password) {
        return apiServices.getRequest(username, password, Endpoints.SIGNIN);
    }
}
