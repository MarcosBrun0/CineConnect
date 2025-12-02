
-- Create product_addons table to link products to their allowed addons
CREATE TABLE IF NOT EXISTS product_addons (
    product_id UUID NOT NULL,
    addon_id UUID NOT NULL,
    PRIMARY KEY (product_id, addon_id),
    CONSTRAINT fk_product_addons_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT fk_product_addons_addon FOREIGN KEY (addon_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Populate product_addons with initial data
-- Popcorns (Food) can have Butter and Salt (Addons)
INSERT INTO product_addons (product_id, addon_id)
SELECT p.id, a.id
FROM products p
CROSS JOIN products a
JOIN product_types pt_p ON p.type_id = pt_p.id
JOIN product_types pt_a ON a.type_id = pt_a.id
WHERE pt_p.name = 'Food' AND pt_a.name = 'Addon';
