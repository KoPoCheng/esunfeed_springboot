package com.project.esunfeed_back.Service;

import java.util.List;

import com.project.esunfeed_back.Dto.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO messageDTO);

    List<CommentDTO> getCommentByPostId(Long postId);

    //Page<MessageDTO> getAllMessages(Pageable pageable);
} 
