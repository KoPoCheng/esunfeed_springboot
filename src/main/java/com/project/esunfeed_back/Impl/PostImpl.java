package com.project.esunfeed_back.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.esunfeed_back.Dto.PostDTO;
import com.project.esunfeed_back.Entity.Post;
import com.project.esunfeed_back.Repo.PostRepo;
import com.project.esunfeed_back.Repo.UserRepo;
import com.project.esunfeed_back.Service.PostService;

@Service
public class PostImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Post savePost(PostDTO postDTO) {

        boolean isdeleted = false;

        // 在這裡加上8小時
        LocalDateTime adjustedTime = postDTO.getCreatedAt().plusHours(8);

        Post post1 = new Post(
                postDTO.getUserId(),
                postDTO.getContent(),
                postDTO.getImage(),
                adjustedTime,
                isdeleted);

        return postRepo.save(post1);
    }


    @Override
    public List<Post> getPostsForUser(String userid) {
        
        return postRepo.findByUserIdAndIsDeletedFalse(userid);
    }

    @Override
    public void markPostAsDeleted(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setDeleted(true);
        postRepo.save(post);
    }
    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("文章未找到"));

        // 更新文章的內容
        post.setContent(postDTO.getContent());
        post.setImage(postDTO.getImage());

        // 保存更新後的文章
        Post updatedPost = postRepo.save(post);

        // 返回更新後的 PostDTO，包括所有需要的屬性
        return new PostDTO(
                updatedPost.getPostId(),
                updatedPost.getUserId(),
                updatedPost.getContent(),
                updatedPost.getImage(),
                updatedPost.getCreatedAt(),
                updatedPost.isDeleted()
        );

    }
}
