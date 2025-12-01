-- Cria a tabela de sessões
CREATE TABLE IF NOT EXISTS sessions (
                                        session_id SERIAL PRIMARY KEY,
                                        time_date TIMESTAMP NOT NULL,
                                        language VARCHAR(50) NOT NULL,
    movie_id INTEGER NOT NULL,
    theater_id INTEGER NOT NULL,

    -- Configuração das chaves estrangeiras
    CONSTRAINT fk_movie
    FOREIGN KEY (movie_id)
    REFERENCES movies(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_theater
        FOREIGN KEY (theater_id)
        REFERENCES theaters(id)
    );