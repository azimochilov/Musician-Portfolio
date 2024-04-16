package Musician.Portfolio.Musician.Portfolio.service.photo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
@Service
public class FileStorageService {

    @Value("${spring.file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path destinationPath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(fileName);

            if (!Files.exists(destinationPath.getParent())) {
                Files.createDirectories(destinationPath.getParent());
            }

            Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    public boolean deleteFile(String fileName) {
        Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(fileName);
        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file.", e);
        }
    }
}

