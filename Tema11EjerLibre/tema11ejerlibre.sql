-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-06-2020 a las 22:00:22
-- Versión del servidor: 10.1.32-MariaDB
-- Versión de PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tema11ejerlibre`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `ID_TRANSACCION` int(11) NOT NULL,
  `ID_PRODUCTO` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`ID_TRANSACCION`, `ID_PRODUCTO`, `CANTIDAD`) VALUES
(4, 2, 1),
(4, 4, 23),
(29, 2, 1),
(33, 2, 1),
(34, 5, 21),
(34, 11, 11),
(35, 2, 1),
(36, 2, 12),
(36, 4, 12),
(36, 6, 32),
(37, 6, 12),
(37, 7, 23),
(37, 20, 12),
(37, 21, 1),
(38, 3, 122),
(38, 4, 32),
(38, 8, 12),
(38, 11, 12),
(39, 3, 1),
(40, 2, 1),
(42, 3, 12),
(42, 11, 12),
(43, 2, 2),
(45, 5, 12),
(45, 10, 123);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ID_PROD` int(11) NOT NULL,
  `NOMBRE_PROD` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `DESC_PROD` varchar(300) COLLATE latin1_general_cs NOT NULL,
  `PRECIO` decimal(10,0) NOT NULL,
  `STOCK` int(11) NOT NULL,
  `ID_DUENIO` varchar(20) COLLATE latin1_general_cs NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ID_PROD`, `NOMBRE_PROD`, `DESC_PROD`, `PRECIO`, `STOCK`, `ID_DUENIO`) VALUES
(1, 'PROD 1', 'Este es el producto de prueba 1, jnklgs djfljfsdlk fjksdjfsdlk jfdsklfsdj', '100', 0, '0'),
(2, 'PRODUCTO 2', 'Este es el producto de prueba 2, jnklgs djfljfsdlk fjksdjfsdlk jfdsklfsdj', '100', 1, '0'),
(3, 'prod 3', 'asdasdasdasdas asddas d sasda sdasd asdasd', '200', 50, 'IKER'),
(4, 'prod 4', 'asdasdasdasdas asddas d sasda sdasd asdasd', '200', 125, 'IKER'),
(5, 'prod 5', 'asdasdasdasdas asddas d sasda sdasd asdasd', '200', 150, 'IKER'),
(6, 'prod 6', 'asdasdasdasdas asddas d sasda sdasd asdasd', '200', 33, 'IKER'),
(7, 'prod 7', 'asdasdasdasdas asddas d sasda sdasd asdasd', '200', 164, 'IKER'),
(8, 'prod 8', 'asdasdasdasdas asddas d sasda sdasd asdasd', '200', 188, 'IKER'),
(9, 'prod 9', 'asdasdasdasdas asddas d sasda sdasd asdasd', '200', 199, 'IKER'),
(10, 'prod 10', 'asdasdasdasdas asddas d sasda sdasd asdasd', '200', 77, 'IKER'),
(11, 'PROD 11', 'desc adsdasd asd saa asd das', '15', 943, 'IKER'),
(12, 'PROD 12', 'desc adsdasd asd saa asd das', '15', 988, 'IKER'),
(13, 'PROD 13', 'desc adsdasd asd saa asd das', '15', 1000, 'IKER'),
(14, 'PROD 14', 'desc adsdasd asd saa asd das', '15', 1000, 'IKER'),
(15, 'PROD 15', 'desc adsdasd asd saa asd das', '15', 1000, 'IKER'),
(16, 'PROD 16', 'desc adsdasd asd saa asd das', '15', 999, 'IKER'),
(17, 'PROD 17', 'desc adsdasd asd saa asd das', '15', 1000, 'IKER'),
(18, 'PROD 18', 'desc adsdasd asd saa asd das', '15', 1000, 'IKER'),
(19, 'PROD 19', 'desc adsdasd asd saa asd das', '15', 1000, 'IKER'),
(20, 'PROD 20', 'desc adsdasd asd saa asd das', '15', 968, 'IKER'),
(21, 'PROD 21', 'desc adsdasd asd saa asd das', '15', 999, 'IKER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `ID_TRANSACCION` int(11) NOT NULL,
  `ID_USUARIO` varchar(20) COLLATE latin1_general_cs NOT NULL,
  `FECHA` varchar(20) COLLATE latin1_general_cs DEFAULT NULL,
  `FINALIZADA` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Volcado de datos para la tabla `transacciones`
--

INSERT INTO `transacciones` (`ID_TRANSACCION`, `ID_USUARIO`, `FECHA`, `FINALIZADA`) VALUES
(4, 'b', '2020-06-07 18:36:56', 1),
(29, 'd', NULL, 0),
(33, 'c', '2020-06-07 16:30:32', 1),
(34, 'c', '2020-06-07 16:31:49', 1),
(35, 'c', '2020-06-07 16:38:58', 1),
(36, 'c', '2020-06-07 18:58:47', 1),
(37, 'b', '2020-06-07 19:06:34', 1),
(38, 'IKER', '2020-06-07 19:08:56', 1),
(39, 'c', '2020-06-07 19:11:54', 1),
(40, 'c', '2020-06-07 20:33:32', 1),
(42, 'c', '2020-06-07 21:24:31', 1),
(43, 'c', '2020-06-07 21:47:23', 1),
(45, 'a', '2020-06-07 21:53:30', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `USUARIO` varchar(20) COLLATE latin1_general_cs NOT NULL,
  `NOMBRE` varchar(30) COLLATE latin1_general_cs NOT NULL,
  `APELLIDOS` varchar(60) COLLATE latin1_general_cs NOT NULL,
  `FECHA_ALTA` date NOT NULL,
  `PASSWORD` varchar(20) COLLATE latin1_general_cs NOT NULL,
  `TIPO` enum('ADMIN','USER') COLLATE latin1_general_cs NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`USUARIO`, `NOMBRE`, `APELLIDOS`, `FECHA_ALTA`, `PASSWORD`, `TIPO`) VALUES
('a', 'a', 'a', '2020-06-07', 'a', 'USER'),
('b', 'b', 'b', '2020-05-27', 'b', 'USER'),
('c', 'c', 'c', '2020-05-30', 'c', 'USER'),
('d', 'd', 'd', '2020-06-04', 'd', 'USER'),
('IKER', 'IKER', 'NAVARRO', '2020-06-07', '1234', 'USER'),
('z', 'z', 'z', '2020-06-07', 'z', 'USER');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`ID_TRANSACCION`,`ID_PRODUCTO`) USING BTREE;

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ID_PROD`);

--
-- Indices de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD PRIMARY KEY (`ID_TRANSACCION`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`USUARIO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `ID_PROD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  MODIFY `ID_TRANSACCION` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
