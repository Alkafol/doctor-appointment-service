package com.svi.group5.helpers;

import com.svi.group5.json.AsyncRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Requester {
    public static Response getRequest(String address) {
        return given()
                .contentType("application/json; charset=UTF-8")
                .when()
                .get(address)
                .then()
                .extract().response();
    }

    public static Response postRequest(AsyncRequest request, String address) {
        return given()
                .contentType("application/json; charset=UTF-8")
                .body(request)
                .when()
                .post(address)
                .then()
                .extract().response();
    }
}
