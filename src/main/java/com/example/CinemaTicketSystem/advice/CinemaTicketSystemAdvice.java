package com.example.CinemaTicketSystem.advice;

import com.example.CinemaTicketSystem.Exception.SecException;
import com.example.CinemaTicketSystem.Exception.SysException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Created by sniryosefof on 22 פבר׳
@RestControllerAdvice
public class CinemaTicketSystemAdvice {
    @ExceptionHandler(value = {SecException.class})
    public ResponseEntity<?> handleSecException(SecException e) {
        return new ResponseEntity<>(new ErrAdvice("s",e.getSecMsg().getMsg()), e.getSecMsg().getStatus());
    }
    @ExceptionHandler(value = {SysException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrAdvice handlerError(Exception e){
        return new ErrAdvice("Cinema err",e.getMessage());
    }

}
