CREATE TABLE IF NOT EXISTS roles (
                                     id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                     name VARCHAR(100) NOT NULL UNIQUE,
                                     created_at TIMESTAMP DEFAULT NOW(),
                                     description VARCHAR(255)
);

INSERT INTO roles (name, description)
VALUES
    ('Admin', 'Full access to the system and user management.'),
    ('Manager', 'Manages daily operations and staff.'),
    ('Cashier', 'Handles ticket sales and transactions.'),
    ('Employee', 'General staff member for various duties.'),
    ('Client', 'Customer who buys tickets and uses the services.')
ON CONFLICT (name) DO NOTHING;
