package vn.project1.demo.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VirusTotalService {

    private static final String API_KEY = "ec560c6ca3d0750d70f05ec997d589c008715672f1dc11bdf78dd0ce5ebd2ab4";

    private final MultipartFileToSHA256Service multipartFileToSHA256Service;

    public VirusTotalService(MultipartFileToSHA256Service multipartFileToSHA256Service) {
        this.multipartFileToSHA256Service = multipartFileToSHA256Service;
    }

    public String getUrlReport(String urlId) {
        String endPoint = "https://www.virustotal.com/api/v3/urls/" + urlId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .header("accept", "application/json")
                .header("x-apikey", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
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

    public String analyzeUrl(String urlToScan) {

        String endPoint = "https://www.virustotal.com/api/v3/urls";
        String encodedUrl = URLEncoder.encode(urlToScan, StandardCharsets.UTF_8);
        encodedUrl = "url=" + encodedUrl;
        // Tạo yêu cầu HTTP POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
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

    public String getFileReport(MultipartFile file) {
        String scanFile = "";
        try {
            scanFile = this.multipartFileToSHA256Service.generateSHA256(file);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error MultipartFileToSHA256");
        }
        String endPoint = "https://www.virustotal.com/api/v3/files/" + scanFile;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .header("accept", "application/json")
                .header("x-apikey", "ec560c6ca3d0750d70f05ec997d589c008715672f1dc11bdf78dd0ce5ebd2ab4")
                .method("GET", HttpRequest.BodyPublishers.noBody())
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

    public String getIpAddressReport(String ipAdress) {
        String endPoint = "https://www.virustotal.com/api/v3/ip_addresses/" + ipAdress;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .header("accept", "application/json")
                .header("x-apikey", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
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

    public String getDomainReport(String domain) {
        String endPoint = "https://www.virustotal.com/api/v3/domains/" + domain;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .header("accept", "application/json")
                .header("x-apikey", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
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
