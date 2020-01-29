drop database shoewebshop;

create database shoewebshop;

use shoewebshop;

SET GLOBAL log_bin_trust_function_creators = 1;

create table City
(
    ID   int not null auto_increment,
    Name varchar(255),
    PRIMARY KEY (ID)
);

create table Customer
(
    ID        int          not null auto_increment,
    FirstName varchar(255) not null,
    LastName  varchar(255) not null,
    CityID    int,
    Password  varchar(20)  not null,
    FOREIGN KEY (CityID) references City (ID) on delete set null, -- Jag använder on delete set null för att förhindra problem om city tas bort.
    PRIMARY KEY (ID)
);

create table Category
(
    ID   int          not null auto_increment,
    Name varchar(255) not null,
    PRIMARY KEY (ID)
);

create table Brand
(
    ID   int          not null auto_increment,
    Name varchar(255) not null,
    PRIMARY KEY (ID)
);

create table Shoe
(
    ID       int          not null auto_increment,
    Name     varchar(255) not null,
    Size     varchar(255) not null,
    Color    varchar(255) not null,
    Price    int          not null,
    BrandID  int          not null,
    Quantity int          not null DEFAULT 0,
    FOREIGN KEY (BrandID) references Brand (ID) on delete cascade on update cascade, -- Om jag tar bort ett märke försvinner skorna som tillhör.
    PRIMARY KEY (ID)
);

create table Rating
(
    ID         int                 not null auto_increment,
    Score      tinyint(1) UNSIGNED not null DEFAULT 0,
    Comment    varchar(255)        not null,
    ShoeID     int                 not null,
    CustomerID int,
    FOREIGN KEY (ShoeID) references Shoe (ID) on delete cascade on update cascade,
    FOREIGN KEY (CustomerID) references Customer (ID) on delete set null,
    PRIMARY KEY (ID)
);

create table Shoe_Category
(
    ShoeID     int not null,
    CategoryID int not null,
    PRIMARY KEY (ShoeID, CategoryID),
    FOREIGN KEY (ShoeID) references Shoe (ID) on delete cascade,
    FOREIGN KEY (CategoryID) references Category (ID) on delete cascade
);


create table Orders
(
    ID         int auto_increment,
    Date       DATE not null,
    CustomerID int  not null,
    FOREIGN KEY (CustomerID) references Customer (ID) on delete cascade on update cascade,
    PRIMARY KEY (ID)
);

create table Shoe_Orders
(
    ShoeID    int     not null,
    OrdersID  int,
    Quantity  int     not null,
    Completed boolean not null DEFAULT false,
    PRIMARY KEY (OrdersID, ShoeID),
    FOREIGN KEY (ShoeID) references Shoe (ID) on delete cascade,
    FOREIGN KEY (OrdersID) references Orders (ID) on delete cascade
);
CREATE TABLE Out_Of_Stock
(
    ID     int  not null auto_increment,
    ShoeID int  not null,
    Date   DATE not null,
    PRIMARY KEY (ID),
    FOREIGN KEY (ShoeID) references Shoe (ID) ON DELETE CASCADE
);


INSERT City (Name)
VALUES ('Stockholm'),
       ('Gothenburg'),
       ('London'),
       ('New York');

INSERT Customer (FirstName, LastName, CityID, Password)
VALUES ('Malcolm', 'Rudhag', 2, 'valle'),
       ('Liliana', 'Montini', 1, 'lili'),
       ('Fredrik', 'Billing', 1, 'freddegrädde'),
       ('Terry', 'Crews', 4, 'oldspice'),
       ('Phoebe', 'Buffay', 4, 'smellycat'),
       ('Queen', 'Elizabeth', 3, 'immortal');

INSERT Category (Name)
VALUES ('Sandaler'),
       ('Klackskor'),
       ('Sneakers'),
       ('Tofflor'),
       ('Stövlar');

INSERT Brand (Name)
VALUES ('Gukki'),
       ('ECCO'),
       ('Mike'),
       ('Abibas'),
       ('Fuggs');

INSERT Shoe (Name, Size, Color, Price, BrandID, Quantity)
VALUES ('Footgasm', 38, 'SVART', 299, 2, 5),
       ('Snickers', 36, 'Lila', 899, 4, 5),
       ('Väldigt höga klackar', 38, 'Svart', 1699, 1, 5),
       ('AirMin', 43, 'Vita', 1299, 3, 5),
       ('Bekvämsko', 37, 'Brun', 199, 5, 5),
       ('Fotfetish', 42, 'Röd', 299, 2, 5),
       ('MahmudsFavoriter', 40, 'Svart', 299, 4, 5),
       ('Obekväma men snygga', 35, 'Röd', 2399, 3, 5);

