-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 01, 2021 at 05:35 PM
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
-- Database: `boardgames`
--

-- --------------------------------------------------------

--
-- Table structure for table `boardgame`
--

DROP TABLE IF EXISTS `boardgame`;
CREATE TABLE IF NOT EXISTS `boardgame` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf32_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `category` int(255) NOT NULL,
  `numberofsold` int(255) NOT NULL,
  `availability` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Dumping data for table `boardgame`
--

INSERT INTO `boardgame` (`id`, `name`, `price`, `category`, `numberofsold`, `availability`) VALUES
(31, 'Carcassonne', 2200, 4, 2, 1),
(22, 'Munchkins', 3500, 2, 25, 0),
(32, 'Code Names', 2400, 1, 15, 1),
(33, 'Dixit', 2800, 1, 8, 1),
(30, 'Catan', 4400, 1, 10, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
