package com.example.CinemaTicketSystem.Exception;

import lombok.Data;

//Created by sniryosefof on 21 פבר׳
@Data
public class SecException extends Exception {
        private SecMsg secMsg;
        public SecException(SecMsg secMsg) {
            super(secMsg.getMsg());
            this.secMsg=secMsg;
        }
    }


