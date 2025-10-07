CREATE TABLE IF NOT EXISTS client (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS employee (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    role VARCHAR(100),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL
    );

INSERT INTO client (email, password, name, birth_date) VALUES
            ('ana.silva@example.com', 'hashed_password_1', 'Ana Silva', '1995-03-12'),
            ('bruno.santos@example.com', 'hashed_password_2', 'Bruno Santos', '1990-08-25'),
            ('carla.almeida@example.com', 'hashed_password_3', 'Carla Almeida', '2001-11-03'),
            ('daniel.costa@example.com', 'hashed_password_4', 'Daniel Costa', '1988-02-14'),
            ('elaine.martins@example.com', 'hashed_password_5', 'Elaine Martins', '1999-09-27')
ON CONFLICT (email) DO NOTHING;

INSERT INTO employee (name, email, password, birth_date) VALUES
            ('João Pereira', 'joao.pereira@empresa.com', 'hashed_password_a', '1992-04-22'),
            ('Mariana Rocha', 'mariana.rocha@empresa.com', 'hashed_password_b', '1992-01-22'),
            ('Felipe Andrade', 'felipe.andrade@empresa.com', 'hashed_password_c', '1987-12-15'),
            ('Patrícia Gomes', 'patricia.gomes@empresa.com', 'hashed_password_d', '1994-04-05'),
            ('Rafael Nunes', 'rafael.nunes@empresa.com', 'hashed_password_e', '1990-07-30')
ON CONFLICT (email) DO NOTHING;