INSERT Orders (CustomerID, Date)
VALUES (1, '2020-01-30'),
       (2, '2020-02-04'),
       (2, '2020-01-12'),
       (3, '2020-02-02'),
       (3, '2020-01-16'),
       (3, '2020-03-01'),
       (2, '2020-01-06'),
       (1, '2020-02-14'),
       (2, '2020-03-03'),
       (6, '2020-01-22'),
       (2, '2020-02-25'),
       (1, '2020-01-22'),
       (6, '2020-01-19'),
       (3, '2020-02-18'),
       (4, '2020-01-20'),
       (2, '2020-01-01');

INSERT Rating (Score, Comment, ShoeID, CustomerID)
VALUES (3, 'Dyra men sköna', 4, 2),
       (4, 'Inte tillräckling höga', 3, 2),
       (1, 'Jag älskar fötter', 6, 1),
       (1, 'POWWWEEEEEERR', 2, 4),
       (3, 'I Like Tea', 5, 6),
       (4, 'Smelly cat', 1, 5),
       (1, 'Dyra men sköna', 1, 2),
       (2, 'Inte tillräckling höga', 1, 2),
       (3, 'Jag älskar fötter', 6, 1),
       (4, 'POWWWEEEEEERR', 2, 4),
       (5, 'I Like Tea', 5, 6),
       (1, 'Smelly cat', 8, 5);


INSERT Shoe_Orders (ShoeID, OrdersID, Quantity, Completed)
VALUES (1, 1, 2, 1),
       (2, 2, 1, 1),
       (3, 3, 3, 1),
       (4, 4, 1, 1),
       (3, 5, 2, 1),
       (5, 6, 6, 1),
       (8, 7, 8, 1),
       (7, 8, 1, 1),
       (8, 9, 2, 1),
       (8, 10, 1, 1),
       (1, 11, 2, 1),
       (1, 12, 3, 1),
       (2, 13, 1, 1),
       (4, 14, 1, 1),
       (7, 15, 1, 1),
       (1, 16, 1, 1);

INSERT Shoe_Category (ShoeID, CategoryID)
VALUES (1, 1),
       (2, 2),
       (2, 1),
       (3, 3),
       (4, 2),
       (4, 1),
       (5, 4),
       (6, 5),
       (6, 2),
       (7, 2),
       (8, 3);


-- Jag indexerar shoe och customer för att det är förmodligen det datat som kommer att sökas igenom mest.
Create INDEX IX_customer on Customer (FirstName, LastName);
Create INDEX IX_shoe on Shoe (Name);



DROP PROCEDURE IF EXISTS addToCart;
DELIMITER //
CREATE PROCEDURE addToCart(thisCustomerID INT, thisShoeID INT, thisQuantity INT)

BEGIN
    DECLARE any_rows_found INT;
    DECLARE shoe_quantity INT;
    DECLARE exit HANDLER for SQLEXCEPTION
        BEGIN
            ROLLBACK;
            SELECT 'An unexpected error has occured! Rollback initiated.';
        END;

    SET AUTOCOMMIT = 0;

    START TRANSACTION;
    INSERT INTO orders (Date, CustomerID)
    VALUES (CURRENT_DATE, thisCustomerID);

    SELECT COUNT(*)
    INTO any_rows_found
    FROM orders
             JOIN Shoe_Orders
                  ON Orders.ID = Shoe_Orders.OrdersID
             JOIN Shoe
                  ON Shoe_Orders.ShoeID = Shoe.ID
    WHERE ShoeID = thisShoeID
      AND CustomerID = thisCustomerID
      AND Shoe_Orders.Completed = 0;

    SELECT shoe.Quantity
    INTO shoe_quantity
    FROM shoe
    WHERE shoe.ID = thisShoeID;

    IF (shoe_quantity >= thisQuantity)
    THEN
        IF (any_rows_found > 0)
        THEN
            UPDATE shoe_orders
                JOIN Orders
                ON Orders.ID = Shoe_Orders.OrdersID
                JOIN Shoe
                ON Shoe_Orders.ShoeID = Shoe.ID
            SET Shoe_Orders.Quantity = Shoe_Orders.Quantity + thisQuantity
            WHERE ShoeID = thisShoeID
              AND CustomerID = thisCustomerID;
        ELSE
            INSERT INTO shoe_orders (ShoeID, OrdersID, Quantity)
            VALUES (thisShoeID, (SELECT ID
                                 FROM orders
                                 WHERE CustomerID = thisCustomerID
                                   AND Date = CURRENT_DATE
                                 ORDER BY ID DESC
                                 LIMIT 1), thisQuantity);
        END IF;
        UPDATE shoe
        SET shoe.Quantity = shoe.Quantity - thisQuantity
        WHERE shoe.ID = thisShoeID;
        COMMIT;
        SET AUTOCOMMIT = 1;

    ELSE
        ROLLBACK;
        SELECT 'Out of stock';
        SET AUTOCOMMIT = 1;
    END IF;

