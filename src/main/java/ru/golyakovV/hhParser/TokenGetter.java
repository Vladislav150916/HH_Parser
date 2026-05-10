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
        //В целях безопасности два следующих поля имеют пустое значение, чтобы не произошло случайного переполучения токена
        final String clientID = "";
        final String clientSecret = "";

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
        System.out.println("Токен сохранён в файл: " + filename);
    }
}