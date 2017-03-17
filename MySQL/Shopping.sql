#THE REUQIREMENTS FOR THE VIEWS ARE QUITE VAGEUE, I THOUGHT I SHOULD MAKE AN INNER JOIN IN ORDER TO INCLUDE ONLY NON-NULL VALUES
#BUT I USED A LEFT OUTER JOIN IN ORDER TO INCLUDE THE NULL VALUES IN THE CHILD TABLE ASWELL. 
CREATE TABLE Category(CategoryID INTEGER(6), 
			          Description VARCHAR(100),
					  PRIMARY KEY (CategoryID)
)ENGINE=INNODB;

CREATE TABLE Customer(CustomerID INTEGER(6), 
		      FirstName VARCHAR(20), 
                      LastName VARCHAR(20), 
		      Username VARCHAR(10),
	              UserPass VARCHAR(20),
                      Email VARCHAR(50),  
                      Postcode VARCHAR(20), 
                      HouseNo INTEGER(6),  
                      DOB DATE, 
		      REGISTRATION_DATE DATE,
		      PRIMARY KEY (CustomerID)
)ENGINE=INNODB;

CREATE TABLE Supplier(SupplierID INTEGER(6), 
		      Name VARCHAR(20), 
                      Postcode VARCHAR(20), 
		      HouseNo INTEGER(6), 
         	      Telephone BIGINT,
		      PRIMARY KEY(SupplierID)
)ENGINE=INNODB;

CREATE TABLE Rep(SupplierID INTEGER(6) UNIQUE NOT NULL,
	         FirstName VARCHAR(20),
		 LastName VARCHAR(20),				
                 Phone BIGINT,
		 Email VARCHAR(50),
                 MSN VARCHAR(50),
                 Skype VARCHAR(20),
		 Postcode VARCHAR(20), 
		 HouseNo INTEGER(6),
		 FOREIGN KEY(SupplierID) REFERENCES Supplier(SupplierID)
)ENGINE=INNODB;

CREATE TABLE Item(ItemID INTEGER(6),
                  ItemName VARCHAR(20),
		  Price FLOAT,
		  Description VARCHAR(100),
		  Image BLOB,
                  CategoryID INTEGER(6) NOT NULL,
                  SupplierID INTEGER(6) NOT NULL,
		  PRIMARY KEY(ItemID),
                  FOREIGN KEY(CategoryID) REFERENCES Category(CategoryID),
                  FOREIGN KEY(SupplierID) REFERENCES Supplier(SupplierID)
)ENGINE=INNODB;

CREATE TABLE Transact(CustomerID INTEGER(6),
		      ItemID INTEGER(6),
                      Qty INTEGER(6),
                      PRIMARY KEY(CustomerID, ItemID), FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID), FOREIGN KEY(ItemID) REFERENCES Item(ItemID) 
)ENGINE=INNODB;

CREATE TABLE Recommendations(ItemID INTEGER(6),
 		             Recommends INTEGER(6),
                             PRIMARY KEY(ItemID, Recommends), FOREIGN KEY(ItemID) REFERENCES Item(ItemID), FOREIGN KEY(Recommends) REFERENCES Item(ItemID)
)ENGINE=INNODB;

INSERT INTO Category(CategoryID, Description)
VALUES (1, 'Biscuits'),
       (2, 'Clothes'),
       (3, 'Meat'),
       (4, 'Sports'),
       (5, 'Juices'),
       (6, 'Tea');

INSERT INTO Supplier(SupplierID, Name, Postcode, HouseNo, Telephone)
VALUES (1, 'Biscuiteers', 'G86B2', 67, 76834234),
       (2, 'Yogurteers', 'G96B2', 66, 86664445),
       (3, 'Jaggs', 'J81ES', 54, 56664445),
       (4, 'Nikes', 'S82F3', 12, 26482445),
       (5, 'Crumbs', 'H85YX', 28, 78282545),
       (6, 'Edds', 'P82BO', 25, 78725945);

INSERT INTO Rep(SupplierID, FirstName, LastName, Phone, Email, MSN, Skype, Postcode, HouseNo)
VALUES (5, 'Chris', 'Angels', 181827457, 'chris.cool_guy@person.com', 'chris.cool_guy@msn.com', 'chris.cool_guy', 'YB18R', 90),
       (3, 'Michael', 'Adrian', 781457407, 'mike.cool_guy@person.com', 'mike.cool_guy@msn.com', 'mike.cool_guy', 'X8T6R', 30),
       (6, 'Sam', 'Tyson', 781003407, 'sam.cool_guy@person.com', 'sam.cool_guy@msn.com', 'sam.cool_guy', 'H8T6R', 36),
       (2, 'Ela', 'David', 781003407, 'ela.david@person.com', 'ela.david@msn.com', 'ela.david', 'E8Y5R', 89),
       (4, 'Craig', 'Gutag', 581908907, 'craig.cool_guy@person.com', 'craig.cool_guy@msn.com', 'craig.cool_guy', 'J1Y8U', 75);

