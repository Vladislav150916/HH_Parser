package ru.golyakovV.hhParser.model;

import com.google.gson.annotations.SerializedName;

public class SJVacancy extends VacancyAbstract{
    @SerializedName("profession")
    private String name;
    @SerializedName("link")
    private String vacancyUrl;
    @SerializedName("firm_name")
    private String employer;
    @SerializedName("payment_from")
    private int salaryFrom;
    @SerializedName("payment_to")
    private int salaryTo;

    public String getName(){
        return name;
    }
    public String getVacancyUrl(){
        return vacancyUrl;
    }
    public String getEmployer() {
        return employer;
    }
    public int getSalaryFrom(){
        return salaryFrom;
    }
    public int getSalaryTo(){
        return salaryTo;
    }
    public String getSalary() {
        String salary = "Зарплата ";
        if (getSalaryFrom() == getSalaryTo()) {
            if (getSalaryFrom() == 0) {
                salary += "не указана";
            } else {
                salary += getSalaryFrom() + " руб";
            }
            return salary;
        }
        if (getSalaryFrom() != 0) {
            salary += "от " + getSalaryFrom() + " ";
        }
        if (getSalaryTo() != 0) {
            salary += "до " + getSalaryTo() + " ";
        }
        salary += "руб";
        return salary;
    }
}
