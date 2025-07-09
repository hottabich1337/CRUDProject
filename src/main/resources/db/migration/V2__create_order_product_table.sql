CREATE TABLE IF NOT EXISTS order_product (
                                             order_id INTEGER NOT NULL,
                                             product_id INTEGER NOT NULL,
                                             quantity INTEGER NOT NULL DEFAULT 1,
                                             PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
    );