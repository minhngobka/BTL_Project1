package vn.project1.demo.service;

import java.io.IOException;
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

    public String analyzeFile(MultipartFile file) {
        String endPoint = "https://www.virustotal.com/api/v3/files";

        HttpClient client = HttpClient.newHttpClient();

        // Tạo một boundary ngẫu nhiên
        String boundary = "----Boundary" + System.currentTimeMillis();

        // Tạo body multipart/form-data
        StringBuilder body = new StringBuilder();
        try {
            // Phần mở đầu - boundary và thông tin file
            body.append("--").append(boundary).append("\r\n");
            body.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                    .append(file.getOriginalFilename()).append("\"\r\n");
            body.append("Content-Type: application/octet-stream\r\n\r\n");

            // Đọc nội dung file vào body
            body.append(new String(file.getBytes(), StandardCharsets.UTF_8)); // Nội dung tệp

            // Kết thúc boundary
            body.append("\r\n--").append(boundary).append("--\r\n");

            // Tạo HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endPoint))
                    .header("accept", "application/json")
                    .header("x-apikey", API_KEY) // API Key của bạn
                    .header("Content-Type", "multipart/form-data; boundary=" + boundary) // Boundary trong Content-Type
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString())) // Gửi body dưới dạng chuỗi
                    .build();

            // Gửi yêu cầu và nhận phản hồi
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body(); // Trả về kết quả quét tệp từ VirusTotal
            } else {
                throw new RuntimeException("API Error: " + response.statusCode() + " - " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Error during file scan: " + e.getMessage(), e);
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
