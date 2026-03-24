package ru.golyakovV.hhParser;

import com.google.gson.Gson;
import ru.golyakovV.hhParser.model.VacanciesList;
import ru.golyakovV.hhParser.model.Vacancy;

import java.util.List;

public class VacancyBuilder {

    public static List<Vacancy> getVacanciesList(String str){
        Gson gson = new Gson();
        VacanciesList vacanciesList = gson.fromJson(str, VacanciesList.class);
        List<Vacancy> vacancies = vacanciesList.getVacancies();
        return vacancies;
    }
}
