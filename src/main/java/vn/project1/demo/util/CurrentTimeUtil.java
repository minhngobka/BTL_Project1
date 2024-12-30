package vn.project1.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimeUtil {
    public static String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
}
