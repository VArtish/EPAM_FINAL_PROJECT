package com.example.finalwebprojectepam.service;

import java.util.Map;

public interface UserDataResearcher {
    boolean researchLoginData(Map<String, String> userLoginData);

    boolean researchRegisterData(Map<String, String> userRegisterData);

    boolean researchUserProfileData(Map<String, String> userProfileData);

    boolean researchChangePasswordData(Map<String, String> userChangePassowrdData);
}
