package ru.golyakovV.hhParser.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SJJsonResponse {
    @SerializedName("objects")
    private List<SJVacancy> vacancies;
    @SerializedName("total")
    private int count;

    public List<SJVacancy> getVacancies(){
        return vacancies;
    }
}
