package ru.golyakovV.hhParser.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HHJsonResponse {
    @SerializedName("items")
    private List<HHVacancy> vacancies;
    @SerializedName("found")
    private int count;

    public List<HHVacancy> getVacancies(){
        return vacancies;
    }
    public int getCount() {
        return count;
    }
}
