package com.project.esunfeed_back.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.esunfeed_back.Dto.CommentDTO;
import com.project.esunfeed_back.Service.CommentService;

@RestController
@RequestMapping("/api/v1/user/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        // 簡單的驗證邏輯
        if (commentDTO.getContent() == null || commentDTO.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CommentDTO createdComment = commentService.createMessage(commentDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDTO>> getMessagesByPostId(@PathVariable Long postId) {
        List<CommentDTO> comments = commentService.getMessagesByPostId(postId);
        // 直接返回留言列表，即使是空的
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    
}
