package tests;

import config.Config;
import endpoints.UserEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserTest extends Config {




    @Test(description = "Send get request to fetch all the users from database.")
    public void getAllUsers(){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "0");
        queryParams.put("limit", "30");
        given()
                .queryParams(queryParams)
                .header("app-id", "62b8d93dff7d15c903cd1840")
                .contentType(ContentType.JSON)

                .when().get("https://dummyapi.io/data/v1/" + UserEndpoints.GET_ALL_USERS)

                .then().statusCode(200);
        System.out.println("Get all users");
    }

    @Test
    public void getUserById(){
        System.out.println("Get user byId method.");
    }

    @Test
    public void createUser(){
        System.out.println("Create user method.");
    }

    @Test
    public void deleteUser(){
        System.out.println("Delete user method.");
    }



}
