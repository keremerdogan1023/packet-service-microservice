package com.kerem.userpackage.service.concretes;

import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.userpackage.entities.User;
import com.kerem.userpackage.repository.UserRepository;
import com.kerem.userpackage.service.abstracts.UserService;
import com.kerem.userpackage.service.dto.request.CreateUserRequest;
import com.kerem.userpackage.service.dto.request.UpdateUserRequest;
import com.kerem.userpackage.service.dto.response.CreateUserResponse;
import com.kerem.userpackage.service.dto.response.GetAllUsersResponse;
import com.kerem.userpackage.service.dto.response.GetUserResponse;
import com.kerem.userpackage.service.dto.response.UpdateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllUsersResponse> getAll() {
        var users = repository.findAll();
        var response = users.stream()
                .map(user -> mapper.forResponse().map(user, GetAllUsersResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetUserResponse getById(int id) {
        var user = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(user,GetUserResponse.class);
        return response;
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var user = mapper.forRequest().map(request, User.class);
        user.setId(0);
        var password = hashPassword(request.getPassword());
        user.setPassword(password);
        repository.save(user);
        System.out.println(password);
        var response = mapper.forResponse().map(user, CreateUserResponse.class);
        return response;

    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest request, int id) {
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
    public String  hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(),salt,65536,128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        String  base64pw = new String(hash);
        return base64pw;


    }
}
