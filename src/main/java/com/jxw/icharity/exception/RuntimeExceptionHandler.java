package com.jxw.icharity.exception;

import com.jxw.icharity.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.jxw.icharity.enums.ResponseEnum.DB_NOT_FOUND;
import static com.jxw.icharity.enums.ResponseEnum.ERROR;

@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseVo handle(RuntimeException e) {
        return ResponseVo.error(ERROR, e.getMessage());
    }

    @ExceptionHandler(DBNotFoundException.class)
    @ResponseBody
    public ResponseVo handle(DBNotFoundException e){
        return ResponseVo.error();
    }

}