END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER after_shoe_update
    AFTER UPDATE
    ON shoewebshop.Shoe
    FOR EACH ROW
BEGIN
    IF (new.Quantity < 1)
    THEN
        INSERT INTO Out_Of_Stock (ShoeID, Date)
        VALUES (old.ID, CURRENT_TIMESTAMP);
    END IF;
END //

DELIMITER ;

DELIMITER //

DROP FUNCTION IF EXISTS printShoeRating;
CREATE FUNCTION printShoeRating(thisShoeID INT)
    RETURNS DECIMAL(2, 1)

BEGIN
    DECLARE average_score DOUBLE;
    SELECT AVG(Score)
    INTO average_score
    FROM Rating
             JOIN Shoe ON Rating.ShoeID = Shoe.ID
    WHERE ShoeID = thisShoeID;
    RETURN average_score;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE rateShoe(thisShoeID INT, thisCustomerID INT, thisScore INT, thisComment varchar(255))

BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            SELECT 'Error occured, rollback initiated.';
        END;
    SET AUTOCOMMIT = 0;
    START TRANSACTION;
    INSERT INTO Rating (Comment, ShoeID, CustomerID, Score)
    VALUES (thisComment, thisShoeID, thisCustomerID, thisScore)
    ON DUPLICATE KEY
        UPDATE Rating.Score = thisScore AND rating.Comment = thisComment;
    COMMIT;
    SET AUTOCOMMIT = 1;
END //

DELIMITER ;

DELIMITER //

DROP PROCEDURE IF EXISTS viewCart;
CREATE PROCEDURE viewCart(thisCustomerID int)
BEGIN
    SELECT shoe.id, shoe.Name, shoe.Size, shoe.Color, shoe.BrandID, shoe.Price, shoe_orders.Quantity
    FROM shoewebshop.shoe
             JOIN Brand ON shoe.BrandID = brand.ID
             JOIN shoe_orders ON shoe.ID = Shoe_Orders.ShoeID
             JOIN orders ON Shoe_Orders.OrdersID = Orders.ID
    WHERE Orders.CustomerID = thisCustomerID
      AND shoe_orders.Completed = 0
    GROUP BY shoe.Name;
END
//

DELIMITER ;

DELIMITER //
DROP FUNCTION IF EXISTS shoeRatingToText;
CREATE FUNCTION shoeRatingToText(thisShoeID INT)
    RETURNS VARCHAR(40)

BEGIN
    DECLARE average_score INT;
    DECLARE shoe_rating VARCHAR(40);
    SELECT AVG(Score)
    INTO average_score
    FROM Rating
             JOIN Shoe ON Rating.ShoeID = thisShoeID;
    IF average_score >= 4
    THEN SET shoe_rating = 'Very Satisfied';
    ELSEIF average_score >= 3 AND average_score <= 4
    THEN SET shoe_rating = 'Satisfied';
    ELSEIF average_score >= 2 AND average_score <= 3
    THEN SET shoe_rating = 'A Little Satisfied';
    ELSEIF average_score >= 1 AND average_score <= 2
    THEN SET shoe_rating = 'Not Satisfied';
    ELSE SET shoe_rating = 'No Rating';
    END IF;
    RETURN shoe_rating;
END //

DELIMITER ;

DELIMITER //
CREATE VIEW View_allShoesAndRating
AS
SELECT Shoe.Name AS Name, AVG(Score) AS Average_Score, shoeRatingToText(shoe.ID) AS Text_Rating
FROM rating
         RIGHT JOIN Shoe ON rating.ShoeID = Shoe.ID
GROUP BY Name
ORDER BY Average_Score DESC;

DELIMITER ;
DELIMITER //