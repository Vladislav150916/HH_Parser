package ru.golyakovV.hhParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Map;

public class HTTPController {
    private URI uri;

    public HTTPController(Map<String, String> parameters){
        String vacancyName = parameters.get("vacancyName");
        String area = parameters.get("area");
        try {
            uri = new URI("https://api.hh.ru/vacancies?text=" + vacancyName + "&area=" + area);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }



    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("User-Agent", "Mozilla/5.0")
            .GET()
            .build();

}
