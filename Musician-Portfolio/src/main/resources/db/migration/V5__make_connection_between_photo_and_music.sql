ALTER TABLE music
    ADD COLUMN photo_id BIGINT;

ALTER TABLE music
    ADD CONSTRAINT fk_music_photo
        FOREIGN KEY (photo_id) REFERENCES photo(id);

CREATE INDEX idx_music_photo_id ON music(photo_id);
