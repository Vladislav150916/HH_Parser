package ru.golyakovV.hhParser.model;

import com.google.gson.annotations.SerializedName;

public class HHVacancy extends VacancyAbstract {
    private String name;
    @SerializedName("alternate_url")
    private String vacancyUrl;
    private HHEmployer employer;
    private HHSalary salary;

    public String getName(){
        return name;
    }
    public String getVacancyUrl(){
        return vacancyUrl;
    }
    public String getEmployer() {
        return employer.getName();
    }
    public int getSalaryFrom(){
        if (salary == null) {
            return 0;
        } else {
            return salary.getFrom();
        }
    }
    public int getSalaryTo(){
        if (salary == null) {
            return 0;
        } else {
            return salary.getTo();
        }
    }
}
