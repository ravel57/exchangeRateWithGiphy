package ru.ravel.exchangeRateWithGiphy.enums;

import lombok.Getter;

public enum ChangeOfCourse {
    higher ("rich"),
    lower("broke"),
    noChange("same"),
    error("error");

    @Getter
    private  String tag;

    ChangeOfCourse(String code){
        this.tag = code;
    }

}
