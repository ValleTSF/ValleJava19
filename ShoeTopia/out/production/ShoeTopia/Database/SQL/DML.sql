--Lista antalet produkter per kategori. Listningen ska innehålla kategori-namn och antalet produkter--

SELECT c.Name AS Name,
COUNT(sc.ShoeID) AS Quantity
FROM category c
JOIN shoe_category sc ON c.ID = sc.CategoryID
JOIN shoe s ON s.ID = sc.ShoeID
GROUP BY Name
ORDER BY Quantity DESC;

--Skapa en kundlista med den totala summan pengar som varje kund har handlat för. Kundens för-och efternamn, samt det totala värdet som varje personhar shoppats för, skall visas--

SELECT
    c.FirstName AS First_Name,
    c.LastName AS Last_Name,
    SUM(s.Price * so.Quantity) AS Total_Spent
FROM customer c
         JOIN orders o ON c.ID = o.CustomerID
         JOIN shoe s ON o.ShoeID = s.ID
JOIN shoe_orders so on o.ID = so.OrdersID
GROUP BY c.FirstName, c.LastName
ORDER BY Total_Spent DESC;


--Vilka kunder har köpt svarta sandaler i storlek 38 av märket Ecco? Lista deras namn.--

SELECT
    c.FirstName AS First_Name,
    c.LastName AS Last_Name,
    c2.Name AS Category,
    b.Name AS Brand,
    s.Name AS Model,
    s.Size AS Size,
    s.Color AS Color
FROM customer c
         JOIN orders o ON o.CustomerID = c.ID
         JOIN shoe_orders so ON o.ShoeID = so.ShoeID
         JOIN shoe s ON o.ShoeID = s.ID
         JOIN brand b ON s.BrandID = b.ID
         JOIN shoe_category sc ON o.ShoeID = sc.ShoeID
         JOIN category c2 ON sc.CategoryID = c2.ID

WHERE b.name LIKE 'ECCO' AND s.Size LIKE '38%' AND s.Color LIKE 'Svart'
GROUP BY c.ID;

--Skriv ut en lista på det totala beställnings värdet per ort där beställnings värdet är större än 1000 kr.
-- Ortnamn och värde ska visas.(det måste finnas orter i databasen där det har handlats för mindre än 1000 kr
-- för att visa att frågan är korrekt formulerad

SELECT
    c2.Name AS City,
    SUM(s.Price * so.Quantity) AS Total_Spent
FROM customer c
         JOIN orders o ON c.ID = o.CustomerID
         JOIN shoe_orders so ON o.ShoeID = so.ShoeID
         JOIN city c2 ON c.CityID = c2.ID
         JOIN shoe s ON o.ShoeID = s.ID
GROUP BY c2.Name
HAVING Total_Spent >= 4000
ORDER BY Total_Spent DESC;



--Skapa en topp-5 lista av de mest sålda produkterna. --

SELECT
    b.Name AS Brand, s.Name AS Model, s.ID AS ID,
    SUM(so.Quantity) AS Quantity
FROM customer c
         JOIN orders o ON c.ID = o.CustomerID
         JOIN shoe_orders so ON o.ID = so.OrdersID
         JOIN shoe s ON so.ShoeID = s.ID
         JOIN brand b ON s.BrandID = b.ID
GROUP BY so.ShoeID
ORDER BY Quantity DESC
LIMIT 5;
--Vilken månad hade du den största försäljningen?
-- (det måste finnas data som anger försäljning för mer än en månad i databasenför att visa att frågan är korrekt formulerad)--

SELECT
    {FN MONTHNAME(o.Date)} AS Month,
       EXTRACT(YEAR from o.Date) AS Year,
       SUM(s.Price * o.Quantity) AS Sold
FROM customer c
JOIN orders o ON c.ID = o.CustomerID
JOIN shoe s on o.ShoeID = s.ID
JOIN shoe_orders so on o.ShoeID = so.ShoeID
JOIN brand b on s.BrandID = b.ID
GROUP BY Month;

-- Create procedure addtocart
DELIMITER //
CREATE PROCEDURE AddToCart (thisCustomerID INT, thisOrdersID INT, thisShoeID INT, thisQuantity INT)
BEGIN
IF thisOrdersID IS NULL
    THEN
    INSERT INTO orders (Date, CustomerID)
    VALUES (CURRENT_TIMESTAMP, thisCustomerID);
    INSERT INTO shoe_orders (ShoeID, OrdersID, Quantity)
    VALUES (thisShoeID, thisOrdersID, thisQuantity);
    END IF;
END //