-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 21-11-2018 a las 13:10:29
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `test`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`` PROCEDURE `AddGeometryColumn` (`catalog` VARCHAR(64), `t_schema` VARCHAR(64), `t_name` VARCHAR(64), `geometry_column` VARCHAR(64), `t_srid` INT)  begin
  set @qwe= concat('ALTER TABLE ', t_schema, '.', t_name, ' ADD ', geometry_column,' GEOMETRY REF_SYSTEM_ID=', t_srid); PREPARE ls from @qwe; execute ls; deallocate prepare ls; end$$

CREATE DEFINER=`` PROCEDURE `DropGeometryColumn` (`catalog` VARCHAR(64), `t_schema` VARCHAR(64), `t_name` VARCHAR(64), `geometry_column` VARCHAR(64))  begin
  set @qwe= concat('ALTER TABLE ', t_schema, '.', t_name, ' DROP ', geometry_column); PREPARE ls from @qwe; execute ls; deallocate prepare ls; end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `route`
--

CREATE TABLE `route` (
  `id` int(11) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 NOT NULL,
  `origin` varchar(150) COLLATE utf8mb4_bin NOT NULL,
  `originAddress` varchar(150) COLLATE utf8mb4_bin NOT NULL,
  `nameAddress1` varchar(100) CHARACTER SET utf8 NOT NULL,
  `address1` varchar(150) CHARACTER SET utf8 NOT NULL,
  `nameAddress2` varchar(100) CHARACTER SET utf8 NOT NULL,
  `address2` varchar(150) CHARACTER SET utf8 NOT NULL,
  `nameAddress3` varchar(100) CHARACTER SET utf8 NOT NULL,
  `address3` varchar(150) CHARACTER SET utf8 NOT NULL,
  `nameAddress4` varchar(100) CHARACTER SET utf8 NOT NULL,
  `address4` varchar(150) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `route`
--

INSERT INTO `route` (`id`, `username`, `origin`, `originAddress`, `nameAddress1`, `address1`, `nameAddress2`, `address2`, `nameAddress3`, `address3`, `nameAddress4`, `address4`) VALUES
(12, 'alvaro', 'Hong Kong, 九龍塘達之路83號學術樓 (三)', 'Hong Kong, 九龍塘達之路83號學術樓 (三)', 'One Dim Sum (一點心)', 'Kenwood Mansion, 15 Playing Field Rd, Prince Edward, Hong Kong', 'Urban Coffee Roaster', 'Hong Mei Building, 135 Lai Chi Kok Rd, Tai Kok Tsui, Hong Kong', 'Lei Cheng Uk Han Tomb Museum (李鄭屋漢墓博物館)', 'Li Cheung Uk Tomb, Tonkin St, Sham Shui Po, Hong Kong', 'T. A. P. - The Ale Project', 'G/F, 15 Hak Po Street, Mong Kok, Hong Kong'),
(15, 'alvaro', 'Hong Kong, 九龍塘達之路83號學術樓 (三)', 'Hong Kong, 九龍塘達之路83號學術樓 (三)', 'Garden Hill (嘉頓山)', 'Hong Kong, 白田喃嘸山', 'Café Golden (琉金穗月)', 'Room 05, 1/F, Jockey Creative Arts Centre, 30 Pak Tin Street, Shek Kip Mei, Hong Kong', 'Café Golden (琉金穗月)', 'Room 05, 1/F, Jockey Creative Arts Centre, 30 Pak Tin Street, Shek Kip Mei, Hong Kong', 'Jockey Club Creative Arts Centre (賽馬會創意藝術中心)', 'Jockey Club Creative Arts Centre, 30 Pak Tin St, Shek Kip Mei, Hong Kong'),
(18, 'alvaro', 'City University of Hong Kong To Yuen Building, 31 To Yuen St, Kowloon Tong, Hong Kong', 'City University of Hong Kong To Yuen Building, 31 To Yuen St, Kowloon Tong, Hong Kong', 'Café Golden (琉金穗月)', 'Room 05, 1/F, Jockey Creative Arts Centre, 30 Pak Tin Street, Shek Kip Mei, Hong Kong', 'Kowloon Walled City Park (九龍寨城公園)', 'Kowloon Walled City Park, Kowloon City, Hong Kong', 'Mongkok Flower Market (旺角花墟)', 'Flower Market Rd, Prince Edward, Hong Kong', 'Craft Coffee Roaster', '29 Tai Kok Tsui Rd, Tai Kok Tsui, Hong Kong'),
(19, 'alvaro', 'City University of Hong Kong To Yuen Building, 31 To Yuen St, Kowloon Tong, Hong Kong', 'City University of Hong Kong To Yuen Building, 31 To Yuen St, Kowloon Tong, Hong Kong', 'Festival Walk (又一城)', '80 Tat Chee Ave, Kowloon Tong, Hong Kong', 'Café Golden (琉金穗月)', 'Room 05, 1/F, Jockey Creative Arts Centre, 30 Pak Tin Street, Shek Kip Mei, Hong Kong', 'Jockey Club Creative Arts Centre (賽馬會創意藝術中心)', 'Jockey Club Creative Arts Centre, Pak Tin St, Shek Kip Mei, Hong Kong', 'Tim Ho Wan (添好運)', '9-11 Fuk Wing St, Sham Shui Po, Hong Kong');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `username` varchar(50) CHARACTER SET latin1 NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('alvaro', '$2a$10$/2E2CODpgvkcDdnHHzX1sOlxcXrY9JN9Vt7N3yW2fKSHUPo3F.UF6', 1),
('peter', '$2a$10$/2E2CODpgvkcDdnHHzX1sOlxcXrY9JN9Vt7N3yW2fKSHUPo3F.UF6', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_roles`
--

CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL,
  `username` varchar(20) CHARACTER SET latin1 NOT NULL,
  `role` varchar(20) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `username`, `role`) VALUES
(2, 'alvaro', 'ROLE_ADMIN'),
(1, 'alvaro', 'ROLE_USER'),
(3, 'peter', 'ROLE_USER');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD KEY `username` (`username`);

--
-- Indices de la tabla `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_role_id`),
  ADD UNIQUE KEY `uni_username_role` (`role`,`username`),
  ADD KEY `fk_username_idx` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `route`
--
ALTER TABLE `route`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
