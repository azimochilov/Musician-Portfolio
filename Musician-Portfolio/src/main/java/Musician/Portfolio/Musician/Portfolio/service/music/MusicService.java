package Musician.Portfolio.Musician.Portfolio.service.music;

import Musician.Portfolio.Musician.Portfolio.domain.entities.music.Music;
import Musician.Portfolio.Musician.Portfolio.domain.entities.photo.Photo;
import Musician.Portfolio.Musician.Portfolio.repository.music.MusicRepository;
import Musician.Portfolio.Musician.Portfolio.service.photo.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final FileStorageService fileStorageService;

    public MusicService(MusicRepository musicRepository, FileStorageService fileStorageService) {
        this.musicRepository = musicRepository;
        this.fileStorageService = fileStorageService;
    }

    public Music addMusic(String name, String description, MultipartFile file) {
        String fileName = fileStorageService.uploadFile(file);
        Music music = new Music();
        music.setName(name);
        music.setDescription(description);
        music.setDateUploaded(new Date());
        music.setFilePath(fileName);
        return musicRepository.save(music);
    }
    public Music addMusicWithPhoto(String name, String description, MultipartFile musicFile, MultipartFile photoFile) {
        String musicFileName = fileStorageService.uploadFile(musicFile);
        String photoFileName = fileStorageService.uploadFile(photoFile);

        Photo photo = new Photo();
        photo.setName("Associated photo for " + name);
        photo.setDescription("Photo for " + description);
        photo.setDateUploaded(new Date());
        photo.setFilePath(photoFileName);

        Music music = new Music();
        music.setName(name);
        music.setDescription(description);
        music.setDateUploaded(new Date());
        music.setFilePath(musicFileName);
        music.setPhoto(photo);

        return musicRepository.save(music);
    }

    public Music getMusicWithPhoto(Long id) {
        return musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found"));
    }

    public void deleteMusic(Long id) {
        Music music = musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found with id " + id));
        boolean isDeleted = fileStorageService.deleteFile(music.getFilePath());
        if (isDeleted) {
            musicRepository.delete(music);
        } else {
            throw new RuntimeException("Failed to delete music file.");
        }
    }

    public Music updateMusic(Long id, String newName, String newDescription, MultipartFile newFile) {
        Music music = musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found"));
        if (newFile != null && !newFile.isEmpty()) {
            String newFileName = fileStorageService.uploadFile(newFile);

            fileStorageService.deleteFile(music.getFilePath());
            music.setFilePath(newFileName);
        }
        music.setName(newName);
        music.setDescription(newDescription);
        return musicRepository.save(music);
    }

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }
}