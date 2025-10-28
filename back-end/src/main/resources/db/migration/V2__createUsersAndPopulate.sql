
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    role_id INT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_users_roles
            FOREIGN KEY(role_id)
            REFERENCES roles(id)
            ON DELETE SET NULL
);


INSERT INTO users (name, role_id, email, password, birth_date)
VALUES
    ('Alice Admin',       (SELECT id FROM roles WHERE name = 'Admin'),    'alice.admin@example.com',    'password123', '1985-03-12'),
    ('Bob Manager',       (SELECT id FROM roles WHERE name = 'Manager'),  'bob.manager@example.com',    'password123', '1990-07-25'),
    ('Carol Cashier',     (SELECT id FROM roles WHERE name = 'Cashier'),  'carol.cashier@example.com',  'password123', '1995-11-03'),
    ('David Employee',    (SELECT id FROM roles WHERE name = 'Employee'), 'david.employee@example.com', 'password123', '2000-01-20'),
    ('Eve Client',        (SELECT id FROM roles WHERE name = 'Client'),   'eve.client@example.com',     'password123', '2002-05-15')
ON CONFLICT (email) DO NOTHING;
