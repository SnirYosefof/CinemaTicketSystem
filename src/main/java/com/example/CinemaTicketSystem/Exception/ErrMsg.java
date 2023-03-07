package com.example.CinemaTicketSystem.Exception;

import lombok.Getter;

//Created by sniryosefof on 19 פבר׳
@Getter
public enum ErrMsg {
    ID_DONT_EXIST("the id doesn't exist"),
    NAME_DONT_EXIST("the name doesn't exist"),
    FUTURE_DATE("you must insert future date"),
    MOVIE_ALREADY_EXIST("the movie already exist"),
    CANT_UPDATE_MOVIE_NAME("you can't update the movie name"),
    CANT_UPDATE_MOVIE_DIRECTOR("you can't update the movie director"),
    CLIENT_EMAIL("The client email is exists"),
    YOU_CANT_BUY_PAST_SCREEN (" you cant buy past screen"),
    THE_CARD_IS_OVER("we sorry the card for this screen is over"),
    THE_PLACE_IS_TAKEN("we sorry this seat is taken"),
    MOVIE_RECOMMENDED("this movie already recommended or at the first time you add screen of this movie you don't recommended him ");



    private String msg;

    ErrMsg(String msg) {
        this.msg = msg;
    }
}
