package com.apitest.runners;

import com.apitest.utils.HelperClass;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class GetTest {
    Dotenv dotenv = Dotenv.load();

    @BeforeEach
    public void beforeEach() {
        RestAssured.reset();
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        
        // compensate for "haryapatih" title update before
        HelperClass.revertRachmatTitleToDr();
    }

    @Test
    @DisplayName("Operasi get data user menggunakan id user yang tidak sesuai format")
    void get_user_with_invalid_user_id_format() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .get("/user/1234567")
        .then()
            .assertThat()
            .statusCode(400) // Status: 400 Bad Request
            .body("error", Matchers.equalTo("PARAMS_NOT_VALID"));
    }

    @Test
    @DisplayName("Operasi get data user dengan tanpa menggunakan header request app-id")
    void get_one_user_without_app_id_headers() {
        given()
            .get("/user/66370b6310f9d4fd57438990")
        .then()
            .assertThat()
            .statusCode(403) // Status: 403 Forbidden
            .body("error", Matchers.equalTo("APP_ID_MISSING"));
    }

    @Test
    @DisplayName("Operasi get data user dengan menggunakan header request app-id yang tidak sesuai format")
    void get_one_user_with_not_exist_app_id_headers() {
        given()
            .header("app-id", "123456")
            .get("/user/66370b6310f9d4fd57438990")
        .then()
            .assertThat()
            .statusCode(403) // Status: 403 Forbidden
            .body("error", Matchers.equalTo("APP_ID_NOT_EXIST"));
    }

    @Test
    @DisplayName("Operasi get data user menggunakan id user yang tidak terdaftar dalam sistem")
    void get_data_from_not_exist_user_id() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .get("/user/06370b6310f9d4fd57438990")
        .then()
            .assertThat()
            .statusCode(404) // Status: 404 Not Found
            .body("error", Matchers.equalTo("RESOURCE_NOT_FOUND"));
    }

    @Test
    @DisplayName("Operasi get data user menggunakan id user yang valid dan terdaftar dalam sistem")
    void get_valid_user_with_existing_user_id() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .get("/user/66370b6310f9d4fd57438990")
        .then()
            .assertThat()
            .statusCode(200) // Status: 200 OK
            .body(matchesJsonSchemaInClasspath("com/apitest/user-schema.json"))
            .body("id", Matchers.equalTo("66370b6310f9d4fd57438990"))
            .body("registerDate", Matchers.equalTo("2024-05-05T04:30:27.418Z"));
    }

}
