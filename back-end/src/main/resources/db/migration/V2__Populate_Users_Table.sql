INSERT INTO client (email, password, name, birth_date) VALUES
                                                           ('ana.silva@example.com', 'hashed_password_1', 'Ana Silva', '1995-03-12'),
                                                           ('bruno.santos@example.com', 'hashed_password_2', 'Bruno Santos', '1990-08-25'),
                                                           ('carla.almeida@example.com', 'hashed_password_3', 'Carla Almeida', '2001-11-03'),
                                                           ('daniel.costa@example.com', 'hashed_password_4', 'Daniel Costa', '1988-02-14'),
                                                           ('elaine.martins@example.com', 'hashed_password_5', 'Elaine Martins', '1999-09-27')
ON CONFLICT (email) DO NOTHING;

INSERT INTO employee (name, email, password, birth_date, cpf) VALUES
                                                                  ('João Pereira', 'joao.pereira@empresa.com', 'hashed_password_a', '1985-06-10', '123.456.789-00'),
                                                                  ('Mariana Rocha', 'mariana.rocha@empresa.com', 'hashed_password_b', '1992-01-22', '987.654.321-00'),
                                                                  ('Felipe Andrade', 'felipe.andrade@empresa.com', 'hashed_password_c', '1987-12-15', '111.222.333-44'),
                                                                  ('Patrícia Gomes', 'patricia.gomes@empresa.com', 'hashed_password_d', '1994-04-05', '555.666.777-88'),
                                                                  ('Rafael Nunes', 'rafael.nunes@empresa.com', 'hashed_password_e', '1990-07-30', '999.888.777-66')
ON CONFLICT (email) DO NOTHING;
