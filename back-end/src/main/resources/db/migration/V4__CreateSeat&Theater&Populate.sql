-- 1. Tabela de Salas (Theaters)
CREATE TABLE IF NOT EXISTS theaters (
                                        id SERIAL PRIMARY KEY,
                                        capacity INTEGER NOT NULL
);

-- 2. Tabela de Assentos (Seats)
CREATE TABLE IF NOT EXISTS seats (
                                     seat_id SERIAL PRIMARY KEY,
                                     row_num INTEGER NOT NULL,
                                     seat_num INTEGER NOT NULL,     -- Número do assento na fileira
                                     theater_id INTEGER NOT NULL,

    -- Relacionamento: Se apagar a sala, apaga os assentos
                                     CONSTRAINT fk_theater_seat
                                     FOREIGN KEY (theater_id)
    REFERENCES theaters(id)
    ON DELETE CASCADE
    );

INSERT INTO theaters (id, capacity)
SELECT ids, 40
FROM generate_series(1, 4) AS ids;

-- 3. Gera os 160 assentos (4 salas x 40 assentos) de uma só vez
INSERT INTO seats (theater_id, row_num, seat_num)
SELECT
    sala.id,        -- ID da Sala (1, 2, 3, 4)
    fileira.num,    -- Número da fileira (1 a 5)
    cadeira.num     -- Número da cadeira (1 a 8)
FROM
    generate_series(1, 4) AS sala(id)       -- Para cada uma das 4 salas...
        CROSS JOIN
    generate_series(1, 5) AS fileira(num)   -- ...crie 5 fileiras...
        CROSS JOIN
    generate_series(1, 8) AS cadeira(num);  -- ...com 8 cadeiras cada.

