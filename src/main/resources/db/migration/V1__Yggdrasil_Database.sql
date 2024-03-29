
CREATE TABLE refresh_Token (
    id SERIAL PRIMARY KEY NOT NULL,
    token VARCHAR(1000) NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    granted_Authorities VARCHAR(200) NOT NULL,
    username VARCHAR(20) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    password VARCHAR(120) NOT NULL,
    email VARCHAR(50) NOT NULL,
    street VARCHAR(100),
    zip_Code VARCHAR(6),
    city VARCHAR(50),
    voivodeship VARCHAR(50),
    refresh_Token INTEGER REFERENCES refresh_Token(id),
    remember_Me BOOLEAN,
    accepted_Terms BOOLEAN NOT NULL,
    accepted_Rodo BOOLEAN NOT NULL,
    is_Account_Non_Expired BOOLEAN NOT NULL,
    is_Account_Non_Locked BOOLEAN NOT NULL,
    is_Credentials_Non_Expired BOOLEAN NOT NULL,
    is_Enabled BOOLEAN NOT NULL
);

INSERT INTO users (granted_Authorities, username, surname, password, email, accepted_Terms, accepted_Rodo, is_Account_Non_Expired, is_Account_Non_Locked, is_Credentials_Non_Expired, is_Enabled) VALUES ('ADMIN', 'Dawid', 'Całkowski', 'pass123', 'daodawod@odwaod', true, true, true, true, true, true);

CREATE TABLE category (
    id SERIAL PRIMARY KEY NOT NULL,
    category_Name VARCHAR(50) NOT NULL
);

INSERT INTO category (category_Name) VALUES ('Bursztyn');
INSERT INTO category (category_Name) VALUES ('Ametyst');
INSERT INTO category (category_Name) VALUES ('Topaz');
INSERT INTO category (category_Name) VALUES ('Lapiz Lazuli');
INSERT INTO category (category_Name) VALUES ('Kwarc');
INSERT INTO category (category_Name) VALUES ('Kryształ Górski');

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
INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Bursztynowe Drzewko 2','C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg' , 1, 300, 'Jest to super Drzewko Bursztynowe 2!', 1);
INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Topazowe Drzewko', 'C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg', 3, 900, 'Jest to super Topazowe Drzewko!', 1);
INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Ametystowe Drzewko','C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg' , 2, 500, 'Jest to super Ametystowe Drzewko!', 0);
INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Lapiz Lazuli Drzewko','C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg' , 4, 500, 'Jest to super Lapiz Lazuli Drzewko!', 0);
INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Kwarcowe Drzewko','C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg' , 5, 500, 'Jest to super Kwarcowe Drzewko!', 23);
INSERT INTO item(item_Name, image, category_Id, price, description, items_Left) VALUES ('Kryształ Górski Drzewko','C:\Users\dawin\IdeaProjects\Yggdrasil\src\main\js\react\assets\images\promotion_image_2.jpg' , 6, 500, 'Jest to super Kryształowe Drzewko!', 4);

CREATE TABLE shipments (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    price DECIMAL NOT NULL,
    shipment_Days INTEGER NOT NULL
);

INSERT INTO shipments(name, price, shipment_Days) VALUES ('Kurier Inpost', 11.99, 2);
INSERT INTO shipments(name, price, shipment_Days) VALUES ('Paczkomat Inpost', 11.99, 2);
INSERT INTO shipments(name, price, shipment_Days) VALUES ('DPD', 9.99, 4);
INSERT INTO shipments(name, price, shipment_Days) VALUES ('Poczta Polska', 15.99, 6);

CREATE TABLE payment (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL
);

INSERT INTO payment(name) VALUES ('Przelew Tradycyjny');
INSERT INTO payment(name) VALUES ('PayPal');

CREATE TABLE cart (
    id SERIAL PRIMARY KEY NOT NULL,
    user_Id INTEGER REFERENCES users(id),
    created_Date DATE NOT NULL DEFAULT CURRENT_DATE,
    token VARCHAR(100) NOT NULL
);

CREATE TABLE cart_Item (
    id SERIAL PRIMARY KEY NOT NULL,
    item_Id INTEGER REFERENCES item(id) NOT NULL,
    quantity INTEGER NOT NULL,
    created_Date DATE NOT NULL DEFAULT CURRENT_DATE,
    cart_Id INTEGER REFERENCES cart(id)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY NOT NULL,
    order_Value INTEGER NOT NULL,
    cart_Id INTEGER REFERENCES cart(id) NOT NULL,
    username VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    user_email VARCHAR(50) NOT NULL,
    order_Date DATE NOT NULL DEFAULT CURRENT_DATE,
    street VARCHAR(100) NOT NULL,
    zip_Code VARCHAR(6) NOT NULL,
    city VARCHAR(50) NOT NULL,
    voivodeship VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    shipments_Id INTEGER REFERENCES shipments(id),
    payment_Id INTEGER REFERENCES payment(id)
);

CREATE TABLE reset_Password_Token (
    id SERIAL PRIMARY KEY NOT NULL,
    email VARCHAR(100) NOT NULL,
    token VARCHAR(100) NOT NULL,
    expiration_Date TIMESTAMP NOT NULL
);
