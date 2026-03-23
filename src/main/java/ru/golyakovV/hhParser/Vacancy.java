package ru.golyakovV.hhParser;

import com.google.gson.annotations.SerializedName;

public class Vacancy {
    private String name; //Название вакансии

    @SerializedName("alternate_url")
    private String alternateUrl;   //Ссылка

    public String getName(){
        return name;
    }
    public String getAlternateUrl(){
        return alternateUrl;
    }
}
