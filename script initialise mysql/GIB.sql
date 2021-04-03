CREATE DATABASE IF NOT EXISTS gib COLLATE `latin1_general_cs`;

USE gib;

DROP TABLE IF EXISTS `order_products`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `inventory`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `products`;


CREATE TABLE `customers` (
  `fore_name` varchar(255),
  `last_name` varchar(255),
  `address` varchar(255),
  `postal_code` varchar(255),
  `email` varchar(255),
  `credit_limit` int,
  `gender` varchar(255),
  `date_of_birth` datetime,
  PRIMARY KEY (`fore_name`, `last_name`)
)ENGINE = InnoDB;

CREATE TABLE `products` (
  `name` varchar(255),
  `code` varchar(255) PRIMARY KEY,
  `selling_price` float,
  `information` varchar(255)
)ENGINE = InnoDB;

CREATE TABLE `inventory` (
  `product_code` varchar(255) PRIMARY KEY,
  `stock_level` int,
  CONSTRAINT FK_Inventory_ProductCode FOREIGN KEY (`product_code`) REFERENCES `products` (`code`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
)ENGINE = InnoDB;

CREATE TABLE `orders` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `fore_name` varchar(255),
  `last_name` varchar(255),
  `transaction_date` datetime,
  `total_price` float,
  CONSTRAINT FK_Name FOREIGN KEY (`fore_name`, `last_name`) REFERENCES `customers` (`fore_name`, `last_name`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
)ENGINE = InnoDB;

CREATE TABLE `order_products` (
  `order_id` int,
  `product_code` varchar(255),
  `quantity` int,
  PRIMARY KEY (`order_id`, `product_code`),
  CONSTRAINT FK_OrderId FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT FK_OrderProducts_ProductCode FOREIGN KEY (`product_code`) REFERENCES `products` (`code`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
)ENGINE = InnoDB;


INSERT INTO `customers` (`fore_name`, `last_name`, `address`, `postal_code`, `email`, `credit_limit`, `gender`, `date_of_birth`) VALUES ('John', 'Doe', '12th avenue', '36150', 'joe.doe@gmail.com', '1000', 'M', '1992-04-17 09:05:18');
INSERT INTO `customers` (`fore_name`, `last_name`, `address`, `postal_code`, `email`, `credit_limit`, `gender`, `date_of_birth`) VALUES ('Jane', 'Shepard', 'Lincoln street, 57', '65980', 'j.shepard@icloud.com', '500', 'F', '1995-06-22 19:52:54');
INSERT INTO `customers` (`fore_name`, `last_name`, `address`, `postal_code`, `email`, `credit_limit`, `gender`, `date_of_birth`) VALUES ('David', 'Miles', '1921 Main St', 'AR 72114', 'David.Miles@yahoo.com', '800', 'M', '1985-01-02 05:09:38');
INSERT INTO `customers` (`fore_name`, `last_name`, `address`, `postal_code`, `email`, `credit_limit`, `gender`, `date_of_birth`) VALUES ('Robert', 'Balboa', 'East Tusculum Street', '65980', 'theitalianstalion@phil.gov', '75', 'M', '1955-01-02 00:00:00');

INSERT INTO `products` (`name`, `code`, `selling_price`, `information`) VALUES ('Light', 'LIG000', '42.95', 'Front light for bicycle');
INSERT INTO `inventory` (`product_code`, `stock_level`) VALUES ('LIG000', '21');
INSERT INTO `products` (`name`, `code`, `selling_price`, `information`) VALUES ('Navigation', 'NAV000', '230', 'GPS navigation utility');
INSERT INTO `inventory` (`product_code`, `stock_level`) VALUES ('NAV000', '3');
INSERT INTO `products` (`name`, `code`, `selling_price`, `information`) VALUES ('Glasses', 'GLA000', '99.99', 'Glasses for high intensity cycling');
INSERT INTO `inventory` (`product_code`, `stock_level`) VALUES ('GLA000', '68');
INSERT INTO `products` (`name`, `code`, `selling_price`, `information`) VALUES ('Shoes', 'SHO000', '79.99', 'Hicking shoes');
INSERT INTO `inventory` (`product_code`, `stock_level`) VALUES ('SHO000', '47');
INSERT INTO `products` (`name`, `code`, `selling_price`, `information`) VALUES ('Bag', 'BAG000', '25', 'Travelling bag');
INSERT INTO `inventory` (`product_code`, `stock_level`) VALUES ('BAG000', '52');
INSERT INTO `products` (`name`, `code`, `selling_price`, `information`) VALUES ('Bike', 'BIK000', '399.99', 'Bike for casual use');
INSERT INTO `inventory` (`product_code`, `stock_level`) VALUES ('BIK000', '12');
