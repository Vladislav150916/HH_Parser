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
    private String site;
    private String vacancyName;
    private String area;
    private URI uri;
    private HttpClient client = HttpClient.newHttpClient();
    final private String tokenHH = "APPLL1PEA3S05P522AL7PVATP6H5C8M9COCI9NT0OG5NV5DR305H5T6QCRN3TBT0";

    public HTTPController(Map<String, String> parameters){
        if (parameters.get("site").equals("1")) {
            site = "HeadHunter";
        } else {
            site = "SuperJob";
        }
        vacancyName = parameters.get("vacancyName");
        area = parameters.get("area");
    }

    public void createURI(){
        try {
            if (site.equals("HeadHunter")) {
                uri = new URI("https://api.hh.ru/vacancies?text=NAME:" + vacancyName + "&area=" + area);
            } else if (site.equals("SuperJob")) {
                uri = new URI("https://api.superjob.ru/2.0/vacancies/?keyword=" + vacancyName + "&town=" + area);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getRawVacancies(){
        createURI();
        System.out.println("URI = " + uri);
        HttpRequest request = createRequest();
        HttpResponse<String> response = sendRequest(request);
        return response.body();
    }


    private HttpRequest createRequest(){
        HttpRequest request = null;
        if (site.equals("HeadHunter")) {
            request = HttpRequest.newBuilder()
                    .uri(uri)
                    .version(HttpClient.Version.HTTP_2)
                    .header("User-Agent", "MyCourseWork/1.0 (bgh4seven@yandex.ru")
                    .header("Authorization", "Bearer " + tokenHH)
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
        } else if (site.equals("SuperJob")) {
            request = HttpRequest.newBuilder()
                    .uri(uri)
                    .version(HttpClient.Version.HTTP_2)
                    .header("User-Agent", "Mozilla/5.0")
                    .header("X-Api-App-Id", "v3.r.139770910.7f045358b1e7983562768b2942ceb52569415be7.cbf0360e8dfcb4e95b0f78908b6df012e6726a46")
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
        }
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

    public String getSite() {
        return site;
    }
}
