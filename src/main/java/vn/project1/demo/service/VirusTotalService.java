package vn.project1.demo.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

@Service
public class VirusTotalService {

    private static final String API_URL = "https://www.virustotal.com/api/v3/urls";
    private static final String API_KEY = "ec560c6ca3d0750d70f05ec997d589c008715672f1dc11bdf78dd0ce5ebd2ab4";

    public String analyzeUrl(String urlToScan) {

        String encodedUrl = URLEncoder.encode(urlToScan, StandardCharsets.UTF_8);
        encodedUrl = "url=" + encodedUrl;
        // Tạo yêu cầu HTTP POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("accept", "application/json")
                .header("x-apikey", API_KEY)
                .header("content-type", "application/x-www-form-urlencoded")
                .method("POST", HttpRequest.BodyPublishers.ofString(encodedUrl))
                .build();

        // Gửi yêu cầu POST tới VirusTotal
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            // Xử lý lỗi
            System.err.println("Unexpected error: " + e.getMessage());
            throw new RuntimeException("Unexpected error occurred: " + e.getMessage());
        }
    }
}
