package ru.golyakovV.hhParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ParametersGetter {

    public static Map<String, String> getParameters(){
        Map<String, String> parameters = new HashMap<>();
        Scanner in = new Scanner(System.in);

        System.out.println("Введите название вакансии:");
        String vacancyName = in.nextLine();
        parameters.put("vacancyName", vacancyName);

        System.out.println("Введите код города:");
        String area = in.nextLine();
        parameters.put("area", area);

        return parameters;
    }
}
