package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Config {


    @BeforeClass(alwaysRun = true)
    public void setup(){
        System.out.println("Izvrsilo se");
        var requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://dummyapi.io/data")
                .setBasePath("/v1/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                //.addHeader("Content-Type", "application/json")
                .addHeader("app-id", "62b8d93dff7d15c903cd1840")
                //.addFilter(new ResponseLoggingFilter())
                .build();

        var responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpec;
    }








}
