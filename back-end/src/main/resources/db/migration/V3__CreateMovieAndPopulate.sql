
CREATE TABLE movies (
                        movie_id INT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        duration INT, -- Duration in minutes
                        rating DECIMAL(3, 1), -- e.g., 8.5
                        img VARCHAR(255) -- URL or path to the movie poster
);

INSERT INTO movies (movie_id, name, duration, rating, img) VALUES
        (1, 'Inception', 148, 8.8, 'images/inception.jpg'),
        (2, 'The Shawshank Redemption', 142, 9.3, 'images/shawshank.jpg'),
        (3, 'The Dark Knight', 152, 9.0, 'images/dark_knight.jpg');

