package Musician.Portfolio.Musician.Portfolio.service.photo;

import Musician.Portfolio.Musician.Portfolio.domain.entities.photo.Photo;
import Musician.Portfolio.Musician.Portfolio.repository.photo.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final FileStorageService fileStorageService;

    public PhotoService(PhotoRepository photoRepository, FileStorageService fileStorageService) {
        this.photoRepository = photoRepository;
        this.fileStorageService = fileStorageService;
    }

    public Photo addPhoto(String name, String description, MultipartFile file) {
        String fileName = fileStorageService.uploadFile(file);
        Photo photo = new Photo();
        photo.setName(name);
        photo.setDescription(description);
        photo.setDateUploaded(new Date());
        photo.setFilePath(fileName);
        return photoRepository.save(photo);
    }

    public void deletePhoto(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
        fileStorageService.deleteFile(photo.getFilePath());
        photoRepository.delete(photo);
    }

    public Photo updatePhoto(Long id, String newName, String newDescription, MultipartFile newFile) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
        if (newFile != null && !newFile.isEmpty()) {
            fileStorageService.deleteFile(photo.getFilePath());
            String newFileName = fileStorageService.uploadFile(newFile);
            photo.setFilePath(newFileName);
        }
        photo.setName(newName);
        photo.setDescription(newDescription);
        return photoRepository.save(photo);
    }
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}
