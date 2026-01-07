package com.auth.user.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.auth.user.domain.interfaces.ICreateUserService;
import com.auth.user.domain.request.CreateUserRequest;
import com.auth.user.domain.response.CreateUserResponse;
import com.auth.user.domain.response.CreateUserResponseData;
import com.auth.user.entity.UserEntity;
import com.auth.user.erros.CreateUserException;
import com.auth.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class CreateUserService implements ICreateUserService {
    
    private final UserRepository userRepository;

    public CreateUserResponseData createUser(CreateUserRequest request) {

        log.atInfo().addKeyValue("payload", request.toString()).setMessage("Creating a new user ->>").log();

        if (userRepository.findByUserEmail(request.email()).isPresent()) {
            log.atError().addKeyValue("payload", request).setMessage("Error create a new user with sequence parameters->>").log();
            throw new CreateUserException("User email already exists");
        }


        UserEntity saveUserEntity = new UserEntity(
            request.username(),
            request.email(),
            request.password()
        );

        log.atInfo().addKeyValue("payload", saveUserEntity.toString()).setMessage("Creating a new user for save in database!").log();

        UserEntity userAlreadyCreateInDataBase = userRepository.save(saveUserEntity);

        log.atInfo().addKeyValue("payload", userAlreadyCreateInDataBase.toString()).setMessage("User save in database with success!").log();

        CreateUserResponse newUserResponse = new CreateUserResponse(
            userAlreadyCreateInDataBase.getUserName(),
            userAlreadyCreateInDataBase.getUserEmail()
        );


        log.atInfo().addKeyValue("payload", newUserResponse.toString()).setMessage("Return new user for controller!!").log();

        return new CreateUserResponseData(newUserResponse);
    }
}
