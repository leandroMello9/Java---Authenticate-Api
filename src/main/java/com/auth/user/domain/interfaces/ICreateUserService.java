package com.auth.user.domain.interfaces;

import com.auth.user.domain.request.CreateUserRequest;
import com.auth.user.domain.response.CreateUserResponseData;

public interface ICreateUserService {
    CreateUserResponseData createUser(CreateUserRequest request);
}