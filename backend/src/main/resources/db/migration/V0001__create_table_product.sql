create table product
(
    id          UUID               NOT NULL,
    name        VARCHAR(50)        NOT NULL,
    price       NUMERIC(10, 2) DEFAULT 0,
    description TEXT,
    condition   INTEGER CHECK (condition between 1 and 10),
    pokemon_id  VARCHAR(50) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);
