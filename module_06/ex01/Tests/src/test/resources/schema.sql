CREATE
    TABLE IF NOT EXISTS product (
        identifier  INTEGER PRIMARY KEY IDENTITY,
        name        VARCHAR(10) NOT NULL,
        price       INTEGER NOT NULL
);