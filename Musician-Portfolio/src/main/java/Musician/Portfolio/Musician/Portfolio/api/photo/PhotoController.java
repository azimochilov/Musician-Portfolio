package Musician.Portfolio.Musician.Portfolio.api.photo;

import Musician.Portfolio.Musician.Portfolio.domain.entities.music.Music;
import Musician.Portfolio.Musician.Portfolio.domain.entities.photo.Photo;
import Musician.Portfolio.Musician.Portfolio.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
    @Value("${spring.file.upload-dir}")
    private String uploadDir;

    @GetMapping("/stream/{filename:.+}")
    public ResponseEntity<Resource> streamPhoto(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String mimeType = Files.probeContentType(filePath);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(mimeType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Photo> addPhoto(@RequestParam("name") String name,
                                          @RequestParam("description") String description,
                                          @RequestPart("file") MultipartFile file) {
        Photo photo = photoService.addPhoto(name, description, file);
        return ResponseEntity.ok(photo);
    }

    @GetMapping("/")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> photos = photoService.getAllPhotos();
        return ResponseEntity.ok(photos);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Photo> updatePhoto(@PathVariable Long id,
                                             @RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestPart("file") MultipartFile file) {
        Photo updatedPhoto = photoService.updatePhoto(id, name, description, file);
        return ResponseEntity.ok(updatedPhoto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        return ResponseEntity.ok("Photo deleted successfully");
    }
}

