package ru.golyakovV.hhParser.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {
    @SerializedName("objects")
    private List<Vacancy> vacancies;
    @SerializedName("total")
    private int count;

    public List<Vacancy> getVacancies(){
        return vacancies;
    }
}
