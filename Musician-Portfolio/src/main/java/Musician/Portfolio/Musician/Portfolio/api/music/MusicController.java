package Musician.Portfolio.Musician.Portfolio.api.music;

import Musician.Portfolio.Musician.Portfolio.domain.entities.music.Music;
import Musician.Portfolio.Musician.Portfolio.service.music.MusicService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;
    @Value("${spring.file.upload-dir}")
    private String uploadDir;
    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Music> addMusic(@RequestParam("name") String name,
                                          @RequestParam("description") String description,
                                          @RequestPart("file") MultipartFile file) {
        Music music = musicService.addMusic(name, description, file);
        return ResponseEntity.ok(music);
    }

    @GetMapping("/")
    public ResponseEntity<List<Music>> getAllMusic() {
        List<Music> musics = musicService.getAllMusics();
        return ResponseEntity.ok(musics);
    }

    @GetMapping("/music/{filename:.+}")
    public ResponseEntity<?> downloadMusic(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(file);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            return ResponseEntity.badRequest().body("URL is malformed");
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().body("Error reading the file");
        }
    }
    @GetMapping("/music/all")
    public void downloadAllMusic(HttpServletResponse response) {

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"all_music.zip\"");

        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
            Path uploadDirectory = Paths.get(uploadDir);

            Files.walk(uploadDirectory)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(uploadDirectory.relativize(path).toString());
                        try {
                            zipOut.putNextEntry(zipEntry);
                            Files.copy(path, zipOut);
                            zipOut.closeEntry();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Music> updateMusic(@PathVariable Long id,
                                             @RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestPart("file") MultipartFile file) {
        Music updatedMusic = musicService.updateMusic(id, name, description, file);
        return ResponseEntity.ok(updatedMusic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMusic(@PathVariable Long id) {
        musicService.deleteMusic(id);
        return ResponseEntity.ok("Music deleted successfully");
    }

    @PostMapping(value = "/with/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Music> uploadMusic(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestPart("musicFile") MultipartFile musicFile,
            @RequestPart("photoFile") MultipartFile photoFile) {
        Music music = musicService.addMusicWithPhoto(name, description, musicFile, photoFile);
        return ResponseEntity.ok(music);
    }


}
