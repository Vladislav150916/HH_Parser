package ru.golyakovV.hhParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

//Класс является вспомогательным для получения токена HeadHunder. Переполучать токен только в случае утраты работоспособности старого токена

public class TokenGetter {
    public static void main(String[] args) throws Exception {
        final String clientID = "TO1M918E77V0QPGUAJO60BMKGRBHM6C291F807S3DK387H7S0NFN39CPS2797NI1";
        final String clientSecret = "LE127BKIJ3C92QUD7ANVCP6MSPSKLQSUVEUN871OMFR1QDS9OO3HBH7ETVO3NJCP";

        String body = "grant_type=client_credentials" +
                "&client_id=" + clientID +
                "&client_secret=" + clientSecret;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.hh.ru/token"))
                .header("User-Agent", "MyCourseWork/1.0 (bgh4seven@yandex.ru")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String res = response.body();
        String filename = "token.json";
        Files.writeString(Paths.get(filename), res);
        System.out.println("Ответ сохранён в файл: " + filename);

    }
}