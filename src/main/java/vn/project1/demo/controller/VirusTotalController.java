package vn.project1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.project1.demo.domain.AnalysisResult;
import vn.project1.demo.service.AnalysisResultService;
import vn.project1.demo.service.VirusTotalService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VirusTotalController {

    private final VirusTotalService virusTotalService;
    private final AnalysisResultService analysisResultService;

    public VirusTotalController(VirusTotalService virusTotalService, AnalysisResultService analysisResultService) {
        this.virusTotalService = virusTotalService;
        this.analysisResultService = analysisResultService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "virustotal/show";
    }

    @PostMapping("/analyze")
    public String analyzeUrl(@RequestParam("type") String type,
            @RequestParam("target") String target,
            Model model) {
        try {
            // Gọi service để phân tích URL
            String result = virusTotalService.analyzeUrl(target);

            // Lưu kết quả phân tích
            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setType(type);
            analysisResult.setTarget(target);
            analysisResult.setResult(result);
            model.addAttribute("analysisResult", analysisResult);

            return "virustotal/result"; // Hiển thị kết quả
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi phân tích: " + e.getMessage());
            return "virustotal/error"; // Hiển thị trang lỗi
        }
    }

}
