CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    transactionNumber INTEGER NOT NULL,
    transactionValue INTEGER NOT NULL,
    itemId INTEGER NOT NULL,
    itemCategoryId INTEGER NOT NULL,
    userId INTEGER NOT NULL,
    transactionDate DATE NOT NULL DEFAULT CURRENT_DATE,
    street VARCHAR(100) NOT NULL,
    zipCode VARCHAR(100) NOT NULL
);