INSERT INTO Customer(CustomerID, FirstName, LastName, Username, UserPass, Email, Postcode, HouseNo, DOB, REGISTRATION_DATE) 
VALUES (1, 'Ehsun', 'Hanif', 'ehsun', 'google12365', 'ehsun@person.com', 'K81US', 45, '1986-02-12', '2008-01-07'),
       (2, 'Aksa', 'Zaheer', 'aksa', '123aksa65', 'aksa@person.com', 'J81XS', 127, '1976-03-02', '2010-07-27'),
       (3, 'Mir', 'Kevin', 'kevin', 'k3\/1|\|', 'kevin@person.com', 'J82XV', 17, '1997-03-02', '2012-09-17'),
       (4, 'Matilda', 'Williams', 'williams','willywonka321', 'matilda@person.com', 'B82VG', 79, '1989-08-10', '2013-09-17'),
       (5, 'Jolly', 'Mahira', 'mahira', 'M/-\|-||RA', 'kevin@person.com', 'J82XV', 17, '1997-03-02', '2015-12-12'),
       (6, 'Shabana', 'Gul', 'shabbo', 'Sh/-\880', 'shabbo@person.com', 'E82GT', 24, '1995-03-01', '2016-01-12');

INSERT INTO Item(ItemID, ItemName, Price, Description, Image, CategoryID, SupplierID)
VALUES (1, 'Digestors', 16.99, 'Chocolate', 'DatabasePics/Biscuits.png', 1, 1),
       (2, 'Cream', 16.99, 'Vanilla', 'DatabasePics/Biscuits.png', 1, 1),
       (3, 'Jeans', 126.99, 'Denim', 'DatabasePics/Jeans.png', 2, 2),
       (4, 'Jumpy', 30.99, 'White', 'DatabasePics/Joggers.png', 4, 3),
       (5, 'Sausages', 12.99, 'Chicken', 'DatabasePics/Sausages.png', 3, 4),
       (6, 'Mango', 2.99, 'Milkshake', 'DatabasePics/Mango.png', 5, 5),
       (7, 'Thornton', 4.99, 'Teabags', 'DatabasePics/Teabag.png', 6, 6),
       (8, 'Jummydodgers', 1.49, 'Cupcake', 'DatabasePics/Biscuits.png', 6, 6),
       (9, 'Nykeis', 4.99, 'Springs', 'DatabasePics/Joggers.png', 4, 3);

INSERT INTO Recommendations(ItemID, Recommends)
VALUES (1, 2),
       (1, 8),
       (2, 1),
       (2, 8),
       (4, 9),
       (9, 4),
       (8, 1),
       (8, 2);

INSERT INTO Transact(CustomerID, ItemID, Qty)
VALUES (1, 1, 3),
       (1, 2, 4),
       (2, 3, 6),
       (2, 4, 1),
       (3, 5, 3),
       (4, 6, 7),
       (4, 7, 3),
       (5, 8, 2),
       (5, 9, 5);

#1
CREATE VIEW CustItem AS 
			SELECT Customer.CustomerID, Customer.FirstName, Customer.Lastname, Customer.Username, Customer.Userpass, Customer.Email, Customer.Postcode, Customer.HouseNo, 
			Customer.DOB, Transact.Qty, Item.ItemID, Item.ItemName, Item.Price, Item.Description, Item.Image, Item.CategoryID, Item. SupplierID
			FROM Customer
			LEFT OUTER JOIN Transact ON Customer.CustomerID = Transact.CustomerID
			LEFT OUTER JOIN Item ON Item.ItemID = Transact.ItemID;
#SELECT * FROM CustItem;
#2
CREATE VIEW SupRep AS
			SELECT Supplier.SupplierID, Supplier.Name, Supplier.Postcode, Supplier.HouseNo, Supplier.Telephone, 
			Rep.FirstName AS RepFirstName, Rep.LastName AS RepLastName, Rep.Phone, Rep.Email, Rep.MSN, Rep.Skype, Rep.Postcode AS RepPostCode, Rep.HouseNo AS RepHouseNo
			FROM Supplier LEFT OUTER JOIN Rep ON Supplier.SupplierID = Rep.SupplierID;
#SELECT * FROM SupRep;
#3
CREATE VIEW ItemRec AS
		    SELECT Item1.ItemID, Item1.ItemName, Item1.Price, Item1.Description, Item1.Image, Item1.CategoryID, Item1. SupplierID, Recommendations.Recommends,  
		    Item2.ItemName AS RecommendName, Item2.Price AS RecommendPrice, Item2.Description AS RecommendDescript, Item2.Image AS RecommendImage, 
			Item2.CategoryID AS RecommendCategory, Item2.SupplierID AS RecommendSupplier
			FROM Item AS Item1 LEFT OUTER JOIN Recommendations ON Item1.ItemID = Recommendations.ItemID 
			LEFT OUTER JOIN Item as Item2 ON Item2.ItemID = Recommendations.Recommends;
#SELECT * FROM ItemRec;
