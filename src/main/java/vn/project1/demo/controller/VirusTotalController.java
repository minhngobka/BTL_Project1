package vn.project1.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.project1.demo.domain.AnalysisResult;
import vn.project1.demo.service.VirusTotalService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class VirusTotalController {

    private final VirusTotalService virusTotalService;

    public VirusTotalController(VirusTotalService virusTotalService) {
        this.virusTotalService = virusTotalService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "files/show";
    }

    @GetMapping("/analyze/url")
    public String getUrlPage(Model model) {
        return "url/show";
    }

    @PostMapping("/analyze/url")
    public String analyzeUrl(@RequestParam("target") String target, Model model) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Gọi service để phân tích URL
            String detailUrl = virusTotalService.analyzeUrl(target);
            Map<String, Object> map = objectMapper.readValue(detailUrl, Map.class);
            // Lấy đối tượng "data" dưới dạng Map
            Map<String, Object> dataMap = (Map<String, Object>) map.get("data");

            // Lấy giá trị của khóa "id"
            String urlId = (String) dataMap.get("id");
            String[] urlIds = urlId.split("-");
            System.out.println(urlIds[1]);
            String result = virusTotalService.getUrlReport(urlIds[1]);

            // Lưu kết quả phân tích
            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setType("url");
            analysisResult.setTarget(target);
            analysisResult.setResult(result);
            model.addAttribute("analysisResult", analysisResult);

            // Tạo biểu đồ từ dịch vụ và truyền vào model
            String chartImage = this.virusTotalService.creatBarChart(result, target);
            model.addAttribute("chart", chartImage);

            return "virustotal/result"; // Hiển thị kết quả
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi phân tích: " + e.getMessage());
            return "virustotal/error"; // Hiển thị trang lỗi
        }
    }

    @GetMapping("/analyze/file")
    public String getFilePage(Model model) {
        return "files/show";
    }

    @PostMapping("/analyze/file")
    public String analyzeFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Gọi service để phân tích URL
            String result = virusTotalService.getFileReport(file);

            // Lưu kết quả phân tích
            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setType("file");
            analysisResult.setTarget(file.getOriginalFilename());
            analysisResult.setResult(result);
            model.addAttribute("analysisResult", analysisResult);

            // Tạo biểu đồ từ dịch vụ và truyền vào model
            String chartImage = this.virusTotalService.creatBarChart(result, file.getOriginalFilename());
            model.addAttribute("chart", chartImage);

            return "virustotal/result"; // Hiển thị kết quả
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi phân tích: " + e.getMessage());
            return "virustotal/error"; // Hiển thị trang lỗi
        }
    }

    @GetMapping("/analyze/ip")
    public String getIpPage(Model model) {
        return "ips/show";
    }

    @PostMapping("/analyze/ip")
    public String analyzeIp(@RequestParam("target") String target, Model model) {
        try {
            // Gọi service để phân tích URL
            String result = virusTotalService.getIpAddressReport(target);

            // Lưu kết quả phân tích
            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setType("ip_address");
            analysisResult.setTarget(target);
            analysisResult.setResult(result);
            model.addAttribute("analysisResult", analysisResult);

            // Tạo biểu đồ từ dịch vụ và truyền vào model
            String chartImage = this.virusTotalService.creatBarChart(result, target);
            model.addAttribute("chart", chartImage);

            return "virustotal/result"; // Hiển thị kết quả
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi phân tích: " + e.getMessage());
            return "virustotal/error"; // Hiển thị trang lỗi
        }
    }

    @GetMapping("/analyze/domain")
    public String getDomainPage(Model model) {
        return "domain/show";
    }

    @PostMapping("/analyze/domain")
    public String analyzeDomain(@RequestParam("target") String target, Model model) {
        try {
            // Gọi service để phân tích URL
            String result = virusTotalService.getDomainReport(target);

            // Lưu kết quả phân tích
            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setType("domain");
            analysisResult.setTarget(target);
            analysisResult.setResult(result);
            model.addAttribute("analysisResult", analysisResult);

            // Tạo biểu đồ từ dịch vụ và truyền vào model
            String chartImage = this.virusTotalService.creatBarChart(result, target);
            model.addAttribute("chart", chartImage);

            return "virustotal/result"; // Hiển thị kết quả
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi phân tích: " + e.getMessage());
            return "virustotal/error"; // Hiển thị trang lỗi
        }
    }
}
