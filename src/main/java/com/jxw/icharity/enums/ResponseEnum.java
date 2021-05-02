package com.jxw.icharity.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    ERROR(-1, "服务端错误"),

    SUCCESS(0, "成功"),

    ACCESS_DENIED(2,"权限验证错误，访问被拒绝"),

    DB_NOT_FOUND(6,"数据没有找到"),

    NEED_LOGIN(10, "用户未登录, 请先登录"),

    USERNAME_OR_PASSWORD_ERROR(11,"用户名或密码错误"),

            ;

    Integer code;

    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
