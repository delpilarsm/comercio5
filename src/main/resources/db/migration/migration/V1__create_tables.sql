CREATE TABLE IF NOT EXISTS product (
    id INTEGER NOT NULL,
    sequence INTEGER NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS size (
    id INTEGER NOT NULL,
    productId INTEGER NOT NULL,
    backSoon BOOL NOT NULL,
    special BOOL NOT NULL,
    CONSTRAINT size_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS stock (
    sizeId INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
     CONSTRAINT stock_pkey PRIMARY KEY (sizeId)
);

