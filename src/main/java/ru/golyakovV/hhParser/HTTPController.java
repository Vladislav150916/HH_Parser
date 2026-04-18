package ru.golyakovV.hhParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Map;


public class HTTPController {
    private String vacancyName;
    private String area;
    private URI uri;
    private HttpClient client = HttpClient.newHttpClient();


    public HTTPController(Map<String, String> parameters){
        vacancyName = parameters.get("vacancyName");
        area = parameters.get("area");
    }

    public void createURI(){
        try {
            uri = new URI("https://api.hh.ru/vacancies?text=NAME:" + vacancyName + "&area=" + area);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void createURI(Map<String, String> parameters){
        vacancyName = parameters.get("vacancyName");
        area = parameters.get("area");
        createURI();
    }

    public String getRawVacancies(){
        createURI();
        System.out.println("URI = " + uri);
        HttpRequest request = createRequest();
        HttpResponse<String> response = sendRequest(request);
        return response.body();
    }


    private HttpRequest createRequest(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .version(HttpClient.Version.HTTP_2)
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
        return request;
    }

    //Добавить проверку кода ответа, если код ответа плох, вывести сообщение об ошибке
    private HttpResponse<String> sendRequest(HttpRequest request){
        HttpResponse<String> response = null;
        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }





}
