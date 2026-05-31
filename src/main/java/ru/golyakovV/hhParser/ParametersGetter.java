package ru.golyakovV.hhParser;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ParametersGetter {

    public static Map<String, String> getParameters(){
        Map<String, String> parameters = new HashMap<>();
        //Для избежания проблемы с кодировкой на разных системах нужно использовать два типа сканера:
        Scanner in;
        String runFromBat = System.getenv("runFromBat");
        if ("1".equals(runFromBat)) {
            //Для Windows при запуске bat
            in = new Scanner(System.in, "CP866");
        } else {
            //Для IDE или другой ОС
            in = new Scanner(System.in, StandardCharsets.UTF_8);
        }

        System.out.println("Выберите на каком сайте хотите искать вакансии.\n1 - HeadHunter (рекомендуется)\n2 - SuperJob");
        String site = "";
        boolean isSiteCorrect = false;
        while (!isSiteCorrect) {
            site = in.nextLine();
            if (site.equals("1") || site.equals("2")){
                isSiteCorrect = true;
                parameters.put("site", site);
            } else {
                System.out.println("Введите корректное значение: 1 или 2");
            }
        }


        System.out.println("Введите название вакансии:");
        String vacancyName = in.nextLine();
        parameters.put("vacancyName", vacancyName);

        System.out.println("Введите название города:");
        boolean isTownCorrect = false;
        while (!isTownCorrect) {
            String area = in.nextLine();
            if (AreaConverter.getCityID(area) != null) {
                if (site.equals("1")) {
                    parameters.put("area", AreaConverter.getCityID(area));
                    parameters.put("areaToFileName", area);
                } else {
                    parameters.put("area", area);
                    parameters.put("areaToFileName", area);
                }
                isTownCorrect = true;
            } else {
                System.out.println("Город не найден в справочнике, введите корректное название города:");
            }
        }

        in.close();

        return parameters;
    }
}
