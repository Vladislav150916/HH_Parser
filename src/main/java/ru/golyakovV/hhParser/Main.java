package ru.golyakovV.hhParser;

import ru.golyakovV.hhParser.model.Vacancy;

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
            System.out.println(v.getEmployer());
            System.out.println("Зарплата от " + v.getSalaryFrom() + " до " + v.getSalaryTo());
            System.out.println(v.getVacancyUrl());
            System.out.println("---");
        }

        ExcelWriter ew = new ExcelWriter(parameters);
        ew.write(vacancies);
    }
}
