package vn.project1.demo.service;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.project1.demo.domain.model.AnalysisStats;

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

    public AnalysisStats createAnalysisStats(String response) {
        AnalysisStats analysisStats = new AnalysisStats();
        try {
            // Khởi tạo ObjectMapper của Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Đọc chuỗi JSON vào Map
            Map<String, Object> jsonMap = objectMapper.readValue(response, Map.class);

            // Lấy đối tượng "data" dưới dạng Map
            Map<String, Object> dataMap = (Map<String, Object>) jsonMap.get("data");

            // Lấy đối tượng "attributes" dưới dạng Map
            Map<String, Object> attributesMap = (Map<String, Object>) dataMap.get("attributes");

            // Lấy đối tượng "last_analysis_stats" dưới dạng Map
            Map<String, Object> statsMap = (Map<String, Object>) attributesMap.get("last_analysis_stats");

            // Truy xuất các giá trị của các khóa "malicious", "undetected", "suspicious",
            // "timeout"
            int malicious = (Integer) statsMap.getOrDefault("malicious", 0);
            int undetected = (Integer) statsMap.getOrDefault("undetected", 0);
            int harmless = (Integer) statsMap.getOrDefault("harmless", 0);
            int suspicious = (Integer) statsMap.getOrDefault("suspicious", 0);
            int timeout = (Integer) statsMap.getOrDefault("timeout", 0);

            analysisStats.setMalicious(malicious);
            analysisStats.setUndetected(undetected);
            analysisStats.setHarmless(harmless);
            analysisStats.setSuspicious(suspicious);
            analysisStats.setTimeout(timeout);

            return analysisStats;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    public String creatBarChart(AnalysisStats analysisStats, String target) {
        try {
            int malicious = analysisStats.getMalicious();
            int undetected = analysisStats.getUndetected();
            int harmless = analysisStats.getHarmless();
            int suspicious = analysisStats.getSuspicious();
            int timeout = analysisStats.getTimeout();

            // Tạo dataset cho biểu đồ cột
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(malicious, "Results", "Malicious");
            dataset.addValue(undetected, "Results", "Undetected");
            dataset.addValue(harmless, "Results", "harmless");
            dataset.addValue(suspicious, "Results", "Suspicious");
            dataset.addValue(timeout, "Results", "Timeout");

            // Tạo biểu đồ cột
            JFreeChart barChart = ChartFactory.createBarChart(
                    "VirusTotal Analysis for " + target, // Tên biểu đồ
                    "Category", // Tiêu đề trục X
                    "Number of Results", // Tiêu đề trục Y
                    dataset // Dữ liệu
            );

            // Chuyển biểu đồ thành hình ảnh PNG
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(byteArrayOutputStream, barChart, 600, 400);

            // Chuyển byte array thành chuỗi base64
            String base64Image = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            return "data:image/png;base64," + base64Image; // Trả về chuỗi base64
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
