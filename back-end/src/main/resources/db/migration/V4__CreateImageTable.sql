CREATE TABLE IF NOT EXISTS images_blob (
    id SERIAL PRIMARY KEY,
    filename VARCHAR(255),
    filetype VARCHAR(100),
    filedata BYTEA
);gi