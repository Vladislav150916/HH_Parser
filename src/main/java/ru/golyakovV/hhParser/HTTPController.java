package ru.golyakovV.hhParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;


public class HTTPController {
    private String site;
    private String vacancyName;
    private String area;
    private URI uri;
    private HttpClient client = HttpClient.newHttpClient();
    //Для пагинации (не все вакансии влазят на одну страницу)
    private int currentPage = 0;
    private int perPage = 100;

    final private String tokenHH = "APPLL1PEA3S05P522AL7PVATP6H5C8M9COCI9NT0OG5NV5DR305H5T6QCRN3TBT0";
    final private String tokenSJ = "v3.r.139770910.7f045358b1e7983562768b2942ceb52569415be7.cbf0360e8dfcb4e95b0f78908b6df012e6726a46";

    public HTTPController(Map<String, String> parameters){
        if (parameters.get("site").equals("1")) {
            site = "HeadHunter";
        } else {
            site = "SuperJob";
        }
        String rawName = parameters.get("vacancyName");
        vacancyName = URLEncoder.encode(rawName, StandardCharsets.UTF_8); //Иначе если в названии вакансии есть пробел - программа крашнется
        area = parameters.get("area");
    }

    public void createURI(){
        try {
            if (site.equals("HeadHunter")) {
                uri = new URI("https://api.hh.ru/vacancies?text=NAME:" + vacancyName
                        + "&area=" + area
                        + "&page=" + currentPage
                        + "&per_page=" + perPage);
            } else if (site.equals("SuperJob")) {
                uri = new URI("https://api.superjob.ru/2.0/vacancies/?keyword=" + vacancyName
                        + "&town=" + area
                        + "&page=" + currentPage
                        + "&count=" + perPage);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getRawVacancies(){
        createURI();
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
                    .header("X-Api-App-Id", tokenSJ)
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
        }
        return request;
    }

    private HttpResponse<String> sendRequest(HttpRequest request){
        HttpResponse<String> response = null;
        try {
            response = client.send(request, BodyHandlers.ofString());

            //Проверяется статус ответа, если статус не ОК, таймаут и повтор запроса
            if (!checkStatus(response)) {
                System.out.println("Таймаут 3 секунды и повторная отправка запроса");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Ошибка таймаута");
                }
                response = client.send(request, BodyHandlers.ofString());
                if (!checkStatus(response)){
                    throw new IOException();
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Произошла ошибка при попытке отправить запрос к эндпоинту и получить ответ. Проверьте HTTPController. Завершение работы программы.");
            System.exit(0);
        }
        return response;
    }

    private boolean checkStatus(HttpResponse<String> response){
        int statusCode = response.statusCode();
        boolean isStatusCorrect = false;
        switch (statusCode) {
            case 200:
                isStatusCorrect = true;
                break;
            case 400:
                System.out.println("Ошибка в запросе. Проверьте параметры запроса (название вакансии, город). Проверьте адрес эндпоинта в HTTPController");
                break;
            case 403:
                System.out.println(
                        "Ошибка 403. Может не пропускать запросы неавторизованного пользователя, либо отсутствует тег User-Agent, либо превышена частота запросов");
                break;
            case 429:
                System.out.println("Превышена частота запросов");
                break;
            case 500:
            case 503:
                System.out.println("Ошибка на стороне сервера, повторите запрос позже");
                break;
            default:
                System.out.println("Неожиданный код ответа: " + statusCode);
        }
        return isStatusCorrect;
    }

    public void setPage(int page) {
        this.currentPage = page;
    }

    public String getSite() {
        return site;
    }
    public int getPerPage() {
        return perPage;
    }
}
