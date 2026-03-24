package ru.golyakovV.hhParser.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VacanciesList {
    @SerializedName("items")
    private List<Vacancy> vacancies;

    public List<Vacancy> getVacancies(){
        return vacancies;
    }
}
