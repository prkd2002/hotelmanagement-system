package com.prkd.HotelServer.controller.auth;

import com.prkd.HotelServer.dto.SignUpRequest;
import com.prkd.HotelServer.dto.UserDto;
import com.prkd.HotelServer.service.auth.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authservice;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest){
        try{
            UserDto createdUser = authservice.createUser(signUpRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }catch(EntityExistsException e){
            return new ResponseEntity<>("User alrady exists", HttpStatus.NOT_ACCEPTABLE);
        }catch(Exception e){
            return new ResponseEntity<>("User not created, come again later", HttpStatus.BAD_REQUEST);

        }
    }

}
