package vn.project1.demo.service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultipartFileToSHA256Service {

    public String generateSHA256(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        // Lấy nội dung file dưới dạng byte array
        byte[] fileBytes = file.getBytes();

        // Tạo instance của SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(fileBytes);

        // Chuyển byte array thành chuỗi hex
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
