ALTER TABLE products ADD COLUMN image_url VARCHAR(500);

UPDATE products SET image_url = 'https://images.unsplash.com/photo-1578849278619-e73505e9610f?auto=format&fit=crop&q=80&w=300' WHERE name = 'Large Popcorn';
UPDATE products SET image_url = 'https://images.unsplash.com/photo-1578849278619-e73505e9610f?auto=format&fit=crop&q=80&w=300' WHERE name = 'Medium Popcorn';
UPDATE products SET image_url = 'https://images.unsplash.com/photo-1511381939415-e44015466834?auto=format&fit=crop&q=80&w=300' WHERE name = 'Chocolate Bar';
UPDATE products SET image_url = 'https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&q=80&w=300' WHERE name = 'Large Soda';
UPDATE products SET image_url = 'https://images.unsplash.com/photo-1564414297-701a05a0551e?auto=format&fit=crop&q=80&w=300' WHERE name = 'Water Bottle';
