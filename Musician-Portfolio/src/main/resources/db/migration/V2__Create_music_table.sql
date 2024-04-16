CREATE TABLE music (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       description TEXT,
                       date_uploaded TIMESTAMP,
                       file_path TEXT
);
