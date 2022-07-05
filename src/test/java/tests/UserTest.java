package tests;

import com.github.javafaker.Faker;
import config.Config;
import endpoints.UserEndpoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.UserCreate;
import pojo.UserLocation;
import pojo.UserResponse;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest extends Config {

    Faker faker;
    SoftAssert softAssert; //testng

//    @BeforeMethod
//    public void setupClass() {
//        faker = new Faker(new Locale("en-US"));
//        softAssert = new SoftAssert();
//    }


    @Test(description = "Send get request to fetch all the users from database.")
    public void getAllUsers() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "0");
        queryParams.put("limit", "30");
        given()
                .queryParams(queryParams)

                .when().get(UserEndpoints.GET_ALL_USERS)

                .then().statusCode(200);
    }


    @Test(description = "Send get request to fetch all the users from database.")
    public void getAllUsersJsonPath() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "0");
        queryParams.put("limit", "30");
        JsonPath jsonPath = given()
                .queryParams(queryParams)

                .when().get(UserEndpoints.GET_ALL_USERS).getBody().jsonPath();
        String name = jsonPath.getString("data[0].firstName");
        //Assert.assertTrue(name.equals("Sara"));
        assertThat(name)
                .withFailMessage("Expected name to be found: %s", name)
                .isEqualTo("Sara");
    }

    @Test()
    public void getUserById() {
        Response response =
                given()
                        .pathParam("id", "62c478a7d2aa4cb10e3fbd2f")
                        .when().get(UserEndpoints.GET_USER_BY_ID);
        UserResponse user = response.getBody().as(UserResponse.class);
        assertThat(user.getFirstName())
                .withFailMessage("Expected name to be found: %s", user.getFirstName())
                .isEqualTo("Joey");
    }

    @Test()
    public void createUser() {

//        UserLocation location = UserLocation.builder()
//                .street(faker.address().streetAddress())
//                .city("Miami")
//                .state("Florida")
//                .country("US")
//                .build();
//
//        UserCreate userCreate = UserCreate.builder()
//                .firstName("John")
//                .lastName("Smith")
//                .email(faker.internet().emailAddress())
//                .gender("male")
//                .phone(faker.phoneNumber().cellPhone())
//                .location(location)
//                .build();

        UserCreate userCreate = UserCreate.createUser();

        UserResponse userResponse = given()
                .body(userCreate)
                .when().post(UserEndpoints.CREATE_USER).getBody().as(UserResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userResponse.getEmail().equals(userCreate.getEmail())).isTrue();
            softly.assertThat(userResponse.getPhone().equals(userCreate.getPhone())).isTrue();
        });

//        softAssert.assertTrue(userResponse.getEmail().equals(userCreate.getEmail()));
//        softAssert.assertTrue(userResponse.getEmail().equals(userCreate.getEmail()));// testng
//        softAssert.assertAll();

    }


    @Test()
    public void deleteUser() {
        UserCreate userCreate = UserCreate.createUser();
        UserResponse userResponse = given()
                .body(userCreate)
                .when().post(UserEndpoints.CREATE_USER).getBody().as(UserResponse.class);
        JsonPath jsonPath = given()
                .pathParam("id", userResponse.getId())
                .when().delete(UserEndpoints.DELETE_USER).getBody().jsonPath();
        String id = jsonPath.getString("id");
        assertThat(userResponse.getId().equals(id)).isTrue();
    }


}
