CREATE TABLE purchases (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    payment_id VARCHAR(255),
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_purchases_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE purchase_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    purchase_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INT NOT NULL,
    price_at_purchase DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_purchase_items_purchase FOREIGN KEY (purchase_id) REFERENCES purchases(id),
    CONSTRAINT fk_purchase_items_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE purchase_addons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    purchase_item_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INT NOT NULL,
    price_at_purchase DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_purchase_addons_item FOREIGN KEY (purchase_item_id) REFERENCES purchase_items(id),
    CONSTRAINT fk_purchase_addons_product FOREIGN KEY (product_id) REFERENCES products(id)
);
