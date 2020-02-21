package com.offcn.cust.exception;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = {MySQLIntegrityConstraintViolationException.class})
    public Map<String,Object> MySQLIntegrityConstraintViolationExceptionHandler(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("statuCode",599);
        return map;
    }
}
