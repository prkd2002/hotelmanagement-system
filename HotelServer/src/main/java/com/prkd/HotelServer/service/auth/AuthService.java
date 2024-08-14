package com.prkd.HotelServer.service.auth;

import com.prkd.HotelServer.dto.SignUpRequest;
import com.prkd.HotelServer.dto.UserDto;

public interface AuthService {
    void createAdminAccount();
    UserDto createUser(SignUpRequest signUpRequest );
}
