package com.jxw.icharity.controller;

import com.jxw.icharity.domain.User;
import com.jxw.icharity.enums.ResponseEnum;
import com.jxw.icharity.service.UserService;
import com.jxw.icharity.vo.LoginUserVo;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.UserVo;
import com.jxw.icharity.vo.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.jxw.icharity.constants.Constants.USERNAME;

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

    @GetMapping("/userInfo")
    public ResponseVo<UserVo> getUserInfo(Integer id){
        Assert.notNull(id,"userId不能为空");
        return ResponseVo.success(webUserService.convertUser2UserVo(userService.getUserInfo(id)));
    }

    @PostMapping("/login")
    public ResponseVo<String>login(@RequestBody LoginUserVo loginUserVo, HttpSession session){

        ResponseVo<String> responseVo=userService.login(loginUserVo);

        if(responseVo.getStatus().equals(ResponseEnum.SUCCESS.getCode())){
            session.setAttribute(USERNAME,responseVo.getData());
        }

        return responseVo;


    }

}
