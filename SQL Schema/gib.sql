-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 13, 2021 at 07:58 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gib`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `fore_name` varchar(255) COLLATE latin1_general_cs NOT NULL,
  `last_name` varchar(255) COLLATE latin1_general_cs NOT NULL,
  `address` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  `postal_code` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  `email` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  `credit_limit` int(11) DEFAULT NULL,
  `gender` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  PRIMARY KEY (`fore_name`,`last_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`fore_name`, `last_name`, `address`, `postal_code`, `email`, `credit_limit`, `gender`, `date_of_birth`) VALUES
('David', 'Miles', '1921 Main St', 'AR 72114', 'David.Miles@yahoo.com', 800, 'M', '1985-01-02 05:09:38'),
('Jane', 'Shepard', 'Lincoln street, 57', '65980', 'j.shepard@icloud.com', 500, 'F', '1995-06-22 19:52:54'),
('John', 'Doe', '12th avenue', '36150', 'joe.doe@gmail.com', 875, 'M', '1992-04-17 09:05:18'),
('Robert', 'Balboa', 'East Tusculum Street', '65980', 'theitalianstalion@phil.gov', 75, 'M', '1955-01-02 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE IF NOT EXISTS `inventory` (
  `product_code` varchar(255) COLLATE latin1_general_cs NOT NULL,
  `stock_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`product_code`, `stock_level`) VALUES
('BAG000', 51),
('BIK000', 12),
('GLA000', 67),
('LIG000', 21),
('NAV000', 3),
('SHO000', 47);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fore_name` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  `last_name` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  `transaction_date` datetime DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Name` (`fore_name`,`last_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `fore_name`, `last_name`, `transaction_date`, `total_price`) VALUES
(1, 'John', 'Doe', '2021-04-03 00:00:00', 124.99);

-- --------------------------------------------------------

--
-- Table structure for table `order_products`
--

DROP TABLE IF EXISTS `order_products`;
CREATE TABLE IF NOT EXISTS `order_products` (
  `order_id` int(11) NOT NULL,
  `product_code` varchar(255) COLLATE latin1_general_cs NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_code`),
  KEY `FK_OrderProducts_ProductCode` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `order_products`
--

INSERT INTO `order_products` (`order_id`, `product_code`, `quantity`) VALUES
(1, 'BAG000', 1),
(1, 'BIK000', 0),
(1, 'GLA000', 1),
(1, 'LIG000', 0),
(1, 'NAV000', 0),
(1, 'SHO000', 0);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `name` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  `code` varchar(255) COLLATE latin1_general_cs NOT NULL,
  `selling_price` float DEFAULT NULL,
  `information` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`name`, `code`, `selling_price`, `information`) VALUES
('Bag', 'BAG000', 25, 'Travelling bag'),
('Bike', 'BIK000', 399.99, 'Bike for casual use'),
('Glasses', 'GLA000', 99.99, 'Glasses for high intensity cycling'),
('Light', 'LIG000', 42.95, 'Front light for bicycle'),
('Navigation', 'NAV000', 230, 'GPS navigation utility'),
('Shoes', 'SHO000', 79.99, 'Hicking shoes');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `FK_Inventory_ProductCode` FOREIGN KEY (`product_code`) REFERENCES `products` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_Name` FOREIGN KEY (`fore_name`,`last_name`) REFERENCES `customers` (`fore_name`, `last_name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `order_products`
--
ALTER TABLE `order_products`
  ADD CONSTRAINT `FK_OrderId` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_OrderProducts_ProductCode` FOREIGN KEY (`product_code`) REFERENCES `products` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
