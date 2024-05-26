package com.apitest.runners;

import com.apitest.InitiationUserId;
import com.apitest.utils.HelperClass;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

class DeleteTest {
    static Dotenv dotenv = Dotenv.load();
    public static List<String> userIDs;

    @BeforeEach
    public void beforeEach() {
        userIDs = InitiationUserId.getAllUserID();
        RestAssured.reset();
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }

    @Test
    @DisplayName("Operasi penghapusan satu user menggunakan id user yang terdaftar dalam sistem")
    void delete_one_existing_user_id() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .delete("/user/"+userIDs.get(0))
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", Matchers.equalTo(userIDs.get(0)));
    }

    @Test
    @DisplayName("Operasi penghapusan satu user menggunakan id user yang tidak sesuai format")
    void delete_user_with_invalid_user_id_format() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .delete("/user/1234567")
        .then()
            .assertThat()
            .statusCode(400) // Status: 400 Bad Request
            .body("error", Matchers.equalTo("PARAMS_NOT_VALID"));
    }

    @Test
    @DisplayName("Operasi penghapusan satu user dengan tanpa menggunakan header request app-id")
    void delete_one_user_without_app_id_headers() {
        given()
            .delete("/user/"+userIDs.get(1))
        .then()
            .assertThat()
            .statusCode(403) // Status: 403 Forbidden
            .body("error", Matchers.equalTo("APP_ID_MISSING"));
    }

    @Test
    @DisplayName("Operasi penghapusan satu user dengan menggunakan header request app-id yang tidak sesuai format")
    void delete_one_user_with_not_exist_app_id_headers() {
        given()
            .header("app-id", "123456")
            .delete("/user/"+userIDs.get(2))
        .then()
            .assertThat()
            .statusCode(403) // Status: 403 Forbidden
            .body("error", Matchers.equalTo("APP_ID_NOT_EXIST"));
    }

    @Test
    @DisplayName("Operasi penghapusan satu user menggunakan id user yang tidak terdaftar di sistem")
    void delete_invalid_user_id() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .delete("/user/06370b6310f9d4fd57438990")
        .then()
            .assertThat()
            .statusCode(404)
            .body("error", Matchers.equalTo("RESOURCE_NOT_FOUND"));
    }

    /**
     * Description: Static method for deleting all created users from the create method
     */
    @AfterAll
    public static void cleanup() {
        HelperClass.deleteRemainingCreatedUsersFromCreateMethod();
    }
}