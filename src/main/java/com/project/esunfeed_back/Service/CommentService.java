package com.project.esunfeed_back.Service;

import java.util.List;

import com.project.esunfeed_back.Dto.CommentDTO;

public interface CommentService {

    CommentDTO createMessage(CommentDTO messageDTO);

    List<CommentDTO> getMessagesByPostId(Long postId);

    //Page<MessageDTO> getAllMessages(Pageable pageable);
} 
