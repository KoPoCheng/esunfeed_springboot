package com.project.esunfeed_back.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.esunfeed_back.Dto.LoginDTO;
import com.project.esunfeed_back.Dto.RegistrationDTO;
import com.project.esunfeed_back.Dto.UserResponseWebDTO;
import com.project.esunfeed_back.Entity.User;
import com.project.esunfeed_back.Repo.UserRepo;
import com.project.esunfeed_back.Response.LoginResponse;
import com.project.esunfeed_back.Service.UserService;
import com.project.esunfeed_back.Util.JwtService;

import jakarta.transaction.Transactional;

@Service
public class UserImpl implements UserService{
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

     @Transactional
    public void registerUser(RegistrationDTO registrationDTO) throws Exception {
        // 檢查電子郵件是否已存在
        if (userRepo.existsByEmail(registrationDTO.getUserEmail())) {
            throw new Exception("電子郵件已被註冊");
        }

        // 創建使用者實體
        User user = new User();
        user.setUserName(registrationDTO.getUserName());
        user.setUserEmail(registrationDTO.getUserEmail());
        user.setUserPassword(passwordEncoder.encode(registrationDTO.getUserPassword()));
        user.setUserRole("USER"); // 根據需要設置角色
        
        // 保存使用者
        userRepo.save(user);
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        Optional<User> optionalUser = userRepo.findByEmail(loginDTO.getUserEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            String password = loginDTO.getUserPassword();
            String encodedPassword = user.getUserPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("role", user.getUserRole());

                String token = jwtService.generateToken(claims, user);

                UserResponseWebDTO userResponseWebDTO = new UserResponseWebDTO(
                        null,
                        user.getUserName(),
                        user.getUserEmail(),
                        user.getUserProfilepic(),
                        user.getUserBio(),
                        user.getUserRole());

                return new LoginResponse("登入成功", true, userResponseWebDTO, token);
            } else {
                return new LoginResponse("密碼錯誤", false);
            }
        } else {
            return new LoginResponse("查無此電子郵件", false);
        }
    }

    @Override
    public List<User> getUsersByIds(List<Long> userid) {
        return userRepo.findByUseridIn(userid);
    }
}
