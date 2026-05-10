package ru.golyakovV.hhParser.model;

public class HHSalary {
    private Integer from;
    private Integer to;

    public int getFrom() {
        if (from == null) {
            return 0;
        } else {
            return from;
        }
    }
    public int getTo() {
        if (to == null) {
            return 0;
        } else {
            return to;
        }
    }

}
