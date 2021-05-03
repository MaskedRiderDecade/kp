package com.jxw.icharity.controller;

import com.jxw.icharity.domain.User;
import com.jxw.icharity.enums.ResponseEnum;
import com.jxw.icharity.service.UserService;
import com.jxw.icharity.util.JwtUtil;
import com.jxw.icharity.vo.user.LoginUserVo;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.user.TokenVo;
import com.jxw.icharity.vo.user.UserVo;
import com.jxw.icharity.vo.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WebUserService webUserService;

    private JwtUtil jwtUtil=new JwtUtil();

    @PostMapping("/register")
    public void register(@RequestBody User user){
        userService.register(user);
    }

    @GetMapping("/userInfo")
    public ResponseVo<UserVo> getUserInfo(@RequestHeader(name = "Authorization") String token){
        return ResponseVo.success(webUserService.convertUser2UserVo(userService.getUserInfo(jwtUtil.getUserId(token))));
    }

    @GetMapping("/username")
    public ResponseVo<UserVo>getUsername(@RequestHeader(name = "Authorization") String token){
        return ResponseVo.success(UserVo.builder().username(jwtUtil.getUsername(token)).build());
    }

    @PostMapping("/login")
    public ResponseVo login(@RequestBody LoginUserVo loginUserVo, HttpSession session){

        ResponseVo<User> responseVo=userService.login(loginUserVo);

        if(responseVo.getStatus().equals(ResponseEnum.SUCCESS.getCode())){
            String token= jwtUtil.createJwtToken(responseVo.getData());
            return ResponseVo.success(TokenVo.builder().token(token).build());
        }

        return responseVo;
    }

}
