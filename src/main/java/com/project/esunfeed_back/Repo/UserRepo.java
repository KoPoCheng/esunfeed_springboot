package com.project.esunfeed_back.Repo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.esunfeed_back.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    
    Optional<User> findByUserEmail(String email);

    boolean existsByUserEmail(String email);

    List<User> findByUserIdIn(List<Long> userid);
}
