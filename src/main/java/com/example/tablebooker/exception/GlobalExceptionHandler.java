package com.example.tablebooker.exception;

import com.example.tablebooker.user.exception.InvalidUserRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserRegisterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidUserRegisterException(InvalidUserRegisterException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
