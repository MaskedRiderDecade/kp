package com.jxw.icharity.service;

import com.jxw.icharity.domain.User;
import com.jxw.icharity.enums.ResponseEnum;
import com.jxw.icharity.repository.UserRepo;
import com.jxw.icharity.vo.LoginUserVo;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void validRegister(User user){
        Assert.isTrue(Strings.isNotEmpty(user.getUsername()),"用户名不能为空");
        Assert.isTrue(Strings.isNotEmpty(user.getPassword()),"密码不能为空");
        Assert.isTrue(Strings.isNotEmpty(user.getName()),"姓名不能为空");
        Assert.isTrue(Strings.isNotEmpty(user.getMobile()),"手机号码不能为空");
        Assert.isTrue(Strings.isNotEmpty(user.getEmail()),"邮箱不能为空");
    }

    public void validLogin(LoginUserVo loginUserVo){
        Assert.notNull(loginUserVo.getUsername(),"用户名不可为空");
        Assert.notNull(loginUserVo.getPassword(),"密码不可为空");
    }

    public void register(User user){
        //验证信息
        validRegister(user);
        Assert.isTrue(userRepo.countUserByUsername(user.getUsername())==0,"用户名已存在");
        //md5摘要算法
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        User saveResult=userRepo.save(user);
        Assert.notNull(saveResult,"保存失败");
    }

    public ResponseVo<String> login(LoginUserVo loginUserVo){
        validLogin(loginUserVo);
        User user=userRepo.getUserByUsername(loginUserVo.getUsername());
        if(user==null){
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        if(!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(loginUserVo.getPassword().getBytes(StandardCharsets.UTF_8)))){
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        return ResponseVo.success(user.getUsername());
    }

    public User getUserInfo(Integer id){
        return userRepo.getById(id);
    }

}
