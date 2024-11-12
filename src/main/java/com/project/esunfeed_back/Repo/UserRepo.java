package com.project.esunfeed_back.Repo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.esunfeed_back.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByUseridIn(List<Long> userid);
}
