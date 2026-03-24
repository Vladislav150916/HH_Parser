package ru.golyakovV.hhParser;

import com.google.gson.Gson;

import java.util.List;

public class VacancyBuilder {

    public static List<Vacancy> getVacanciesList(String str){
        Gson gson = new Gson();
        VacanciesList vacanciesList = gson.fromJson(str, VacanciesList.class);
        List<Vacancy> vacancies = vacanciesList.getVacancies();
        return vacancies;
    }
}
