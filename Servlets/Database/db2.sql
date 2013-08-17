-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 10, 2012 at 09:55 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `nis`
--

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE IF NOT EXISTS `album` (
  `albid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`albid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`albid`, `name`) VALUES
(9, 'Hybrit Theory'),
(8, 'As I Am'),
(10, 'Meteora'),
(11, 'Strip Me'),
(12, 'Loud'),
(13, 'Talk That Talk');

-- --------------------------------------------------------

--
-- Table structure for table `artist`
--

CREATE TABLE IF NOT EXISTS `artist` (
  `artid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`artid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `artist`
--

INSERT INTO `artist` (`artid`, `name`) VALUES
(11, 'Lady Gaga'),
(10, 'Alicia Keys'),
(12, 'Linkin Park'),
(13, 'Rihanna'),
(14, 'Natascha Bedingfield');

-- --------------------------------------------------------

--
-- Table structure for table `attribute`
--

CREATE TABLE IF NOT EXISTS `attribute` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `yid` int(11) NOT NULL,
  `artid` int(11) NOT NULL,
  `albid` int(11) NOT NULL,
  `length` float NOT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `attribute`
--

INSERT INTO `attribute` (`aid`, `gid`, `yid`, `artid`, `albid`, `length`) VALUES
(18, 9, 11, 10, 8, 4),
(17, 8, 10, 12, 9, 5),
(16, 7, 9, 12, 10, 3),
(15, 6, 8, 14, 11, 2),
(14, 6, 7, 13, 12, 4),
(13, 5, 6, 13, 13, 3);

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `cityid` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cityid`),
  UNIQUE KEY `city` (`city`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`cityid`, `city`) VALUES
(14, 'Innsbruck'),
(13, 'Wien'),
(12, 'Graz'),
(15, 'Linz');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cityid` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cid`, `cityid`, `name`) VALUES
(12, 15, 'Sebastian'),
(11, 14, 'Badhan'),
(10, 13, 'Muesluem'),
(9, 12, 'Robin'),
(13, 12, 'Manuel');

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`gid`),
  UNIQUE KEY `genre` (`genre`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`gid`, `genre`) VALUES
(8, 'House'),
(7, 'Rap'),
(6, 'Rock'),
(5, 'Pop'),
(9, 'Classic');

-- --------------------------------------------------------

--
-- Table structure for table `music`
--

CREATE TABLE IF NOT EXISTS `music` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `title` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `music`
--

INSERT INTO `music` (`mid`, `aid`, `title`) VALUES
(18, 18, 'As I Am Intro'),
(17, 17, 'With you'),
(16, 16, 'Numb'),
(15, 15, 'Touch'),
(14, 14, 'Cheers'),
(13, 13, 'Farewell');

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE IF NOT EXISTS `rating` (
  `cid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  `rating` float NOT NULL,
  PRIMARY KEY (`cid`,`mid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`cid`, `mid`, `rating`) VALUES
(9, 16, 10),
(13, 17, 4.5),
(10, 14, 9.5),
(11, 13, 5),
(12, 16, 2),
(9, 18, 8.5);

-- --------------------------------------------------------

--
-- Table structure for table `year`
--

CREATE TABLE IF NOT EXISTS `year` (
  `yid` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`yid`),
  UNIQUE KEY `year` (`year`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `year`
--

INSERT INTO `year` (`yid`, `year`) VALUES
(9, 2007),
(8, 2009),
(7, 2010),
(6, 2011),
(10, 2008),
(11, 2006);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
