-- Create product_addons table to link products to their allowed addons
CREATE TABLE IF NOT EXISTS product_addons (
    product_id UUID NOT NULL,
    addon_id UUID NOT NULL,
    PRIMARY KEY (product_id, addon_id),
    CONSTRAINT fk_product_addons_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT fk_product_addons_addon FOREIGN KEY (addon_id) REFERENCES products(id) ON DELETE CASCADE
);

