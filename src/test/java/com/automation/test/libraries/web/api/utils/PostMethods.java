package com.automation.test.libraries.web.api.utils;

import com.automation.test.libraries.web.api.models.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PostMethods {
    public Post getPostObjectByID(String postObjectID) {
        String baseURL = "https://jsonplaceholder.typicode.com/posts/";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Response response = given().relaxedHTTPSValidation().auth().none().contentType(JSON)
                .when().log().headers().get(baseURL + postObjectID);

        JsonObject jsonObj = new JsonParser().parse(response.body().prettyPrint()).getAsJsonObject();

        return gson.fromJson(jsonObj, Post.class);
    }

    public Post createPostObject(Post postObj) {
        String baseURL = "https://jsonplaceholder.typicode.com/posts";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Response response = given().relaxedHTTPSValidation().auth().none().contentType(JSON)
                .body(gson.toJson(postObj))
                .when().log().headers().post(baseURL);

        JsonObject jsonObj = new JsonParser().parse(response.body().prettyPrint()).getAsJsonObject();

        return gson.fromJson(jsonObj, Post.class);
    }
}
