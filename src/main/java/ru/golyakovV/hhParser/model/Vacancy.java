package ru.golyakovV.hhParser.model;

import com.google.gson.annotations.SerializedName;
import ru.golyakovV.hhParser.model.vacancyParameters.Salary;

public class Vacancy {
    private String name; //Название вакансии
    @SerializedName("alternate_url")
    private String alternateUrl;  //Ссылка
    private Salary salary;
    //Добавить название компании-работодателя (employer.name)






    public String getName(){
        return name;
    }
    public String getAlternateUrl(){
        return alternateUrl;
    }
    public String getSalary(){
        if (salary == null){
            return "Зарплата не указана";
        }
        return salary.getSalary();
    }
}
