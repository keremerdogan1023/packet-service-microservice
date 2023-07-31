package com.kerem.userpackage.api.controllers;

import com.kerem.userpackage.service.abstracts.UserService;
import com.kerem.userpackage.service.dto.request.CreateUserRequest;
import com.kerem.userpackage.service.dto.request.UpdateUserRequest;
import com.kerem.userpackage.service.dto.response.CreateUserResponse;
import com.kerem.userpackage.service.dto.response.GetAllUsersResponse;
import com.kerem.userpackage.service.dto.response.GetUserResponse;
import com.kerem.userpackage.service.dto.response.UpdateUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    @GetMapping
    public List<GetAllUsersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetUserResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse add(@RequestBody CreateUserRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateUserResponse update(@PathVariable int id, @Valid @RequestBody UpdateUserRequest request) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
