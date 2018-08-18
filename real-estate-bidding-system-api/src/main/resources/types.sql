CREATE TABLE IF NOT EXISTS `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
  PRIMARY KEY (`id`),
  UNIQUE (type_name)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=256 ;

--
-- Dumping data for table `types`
--

INSERT INTO `types` (`id`, `type_name`) VALUES
(1, 'One Room Apartment'),
(2, 'Two Room Apartment'),
(3, 'Three Room Apartment'),
(4, 'Four+ Room Apartment'),
(5, 'House'),
(6, 'Floor of House'),
(7, 'Village'),
(8, 'Office'),
(9, 'Shop'),
(10, 'Hotel'),
(11, 'Place'),
(12, 'Business'),
(13, 'Warehouse'),
(14, 'Garage'),
(15, 'Land'),
(16, 'Other')