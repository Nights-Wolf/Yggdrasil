CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    granted_Authorities VARCHAR(200) NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(120) NOT NULL,
    email VARCHAR(50) NOT NULL,
    street VARCHAR(100) NOT NULL,
    zip_Code VARCHAR(6) NOT NULL,
    is_Account_Non_Expired BOOLEAN NOT NULL,
    is_Account_Non_Locked BOOLEAN NOT NULL,
    is_Credentials_Non_Expired BOOLEAN NOT NULL,
    is_Enabled BOOLEAN NOT NULL
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY NOT NULL,
    category_Name VARCHAR(50) NOT NULL
);

INSERT INTO category (category_Name) VALUES ('Bursztynowe');

CREATE TABLE item (
    id SERIAL PRIMARY KEY NOT NULL,
    item_Name VARCHAR(100) NOT NULL,
    image VARCHAR(500) NOT NULL,
    created DATE NOT NULL DEFAULT CURRENT_DATE,
    category_Id INTEGER REFERENCES category(id) NOT NULL,
    price INTEGER NOT NULL,
    description VARCHAR(1000) NOT NULL,
    items_Left INTEGER NOT NULL
);

INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Bursztynowe Drzewko','C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg' , 1, 300, 'Jest to super Drzewko Bursztynowe!', 1);
INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Awangardowe Drzewko', 'C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg', 1, 300, 'Jest to super Awangardowe Drzewko!', 1);
INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Ametystowe Drzewko','C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg' , 1, 300, 'Jest to super Ametystowe Drzewko!', 0);

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
