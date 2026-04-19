package ru.golyakovV.hhParser;

import com.google.gson.Gson;
import ru.golyakovV.hhParser.model.JsonResponse;
import ru.golyakovV.hhParser.model.Vacancy;

import java.util.List;

public class VacancyBuilder {

    public static List<Vacancy> getVacanciesList(String str){
        Gson gson = new Gson();
        JsonResponse jsonResponse = gson.fromJson(str, JsonResponse.class);
        List<Vacancy> vacancies = jsonResponse.getVacancies();
        return vacancies;
    }
}
