CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL
);