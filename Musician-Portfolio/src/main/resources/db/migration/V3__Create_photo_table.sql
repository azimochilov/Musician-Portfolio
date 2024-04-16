CREATE TABLE photo (
                       id BIGSERIAL PRIMARY KEY,
                       name TEXT,
                       description TEXT,
                       date_uploaded TIMESTAMP,
                       file_path TEXT
);
