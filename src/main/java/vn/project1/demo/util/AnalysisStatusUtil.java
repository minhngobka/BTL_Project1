package vn.project1.demo.util;

import vn.project1.demo.domain.model.AnalysisStats;

public class AnalysisStatusUtil {
    public static String analysisStatus(AnalysisStats analysisStats) {
        int malicious = analysisStats.getMalicious();
        int suspicious = analysisStats.getSuspicious();

        if (malicious == 0 && suspicious == 0) {
            return "An toàn";
        } else if (malicious > 5) {
            return "Độc hại";
        } else {
            return "Nghi vấn";
        }
    }
}
