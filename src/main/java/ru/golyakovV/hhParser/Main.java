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
            System.out.println(v.getSalary());
            System.out.println(v.getAlternateUrl());
            System.out.println("---");
        }

        //test



        //Добавить фильтрацию по зарплате (не выводить вакансии с неуказанной зарплатой)
        //Либо убрать из курсового 6 пункт в 3.1
    }
}
