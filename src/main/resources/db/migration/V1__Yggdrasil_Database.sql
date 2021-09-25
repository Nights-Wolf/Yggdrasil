CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(120) NOT NULL,
    email VARCHAR(50) NOT NULL,
    street VARCHAR(100) NOT NULL,
    zip_Code VARCHAR(6) NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY NOT NULL,
    category_Name VARCHAR(50) NOT NULL
);

CREATE TABLE item (
    id SERIAL PRIMARY KEY NOT NULL,
    item_Name VARCHAR(100) NOT NULL,
    created DATE NOT NULL DEFAULT CURRENT_DATE,
    category_Id INTEGER REFERENCES category(id) NOT NULL,
    price INTEGER NOT NULL,
    items_Left INTEGER NOT NULL
);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY NOT NULL,
    transaction_Number INTEGER NOT NULL,
    transaction_Value INTEGER NOT NULL,
    item_Id INTEGER REFERENCES item(id) NOT NULL,
    user_Id INTEGER REFERENCES users(id),
    user_email VARCHAR(50) NOT NULL,
    transaction_Date DATE NOT NULL DEFAULT CURRENT_DATE,
    street VARCHAR(100) NOT NULL,
    zip_Code VARCHAR(6) NOT NULL
);