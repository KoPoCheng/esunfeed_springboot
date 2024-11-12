package com.project.esunfeed_back.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.esunfeed_back.Dto.LoginDTO;
import com.project.esunfeed_back.Dto.RegistrationDTO;
import com.project.esunfeed_back.Response.LoginResponse;
import com.project.esunfeed_back.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/guest")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping(path="/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) throws Exception {
        System.out.println("received registrationDTO = " + registrationDTO.getUserEmail());

        try {
            userService.registerUser(registrationDTO);
            return ResponseEntity.ok("註冊成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("註冊失敗：" + e.getMessage());
        }
    }

    @PostMapping(path = "/loginaction")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        // @PostMapping(path = "/loginaction")：定義一個 POST 請求的 API，當用戶端發送 "/loginaction" 的
        // POST 請求時，將由此方法來處理。

        // @RequestBody LoginDTO loginDTO：將從請求主體中取得的 JSON 資料轉換為 LoginDTO
        // 物件，這個物件包含了使用者的登入資訊（如 email 和 password）。
        LoginResponse loginResponse = userService.loginUser(loginDTO);

        // userService.loginUser(loginDTO)：呼叫 userService 的 loginUser
        // 方法，傳入使用者的登入資訊（loginDTO），來進行登入驗證。
        // LoginResponse loginResponse：loginUser 方法會返回一個 LoginResponse
        // 物件，這個物件可能包含登入成功的訊息、token 或錯誤訊息。

        return ResponseEntity.ok(loginResponse);
        // ResponseEntity.ok(loginResponse)：將 loginResponse 作為回應的內容，並以 HTTP 200 OK
        // 的狀態碼返回給客戶端，表示請求成功。
    }

    // 檢查Email是否存在
    @PostMapping(path = "/checkemail")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@RequestBody String email) {
        // @PostMapping(path = "/checkemail")：定義一個 POST 請求的 API，當用戶端發送 "/checkemail" 的
        // POST 請求時，將由此方法來處理。

        // @RequestBody String email：從請求主體中取得 email 字串，這是要檢查是否已經存在的 email。
        boolean exists = userService.checkEmailExists(email);

        // userService.checkEmailExists(email)：呼叫 userService 的 checkEmailExists
        // 方法，檢查指定的 email 是否已經存在於資料庫中，返回 true 或 false。
        Map<String, Boolean> response = new HashMap<>();

        // Map<String, Boolean> response：建立一個 Map，將 "exists" 這個 key 對應的值設為上一步的結果 (true 或
        // false)，表示 email 是否存在。
        response.put("exists", exists);

        return ResponseEntity.ok(response);
        // ResponseEntity.ok(response)：將 Map 作為回應的內容，並以 HTTP 200 OK 的狀態碼返回給客戶端，表示請求成功。
    }
}
