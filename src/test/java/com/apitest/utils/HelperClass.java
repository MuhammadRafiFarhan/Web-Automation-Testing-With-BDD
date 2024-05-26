package com.apitest.utils;

import com.apitest.InitiationUserId;
import io.github.cdimascio.dotenv.Dotenv;

import static io.restassured.RestAssured.given;

public final class HelperClass {
    /**
     * Description: Static method for deleting all created users from the create method
     */
    public static void deleteRemainingCreatedUsersFromCreateMethod() {
        int index = 0;
        for (String userId : InitiationUserId.getAllUserID()) {
            if (index > 0) {
                given()
                    .header("app-id", Dotenv.load().get("APP_ID"))
                    .delete("/user/" + userId);
            }
            index++;
        }

        InitiationUserId.clearAllUserID();
    }

    public static void revertRachmatTitleToDr() {
        given()
            .header("app-id", Dotenv.load().get("APP_ID"))
            .header("Content-Type", "application/json")
            .body("{\"title\": \"dr\"}")
            .put("/user/66370b6310f9d4fd57438990");
    }
}
