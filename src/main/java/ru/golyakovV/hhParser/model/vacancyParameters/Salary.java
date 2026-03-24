package ru.golyakovV.hhParser.model.vacancyParameters;

public class Salary {
    private int from = 0;
    private int to = 0;
    private String currency = "RUR";

 /* public int getFrom(){
        return from;
    }
    public int getTo(){
        return to;
    }
    public String getCurrency(){
        return currency;
    }*/
    public String getSalary(){
        String salary = "Зарплата";
        if (from != 0){
            salary += (" от " + from);
        }
        if (to != 0){
            salary += (" до " + to);
        }

        switch (currency){
            case "RUR":
                salary += " рублей";
                break;
            case "EUR":
                salary += " евро";
                break;
            case "USD":
                salary += " долларов";

        }
        return salary;
    }
}
