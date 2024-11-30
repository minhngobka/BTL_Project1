package vn.project1.demo.service;

import org.springframework.stereotype.Service;

import vn.project1.demo.domain.AnalysisResult;
import vn.project1.demo.repository.AnalysisResultRepository;

@Service
public class AnalysisResultService {

    private final AnalysisResultRepository analysisResultRepository;
    private final VirusTotalService virusTotalService;

    public AnalysisResultService(AnalysisResultRepository analysisResultRepository,
            VirusTotalService virusTotalService) {
        this.analysisResultRepository = analysisResultRepository;
        this.virusTotalService = virusTotalService;
    }

    public void handleSaveAnalysisResult(AnalysisResult analysisResult) {
        this.analysisResultRepository.save(analysisResult);
    }
}
