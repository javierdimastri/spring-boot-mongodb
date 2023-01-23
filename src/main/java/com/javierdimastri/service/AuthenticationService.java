package com.javierdimastri.service;

import com.javierdimastri.payload.request.SignupRequest;

public interface AuthenticationService {
    void registerUser(SignupRequest signUpRequest);
}
