CREATE TABLE movies (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        synopsis TEXT,
                        genre VARCHAR(100),
                        duration INTEGER,     -- minutos
                        rating DECIMAL(3, 1), -- ex: 8.5
                        image_filename VARCHAR(255) -- Aqui guardamos "poster_batman.png"
);

INSERT INTO movies (id, title, synopsis, genre, duration, rating, image_filename) VALUES
                                                                                      (1, 'Em busca da derivada perfeita', 'Um professor de matemática genial, mas atormentado, arrisca sua carreira e sanidade ao se tornar obcecado por um problema de cálculo que ninguém jamais resolveu.', 'Drama', 120, 8.5, 'poster_derivada.png'),

                                                                                      (2, 'Bikes O filme', 'Um grupo de amigos de infância encontra um mapa misterioso durante as férias de verão. Usando apenas suas bicicletas, eles embarcam em uma jornada épica para encontrar um tesouro perdido na cidade.', 'Aventura', 145, 9.1, 'poster_bikes.png'),

                                                                                      (3, 'O Sapo', 'Uma bióloga é enviada a uma cidade pantanosa e isolada para investigar uma estranha mutação em anfíbios. Ela logo descobre que uma criatura antiga e faminta acordou.', 'Terror', 95, 7.8, 'poster_sapo.png'),

                                                                                      (4, 'SCTI a origem', 'Em um futuro próximo, a inteligência artificial "SCTI" gerencia o mundo. Quando ela começa a agir de forma errática, sua criadora deve invadir o sistema e revisitar suas próprias memórias para encontrar a falha original.', 'Scifi', 160, 8.8, 'poster_scti.png'),

                                                                                      (5, 'Agente 121: Teoria da Conspiração', 'Para salvar o país de uma ameaça invisível, um espião de elite precisa enfrentar seu desafio mais difícil: passar nas provas finais enquanto investiga seu professor favorito.', 'Ação', 130, 8.2, 'poster_agente121.png'),

                                                                                      (6, 'Restaurante Universitario 2 o inimigo é outro', 'Após salvar o RU da fila interminável, os heróis estudantes agora enfrentam uma ameaça corporativa que quer privatizar o restaurante e substituir o "bandejão" por comida gourmet superfaturada.', 'Ação', 132, 7.5, 'poster_ru2.png'),

                                                                                      (7, 'Grafos Ocultos', 'Um detetive cético se une a um matemático especialista em Teoria dos Grafos para traçar um padrão em assassinatos aparentemente aleatórios, descobrindo uma conspiração conectada por "nós" invisíveis na sociedade.', 'Mistério', 128, 8.0, 'poster_grafos.png'),

                                                                                      (8, 'Fricasse onde esta voce?', '"Fricassê" é o nome de um chef mundialmente famoso que desaparece misteriosamente. Um detetive particular durão, que odeia comida chique, deve mergulhar no mundo da alta gastronomia para encontrá-lo.', 'Comédia', 150, 9.0, 'poster_fricasse.png'),

                                                                                      (9, 'O Código de Quicksort', 'Uma estudante de ciência da computação encontra uma mensagem criptografada dentro do algoritmo Quicksort. Ela se vê em uma caça ao tesouro digital, perseguida por uma organização que busca a "chave" para controlar a internet.', 'Aventura', 115, 7.2, 'poster_quicksort.png');