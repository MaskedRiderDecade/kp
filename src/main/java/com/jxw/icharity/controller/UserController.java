package com.jxw.icharity.controller;

import com.jxw.icharity.domain.User;
import com.jxw.icharity.enums.ResponseEnum;
import com.jxw.icharity.service.UserService;
import com.jxw.icharity.vo.user.LoginUserVo;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.user.UserVo;
import com.jxw.icharity.vo.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.jxw.icharity.constants.Constants.USERNAME;
import static com.jxw.icharity.constants.Constants.USER_ID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WebUserService webUserService;

    @PostMapping("/register")
    public void register(@RequestBody User user){
        userService.register(user);
    }

    @GetMapping("/user")
    public ResponseVo<UserVo> getUserInfo(HttpSession session){
        Assert.notNull(session.getAttribute(USER_ID),"还未登录，请先登录");
        return ResponseVo.success(webUserService.convertUser2UserVo(userService.getUserInfo((Integer) session.getAttribute(USER_ID))));
    }

    @PostMapping("/login")
    public ResponseVo login(@RequestBody LoginUserVo loginUserVo, HttpSession session){

        ResponseVo<User> responseVo=userService.login(loginUserVo);

        if(responseVo.getStatus().equals(ResponseEnum.SUCCESS.getCode())){
            session.setAttribute(USERNAME,responseVo.getData().getUsername());
            session.setAttribute(USER_ID,responseVo.getData().getId());
            return ResponseVo.success();
        }

        return responseVo;
    }

    @PostMapping("/logout")
    public ResponseVo logout( HttpSession session){
        if(session.getAttribute(USERNAME)!=null){
            session.removeAttribute(USERNAME);
        }
        if(session.getAttribute(USER_ID)!=null){
            session.removeAttribute(USER_ID);
        }
        return ResponseVo.success();
    }

}
