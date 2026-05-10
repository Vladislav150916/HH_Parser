package ru.golyakovV.hhParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AreaConverter {

    public static String getSityID(String area) {
        String cityName = area.trim();
        String foundId = null;
        String areasJson = "";

        try {
            areasJson = new String(Files.readAllBytes(Paths.get("src/main/resources/areas.json")));
        } catch (IOException e) {
            System.out.println("Ошибка чтения справочника: " + e.getMessage());
            return null;
        }

        JsonArray countries = JsonParser.parseString(areasJson).getAsJsonArray();

        //Ищем страну Россия
        for (int i = 0; i < countries.size(); i++) {
            JsonObject country = countries.get(i).getAsJsonObject();
            if (country.get("name").getAsString().equals("Россия")) {
                JsonArray regions = country.getAsJsonArray("areas");

                //Перебор областей
                for (int j = 0; j < regions.size(); j++) {
                    JsonObject region = regions.get(j).getAsJsonObject();

                    //Некоторые города (Москва например) находятся на уровне областей, это проверка для них
                    if (region.get("name").getAsString().equalsIgnoreCase(cityName)) {
                        foundId = region.get("id").getAsString();
                        break;
                    }
                    //Если рассматриваемая область не искомый город - ищем среди закрепленных за ней городов
                    if (region.has("areas") && region.get("areas").isJsonArray()) {
                        JsonArray cities = region.getAsJsonArray("areas");
                        for (int k = 0; k < cities.size(); k++) {
                            JsonObject city = cities.get(k).getAsJsonObject();
                            if (city.get("name").getAsString().equalsIgnoreCase(cityName)) {
                                foundId = city.get("id").getAsString();
                                break;
                            }
                        }
                    }
                    if (foundId != null) break;
                }
                break;
            }
        }

        return foundId;
    }
}