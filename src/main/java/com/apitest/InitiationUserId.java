package com.apitest;

import java.util.ArrayList;
import java.util.List;

public final class InitiationUserId {
    private static List<String> userIDs = new ArrayList<>();

    public static void addUserID(String userID) {
        userIDs.add(userID);
    }

    public static List<String> getAllUserID() {
        return userIDs;
    }

    public static void clearAllUserID() {
        userIDs.clear();
    }
}
