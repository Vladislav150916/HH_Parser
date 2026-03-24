package ru.golyakovV.hhParser;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> parameters = ParametersGetter.getParameters();
        HTTPController controller = new HTTPController(parameters);
        String rawVacancies = controller.getRawVacancies();
        List<Vacancy> vacancies = VacancyBuilder.getVacanciesList(rawVacancies);

        for (Vacancy v : vacancies){
            System.out.println(v.getName());
            System.out.println(v.getAlternateUrl());
        }

    }
}
