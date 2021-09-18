CREATE TABLE transactions (
    id SERIAL PRIMARY KEY NOT NULL,
    transaction_Number INTEGER NOT NULL,
    transaction_Value INTEGER NOT NULL,
    item_Id INTEGER NOT NULL,
    item_Category_Id INTEGER NOT NULL,
    user_Id INTEGER NOT NULL,
    transaction_Date DATE NOT NULL DEFAULT CURRENT_DATE,
    street VARCHAR(100) NOT NULL,
    zip_Code VARCHAR(100) NOT NULL
);