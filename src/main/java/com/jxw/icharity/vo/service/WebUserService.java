package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.User;
import com.jxw.icharity.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public class WebUserService {

    public UserVo convertUser2UserVo(User user){
        return UserVo.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .build();
    }

}
