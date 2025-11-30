CREATE TABLE purchases (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id),
    payment_id VARCHAR(255),
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE purchase_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    purchase_id UUID NOT NULL REFERENCES purchases(id) ON DELETE CASCADE,
    product_id UUID NOT NULL REFERENCES products(id),
    quantity INT NOT NULL,
    price_at_purchase DECIMAL(10, 2) NOT NULL
);

CREATE TABLE purchase_addons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    purchase_item_id UUID NOT NULL REFERENCES purchase_items(id) ON DELETE CASCADE,
    product_id UUID NOT NULL REFERENCES products(id),
    quantity INT NOT NULL,
    price_at_purchase DECIMAL(10, 2) NOT NULL
);
