package ru.golyakovV.hhParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ParametersGetter {

    public static Map<String, String> getParameters(){
        Map<String, String> parameters = new HashMap<>();
        Scanner in = new Scanner(System.in);

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
            if (AreaConverter.getSityID(area) != null) {
                if (site.equals("1")) {
                    parameters.put("area", AreaConverter.getSityID(area));
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
