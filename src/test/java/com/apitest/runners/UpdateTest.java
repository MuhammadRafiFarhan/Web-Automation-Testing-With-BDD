package com.apitest.runners;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class UpdateTest {
    Dotenv dotenv = Dotenv.load();

    @BeforeEach
    public void beforeEach() {
        RestAssured.reset();
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }

    @Test
    @DisplayName("Operasi tidak menggunakan id user")
    void update_title_without_specifying_user_id() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .header("Content-Type", "application/json")
                .body("{\"title\":\"mr\"}")
                .put("/user/")
        .then()
                .assertThat()
                .statusCode(404)
                .body("error", Matchers.equalTo("PATH_NOT_FOUND"));
    }

    @Test
    @DisplayName("Operasi menggunakan id user yang tidak valid")
    void update_title_with_invalid_user_id() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .header("Content-Type", "application/json")
                .body("{\"title\":\"mr\"}")
                .put("/user/123456")
        .then()
                .assertThat()
                .statusCode(400)
                .body("error", Matchers.equalTo("PARAMS_NOT_VALID"));
    }

    @Test
    @DisplayName("Update title user dengan title di luar enum yang diizinkan, yaitu haryapatih")
        void update_title_with_new_user_title_haryapatih() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .header("Content-Type", "application/json")
                .body("{\"title\":\"haryapatih\"}")
                .put("/user/66370b6310f9d4fd57438990")
        .then()
                .assertThat()
                .statusCode(400)
                .body("error", Matchers.equalTo("BODY_NOT_VALID"));
}

    @Test
    @DisplayName("Update title user dengan title mr")
    void update_title_with_new_user_title_mr() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .header("Content-Type", "application/json")
                .body("{\"title\":\"mr\"}")
                .put("/user/66370b6310f9d4fd57438990")
        .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("com/apitest/user-schema.json"))
                .body("title", Matchers.equalTo("mr"));
    }


    @Test
    @DisplayName("Update title user dengan title kosong")
    void update_title_with_new_user_title_null() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .header("Content-Type", "application/json")
                .body("{\"title\":\"\"}")
                .put("/user/66370b6310f9d4fd57438990")
        .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("com/apitest/user-schema.json"))
                .body("title", Matchers.equalTo(""));
    }
}
