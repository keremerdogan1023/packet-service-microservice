package com.kerem.userpackage.service.abstracts;

import com.kerem.userpackage.service.dto.request.CreateUserRequest;
import com.kerem.userpackage.service.dto.request.UpdateUserRequest;
import com.kerem.userpackage.service.dto.response.CreateUserResponse;
import com.kerem.userpackage.service.dto.response.GetAllUsersResponse;
import com.kerem.userpackage.service.dto.response.GetUserResponse;
import com.kerem.userpackage.service.dto.response.UpdateUserResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface UserService {

    List<GetAllUsersResponse> getAll();
    GetUserResponse getById(int id);
    CreateUserResponse add(CreateUserRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException;
    UpdateUserResponse update(UpdateUserRequest request,int id);
    void delete(int id);
}
