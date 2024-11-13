package com.project.esunfeed_back.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.esunfeed_back.Dto.PostDTO;
import com.project.esunfeed_back.Entity.Post;
import com.project.esunfeed_back.Service.PostService;

@RestController
@RequestMapping("/api/v1/user")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO) {
        try {
            // 如果有圖片，保存 Base64 編碼的圖片數據
            if (postDTO.getImage() != null && !postDTO.getImage().isEmpty()) {
                
            }

            Post createdPost = postService.savePost(postDTO);
            System.out.println(createdPost + "發文成功");
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            // 返回 400 Bad Request 狀態碼
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getuserpost/{userid}")
    public ResponseEntity<List<Post>> getPostsForUser(
            @PathVariable String userid,
            @RequestParam(defaultValue = "0") int page, // 當前頁數
            @RequestParam(defaultValue = "5") int size) { // 每頁文章數量

        List<Post> posts = postService.getPostsForUser(userid); // 傳遞 page 和 size
        return ResponseEntity.ok(posts); // 返回文章
    }

    // 新增標記刪除的端點
    @DeleteMapping("/post/{id}/delete")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {
            postService.markPostAsDeleted(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/post/{id}/editpost")
    public ResponseEntity<PostDTO> editPost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        try {
            PostDTO updatedPost = postService.updatePost(id, postDTO); // 這裡獲取更新後的 PostDTO

            if (updatedPost != null) {
                return ResponseEntity.ok(updatedPost); // 返回更新後的 PostDTO
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 如果找不到文章
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 返回錯誤信息
        }
    }

}
