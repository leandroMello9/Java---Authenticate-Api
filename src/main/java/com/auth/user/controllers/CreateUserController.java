package com.auth.user.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.user.domain.interfaces.ICreateUserService;
import com.auth.user.domain.request.CreateUserRequest;
import com.auth.user.domain.response.CreateUserResponseData;
import com.auth.user.erros.CreateUserException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class CreateUserController {

    private final ICreateUserService createUserService;
    
    @PostMapping("/create")
    public CreateUserResponseData createUser(@RequestBody CreateUserRequest request) throws CreateUserException {
      return createUserService.createUser(request);
    }

}