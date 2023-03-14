package com.example.CinemaTicketSystem.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//Created by sniryosefof on 21 פבר׳
@Getter
public enum SecMsg {
    EMAIL_EXIST("email already exist",HttpStatus.CONFLICT),
    INVALID_TOKEN("invalid token", HttpStatus.UNAUTHORIZED),
    TYPE_UNCORRECTED("type uncorrected", HttpStatus.CONFLICT),
    EMAIL_OR_PASSWORD_INCORRECT("email or password incorrect", HttpStatus.UNAUTHORIZED);

    private String msg;
    private HttpStatus status;

    SecMsg(String msg, HttpStatus status) {
        this.msg=msg;
        this.status=status;
    }
}
