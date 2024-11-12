package com.project.esunfeed_back.Service;

import java.util.List;

import com.project.esunfeed_back.Dto.PostDTO;
import com.project.esunfeed_back.Entity.Post;

public interface PostService {

    Post savePost(PostDTO postDTO);

    List<Post> getPostsForUser(Long userid);

    void markPostAsDeleted(Long id);

    PostDTO updatePost(Long id, PostDTO postDTO);
}
