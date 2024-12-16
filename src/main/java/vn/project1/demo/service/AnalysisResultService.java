package vn.project1.demo.service;

import org.springframework.stereotype.Service;

import vn.project1.demo.domain.AnalysisResult;
import vn.project1.demo.repository.AnalysisResultRepository;

@Service
public class AnalysisResultService {

    private final AnalysisResultRepository analysisResultRepository;

    public AnalysisResultService(AnalysisResultRepository analysisResultRepository) {
        this.analysisResultRepository = analysisResultRepository;
    }

    public void handleSaveAnalysisResult(AnalysisResult analysisResult) {
        this.analysisResultRepository.save(analysisResult);
    }
}
