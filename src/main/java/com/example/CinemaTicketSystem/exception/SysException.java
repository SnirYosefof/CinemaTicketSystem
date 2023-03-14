package com.example.CinemaTicketSystem.Exception;

import lombok.Data;

//Created by sniryosefof on 19 פבר׳
public class SysException extends Exception{
  private ErrMsg errMsg;

    public SysException(ErrMsg errMsg) {
        super(errMsg.getMsg());
        this.errMsg = errMsg;
    }
}
