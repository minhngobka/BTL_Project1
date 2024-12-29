package vn.project1.demo.service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

import jakarta.servlet.http.HttpServletResponse;
import vn.project1.demo.domain.AnalysisResult;

@Service
public class ExportCsvService {

    private final AnalysisResultService analysisResultService;

    public ExportCsvService(AnalysisResultService analysisResultService) {
        this.analysisResultService = analysisResultService;
    }

    public void export(HttpServletResponse response) throws IOException {
        // Lấy dữ liệu từ database
        List<AnalysisResult> analysisResults = this.analysisResultService.fetchAllAnalysisResults();

        // Thiết lập headers cho file CSV
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=KetQuaPhanTich.csv");

        // Ghi dữ liệu vào output stream
        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
        CSVWriter csvWriter = new CSVWriter(writer);

        // Ghi header
        String[] header = { "Type", "Name/File", "Detection Ratio", "Analysis Time" };
        csvWriter.writeNext(header);

        // Ghi dữ liệu
        for (AnalysisResult result : analysisResults) {
            String[] data = {
                    result.getType(),
                    result.getTarget(),
                    result.getResult(),
                    result.getAnalysisTime()
            };
            csvWriter.writeNext(data);
        }

        csvWriter.flush();
        writer.close();
    }
}
