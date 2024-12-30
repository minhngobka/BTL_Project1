package vn.project1.demo.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import vn.project1.demo.domain.AnalysisResult;

import java.io.IOException;
import java.util.List;

@Service
public class ExportExcelService {

    private final AnalysisResultService analysisResultService;

    public ExportExcelService(AnalysisResultService analysisResultService) {
        this.analysisResultService = analysisResultService;
    }

    public void export(HttpServletResponse response) throws IOException {
        // Cột tiêu đề và dữ liệu mẫu
        String[] columns = { "Id", "Type", "Name/File", "Detection Ratio", "Status", "Analysis Time" };
        List<AnalysisResult> analysisResults = this.analysisResultService.fetchAllAnalysisResults();

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Kết quả phân tích");

        // Tạo hàng tiêu đề
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Điền dữ liệu vào các hàng
        int rowIndex = 1; // Bắt đầu từ dòng 1 (dòng 0 là tiêu đề)
        for (AnalysisResult result : analysisResults) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(result.getId());
            row.createCell(1).setCellValue(result.getType());
            row.createCell(2).setCellValue(result.getName());
            row.createCell(3).setCellValue(result.getDetectionRatio());
            row.createCell(4).setCellValue(result.getStatus());
            row.createCell(5).setCellValue(result.getAnalysisTime());
        }

        // Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Thiết lập response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=KetQuaPhanTich.xlsx");

        // Ghi file Excel vào response output stream
        workbook.write(response.getOutputStream());
        workbook.close();
    }

}
