package ru.golyakovV.hhParser;

import com.google.gson.Gson;
import ru.golyakovV.hhParser.model.HHJsonResponse;
import ru.golyakovV.hhParser.model.HHVacancy;
import ru.golyakovV.hhParser.model.SJJsonResponse;
import ru.golyakovV.hhParser.model.SJVacancy;

import java.util.ArrayList;
import java.util.List;

public class VacancyBuilder {

    public static List<HHVacancy> getHHVacanciesList(HTTPController controller){
        List<HHVacancy> vacancies = new ArrayList<>();
        Gson gson = new Gson();
        int page = 0;
        int totalFound = 0;

        while (true) {
            controller.setPage(page);
            String rawVacancies = controller.getRawVacancies();
            HHJsonResponse response = gson.fromJson(rawVacancies, HHJsonResponse.class);

            if (page == 0) {
                totalFound = response.getCount();
                System.out.println("Всего найдено вакансий: " + totalFound);
                if (totalFound > 2000) {
                    System.out.println("Количество найденных вакансий превышает лимит HeadHunter. Будут обработаны только 2000 вакансий.");
                }
                System.out.println("На странице помещается " + controller.getPerPage() + " вакансий");
            }

            List<HHVacancy> pageVacancies = response.getVacancies();
            vacancies.addAll(pageVacancies);

            System.out.println("Обработана " + (page + 1) + " страница");
            if (vacancies.size() >= totalFound) {
                break;
            } else if (page == 19) {
                System.out.println("Поиск остановлен, HeadHunter выдает не более 20 страниц в рамках запроса. Сформирован неполный список вакансий");
                break;
            }
            page++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Ошибка таймаута");
            }
        }
        return vacancies;
    }

    public static List<SJVacancy> getSJVacanciesList(HTTPController controller){
        List<SJVacancy> vacancies = new ArrayList<>();
        Gson gson = new Gson();
        int page = 0;
        int totalFound = 0;

        while (true) {
            controller.setPage(page);
            String rawVacancies = controller.getRawVacancies();
            SJJsonResponse response = gson.fromJson(rawVacancies, SJJsonResponse.class);

            if (page == 0) {
                totalFound = response.getCount();
                System.out.println("Всего найдено вакансий: " + totalFound + "\nНа странице помещается " + controller.getPerPage() + " вакансий");
            }

            List<SJVacancy> pageVacancies = response.getVacancies();
            vacancies.addAll(pageVacancies);
            System.out.println("Страница " + page + ": загружено " + pageVacancies.size() + " вакансий");

            if (vacancies.size() >= totalFound) {
                break;
            }
            page++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Ошибка таймаута");
            }
        }
        System.out.println("Итого собрано вакансий: " + vacancies.size());
        return vacancies;
    }
}
