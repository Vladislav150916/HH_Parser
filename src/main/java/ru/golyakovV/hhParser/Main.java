package ru.golyakovV.hhParser;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> parameters = ParametersGetter.getParameters();
        HTTPController controller = new HTTPController(parameters);

    }
}
