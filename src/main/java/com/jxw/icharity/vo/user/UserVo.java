package com.jxw.icharity.vo.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVo {

    private String username;

    private String email;

    private String mobile;

    private String name;

}
