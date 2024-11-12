package com.project.esunfeed_back.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.esunfeed_back.Entity.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByUserIdAndIsdeletedFalse(Long userId);
}
