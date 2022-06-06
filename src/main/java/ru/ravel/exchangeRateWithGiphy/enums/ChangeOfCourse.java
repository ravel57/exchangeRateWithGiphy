package ru.ravel.exchangeRateWithGiphy.enums;

public enum ChangeOfCourse {
    higher ("rich"),
    lower("broke"),
    noChange("same"),
    error("error");

    private String tag;

    ChangeOfCourse(String code){
        this.tag = code;
    }

    public String ChangeOfCourse(){
        return tag;
    }
}
