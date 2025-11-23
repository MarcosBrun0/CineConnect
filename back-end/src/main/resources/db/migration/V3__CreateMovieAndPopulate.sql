CREATE TABLE movies (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        synopsis TEXT,
                        genre VARCHAR(100),
                        duration INTEGER,     -- minutos
                        rating DECIMAL(3, 1), -- ex: 8.5
                        image_filename VARCHAR(255) -- Aqui guardamos "poster_batman.jpg"
);

