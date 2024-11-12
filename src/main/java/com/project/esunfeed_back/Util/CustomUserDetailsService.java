package com.project.esunfeed_back.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.project.esunfeed_back.Entity.User; // 假設 User 是您的用戶實體
import com.project.esunfeed_back.Repo.UserRepo; // 假設 UserRepo 是您的資料庫存取介面

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo; // 用於從資料庫獲取用戶信息


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 從資料庫中查找用戶
        User user = userRepo.findByUserEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // 返回 UserDetails 對象
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserEmail())
                .password(user.getUserPassword()) // 加密後的密碼
                .roles(user.getUserRole()) // 獲取單一角色，這裡可以直接使用
                .build();
    }
}