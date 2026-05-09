package ru.golyakovV.hhParser;

import com.google.gson.Gson;
import ru.golyakovV.hhParser.model.HHJsonResponse;
import ru.golyakovV.hhParser.model.HHVacancy;
import ru.golyakovV.hhParser.model.SJJsonResponse;
import ru.golyakovV.hhParser.model.SJVacancy;

import java.util.List;

public class VacancyBuilder {

    public static List<HHVacancy> getHHVacanciesList(String str){
        Gson gson = new Gson();
        HHJsonResponse jsonResponse = gson.fromJson(str, HHJsonResponse.class);
        List<HHVacancy> vacancies = jsonResponse.getVacancies();
        return vacancies;
    }

    public static List<SJVacancy> getSJVacanciesList(String str){
        Gson gson = new Gson();
        SJJsonResponse jsonResponse = gson.fromJson(str, SJJsonResponse.class);
        List<SJVacancy> vacancies = jsonResponse.getVacancies();
        return vacancies;
    }
}
