package com.prkd.HotelServer.service.auth;

import com.prkd.HotelServer.dto.SignUpRequest;
import com.prkd.HotelServer.dto.UserDto;
import com.prkd.HotelServer.entity.User;
import com.prkd.HotelServer.enums.UserRole;
import com.prkd.HotelServer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImplementation implements  AuthService{

    public final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            log.info("Admin account created successfully");


        }else{
            log.info("Admin account already exist");

        }


    }

    public UserDto createUser(SignUpRequest signUpRequest ){
        if(userRepository.findUserByEmail(signUpRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User Already Present With Email " + signUpRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        User createUser = userRepository.save(user);
        return createUser.getUSerDto();

    }
}
