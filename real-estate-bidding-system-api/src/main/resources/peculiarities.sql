/*CREATE TABLE IF NOT EXISTS `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (name)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=256 ;

--
-- Dumping data for table `cities`
--*/

INSERT INTO `peculiarities` (`id`, `name`) VALUES
(1, 'Panel'),
(2, 'Brick'),
(3, 'LFS'),
(4, 'CFW'),
(5, 'Elevator'),
(6, 'Pool'),
(7, 'AirCool'),
(8, 'Garage'),
(9, 'In Build'),
(10, 'Trimmer Joists')