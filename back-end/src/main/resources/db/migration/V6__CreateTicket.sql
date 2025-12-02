CREATE TABLE tickets (
                         id SERIAL PRIMARY KEY,
                         session_id BIGINT NOT NULL,
                         seat_number VARCHAR(10) NOT NULL, -- Ex: "A1", "B5"
                         client_name VARCHAR(255),
                         purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (session_id) REFERENCES sessions(session_id)
);

-- Constraint para garantir que não vendemos o mesmo assento duas vezes na mesma sessão
CREATE UNIQUE INDEX unique_seat_per_session ON tickets(session_id, seat_number);