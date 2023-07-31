package com.kerem.userpackage.repository;

import com.kerem.userpackage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
