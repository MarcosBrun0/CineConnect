-- ==================================================================================
-- AUTOMAÇÃO DE DATAS
-- Este script usa (CURRENT_DATE + INTERVAL '1 day') para calcular "Amanhã".
-- Ao rodar este script, ele criará a grade de horários sempre para o dia seguinte.
-- ==================================================================================

-- 1. Garante que as tabelas existem (Estrutura)
CREATE TABLE IF NOT EXISTS cinemas (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL,
                                       location VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS sessions (
                                        session_id SERIAL PRIMARY KEY,
                                        time_date TIMESTAMP NOT NULL,
                                        language VARCHAR(50) NOT NULL,
                                        movie_id INTEGER NOT NULL,
                                        theater_id INTEGER NOT NULL,
                                        cinema_id INTEGER NOT NULL,

                                        CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
                                        CONSTRAINT fk_theater FOREIGN KEY (theater_id) REFERENCES theaters(id),
                                        CONSTRAINT fk_cinema FOREIGN KEY (cinema_id) REFERENCES cinemas(id) ON DELETE CASCADE
);

-- Limpa sessões antigas (OPCIONAL - descomente se quiser limpar a tabela antes de inserir)
-- TRUNCATE TABLE sessions RESTART IDENTITY;

-- Garante que os cinemas existem (usando ON CONFLICT se o ID for fixo, ou apenas INSERT simples se for primeira vez)
-- Para simplificar, vamos inserir apenas se a tabela estiver vazia:
INSERT INTO cinemas (name, location)
SELECT 'Cine Plaza', 'Centro - Rua das Flores' WHERE NOT EXISTS (SELECT 1 FROM cinemas WHERE name = 'Cine Plaza');
INSERT INTO cinemas (name, location)
SELECT 'Cine Shopping Norte', 'Shopping Norte - 3º Piso' WHERE NOT EXISTS (SELECT 1 FROM cinemas WHERE name = 'Cine Shopping Norte');
INSERT INTO cinemas (name, location)
SELECT 'Cine Cult Sul', 'Zona Sul - Av. do Mar' WHERE NOT EXISTS (SELECT 1 FROM cinemas WHERE name = 'Cine Cult Sul');
INSERT INTO cinemas (name, location)
SELECT 'Cine Multiplex Leste', 'Zona Leste - Próximo ao Metrô' WHERE NOT EXISTS (SELECT 1 FROM cinemas WHERE name = 'Cine Multiplex Leste');


-- 2. INSERÇÃO DINÂMICA
-- A sintaxe (CURRENT_DATE + INTERVAL '1 day' + TIME 'HH:MM:SS') cria o timestamp para amanhã.

-- === CINEMA 1: Cine Plaza ===
-- Sala 1
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '14:00:00'), 'Dublado', 1, 1, 1),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '19:00:00'), 'Legendado', 1, 1, 1);

-- Sala 2
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '15:00:00'), 'Dublado', 2, 2, 1),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '20:00:00'), 'Legendado', 2, 2, 1);

-- Sala 3
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '13:30:00'), 'Dublado', 3, 3, 1),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '18:30:00'), 'Dublado', 3, 3, 1);

-- Sala 4
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '16:00:00'), 'Legendado', 4, 4, 1),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '21:00:00'), 'Legendado', 4, 4, 1);


-- === CINEMA 2: Cine Shopping Norte ===
-- Sala 1
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '13:00:00'), 'Dublado', 5, 1, 2),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '18:00:00'), 'Legendado', 5, 1, 2);

-- Sala 2
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '14:30:00'), 'Dublado', 6, 2, 2),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '19:30:00'), 'Dublado', 6, 2, 2);

-- Sala 3
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '15:00:00'), 'Legendado', 7, 3, 2),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '20:00:00'), 'Legendado', 7, 3, 2);

-- Sala 4
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '16:30:00'), 'Dublado', 8, 4, 2),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '21:30:00'), 'Legendado', 8, 4, 2);


-- === CINEMA 3: Cine Cult Sul ===
-- Sala 1
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '14:15:00'), 'Dublado', 9, 1, 3),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '19:15:00'), 'Legendado', 9, 1, 3);

-- Sala 2
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '15:30:00'), 'Dublado', 1, 2, 3),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '20:30:00'), 'Legendado', 3, 2, 3);

-- Sala 3
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '13:45:00'), 'Dublado', 2, 3, 3),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '18:45:00'), 'Legendado', 5, 3, 3);

-- Sala 4
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '16:15:00'), 'Legendado', 4, 4, 3),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '21:15:00'), 'Legendado', 6, 4, 3);


-- === CINEMA 4: Cine Multiplex Leste ===
-- Sala 1
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '13:15:00'), 'Dublado', 7, 1, 4),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '18:15:00'), 'Legendado', 8, 1, 4);

-- Sala 2
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '14:45:00'), 'Dublado', 9, 2, 4),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '19:45:00'), 'Dublado', 1, 2, 4);

-- Sala 3
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '15:45:00'), 'Legendado', 2, 3, 4),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '20:45:00'), 'Legendado', 4, 3, 4);

-- Sala 4
INSERT INTO sessions (time_date, language, movie_id, theater_id, cinema_id) VALUES
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '16:45:00'), 'Dublado', 6, 4, 4),
                                                                                ((CURRENT_DATE + INTERVAL '1 day' + TIME '21:45:00'), 'Legendado', 5, 4, 4);