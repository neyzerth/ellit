-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-07-2025 a las 19:41:20
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ellit`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `id` int(11) NOT NULL,
  `usuarioId` int(11) NOT NULL,
  `texto` text NOT NULL,
  `calificacion` tinyint(4) NOT NULL CHECK (`calificacion` between 1 and 5),
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`id`, `usuarioId`, `texto`, `calificacion`, `fecha`) VALUES
(1, 3, 'Excelente plataforma, las rutinas son muy completas y fáciles de seguir', 5, '2025-07-30 17:29:22'),
(2, 4, 'Buen servicio, pero podría mejorar la variedad de productos', 4, '2025-07-30 17:29:22'),
(3, 5, 'Los entrenadores son muy profesionales y las instalaciones impecables', 5, '2025-07-30 17:29:22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

CREATE TABLE `detalle_ventas` (
  `idDetalle` int(11) NOT NULL,
  `idVenta` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioUnitario` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejercicios_rutina`
--

CREATE TABLE `ejercicios_rutina` (
  `id` int(11) NOT NULL,
  `rutinaId` int(11) NOT NULL,
  `diaSemana` enum('Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo') NOT NULL,
  `nombreEjercicio` varchar(100) NOT NULL,
  `series` smallint(6) NOT NULL,
  `repeticiones` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ejercicios_rutina`
--

INSERT INTO `ejercicios_rutina` (`id`, `rutinaId`, `diaSemana`, `nombreEjercicio`, `series`, `repeticiones`) VALUES
(1, 6, 'Lunes', 'Press de banca', 5, 5),
(2, 6, 'Lunes', 'Press inclinado con barra', 4, 6),
(3, 6, 'Lunes', 'Fondos en paralelas con peso', 3, 8),
(4, 6, 'Miércoles', 'Sentadillas con barra', 5, 5),
(5, 6, 'Miércoles', 'Peso muerto convencional', 4, 5),
(6, 6, 'Miércoles', 'Prensa de piernas', 4, 8),
(7, 6, 'Viernes', 'Dominadas pronas', 4, 6),
(8, 6, 'Viernes', 'Remo con barra', 4, 6),
(9, 6, 'Viernes', 'Face pulls', 3, 12),
(10, 6, 'Sábado', 'Press militar', 5, 5),
(11, 6, 'Sábado', 'Elevaciones laterales', 4, 10),
(12, 6, 'Sábado', 'Encogimientos con barra', 3, 12),
(13, 7, 'Lunes', 'Sentadillas búlgaras', 4, 10),
(14, 7, 'Lunes', 'Prensa inclinada', 3, 12),
(15, 7, 'Lunes', 'Curl femoral', 3, 12),
(16, 7, 'Martes', 'Press banca con mancuernas', 4, 10),
(17, 7, 'Martes', 'Aperturas con mancuernas', 3, 12),
(18, 7, 'Martes', 'Fondos en máquina', 3, 12),
(19, 7, 'Miércoles', 'Remo con cable', 4, 10),
(20, 7, 'Miércoles', 'Jalón al pecho', 3, 12),
(21, 7, 'Miércoles', 'Face pulls', 3, 15),
(22, 7, 'Jueves', 'Press militar con barra', 4, 10),
(23, 7, 'Jueves', 'Elevaciones laterales', 3, 12),
(24, 7, 'Jueves', 'Encogimientos con mancuernas', 3, 15),
(25, 7, 'Viernes', 'HIIT en bicicleta', 1, 20),
(26, 8, 'Lunes', 'Peso muerto rumano', 4, 8),
(27, 8, 'Lunes', 'Sentadillas con kettlebell', 3, 10),
(28, 8, 'Lunes', 'Step-ups con peso', 3, 10),
(29, 8, 'Miércoles', 'Press militar con kettlebells', 4, 8),
(30, 8, 'Miércoles', 'Remo con kettlebell', 3, 10),
(31, 8, 'Miércoles', 'Farmers walk', 3, 30),
(32, 8, 'Viernes', 'Turkish get-up', 3, 5),
(33, 8, 'Viernes', 'Swing con kettlebell', 4, 15),
(34, 8, 'Viernes', 'Lunges caminando con peso', 3, 10),
(35, 9, 'Lunes', 'Sentadillas con barra', 4, 8),
(36, 9, 'Lunes', 'Prensa de piernas', 3, 10),
(37, 9, 'Lunes', 'Extensiones de pierna', 3, 12),
(38, 9, 'Martes', 'Press de banca', 4, 8),
(39, 9, 'Martes', 'Press inclinado con mancuernas', 3, 10),
(40, 9, 'Martes', 'Cruces en polea', 3, 12),
(41, 9, 'Jueves', 'Dominadas asistidas', 4, 6),
(42, 9, 'Jueves', 'Remo con barra', 3, 10),
(43, 9, 'Jueves', 'Hiperextensiones', 3, 12),
(44, 9, 'Viernes', 'Press militar', 4, 8),
(45, 9, 'Viernes', 'Elevaciones laterales', 3, 12),
(46, 9, 'Viernes', 'Encogimientos con barra', 3, 12),
(47, 10, 'Lunes', 'Air squats', 3, 15),
(48, 10, 'Lunes', 'Push-ups', 3, 10),
(49, 10, 'Lunes', 'Burpees', 3, 8),
(50, 10, 'Miércoles', 'Box jumps (caja baja)', 3, 10),
(51, 10, 'Miércoles', 'Kettlebell swings', 3, 12),
(52, 10, 'Miércoles', 'Plank', 3, 30),
(53, 10, 'Viernes', 'Wall balls', 3, 12),
(54, 10, 'Viernes', 'Jump rope', 3, 50),
(55, 10, 'Viernes', 'Sit-ups', 3, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenadores`
--

CREATE TABLE `entrenadores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `especialidad` enum('Musculación','Cardio','Yoga','Crossfit','Pilates','Boxeo') NOT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrenadores`
--

INSERT INTO `entrenadores` (`id`, `nombre`, `especialidad`, `correo`, `telefono`, `activo`) VALUES
(1, 'María González', 'Musculación', 'mgonzalez@ellit.com', '+525512345678', 1),
(2, 'Carlos Mendoza', 'Crossfit', 'cmendoza@ellit.com', '+525598765432', 1),
(3, 'Ana López', 'Yoga', 'alopez@ellit.com', '+525555551234', 1),
(4, 'Roberto Jiménez', 'Cardio', 'rjimenez@ellit.com', '+525544443333', 1),
(5, 'Laura Sánchez', 'Pilates', 'lsanchez@ellit.com', '+525566667777', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_comidas`
--

CREATE TABLE `plan_comidas` (
  `idComida` int(11) NOT NULL,
  `idPlan` int(11) NOT NULL,
  `diaSemana` varchar(20) NOT NULL,
  `momento` varchar(50) NOT NULL,
  `descripcion` text NOT NULL,
  `caloriasAprox` int(11) NOT NULL,
  `horaRecomendada` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_nutricional`
--

CREATE TABLE `plan_nutricional` (
  `idPlan` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `objetivo` varchar(100) NOT NULL,
  `recomendaciones` text DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estatus` char(1) DEFAULT 'A'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plan_nutricional`
--

INSERT INTO `plan_nutricional` (`idPlan`, `nombre`, `objetivo`, `recomendaciones`, `descripcion`, `estatus`) VALUES
(3, 'Definición Muscular', 'Reducir grasa manteniendo masa muscular', 'Consumir 1.8-2.2g de proteína por kg de peso, moderar carbohidratos', 'Plan equilibrado con déficit calórico controlado', 'A'),
(4, 'Volumen Limpio', 'Aumentar masa muscular con mínimo aumento de grasa', 'Superávit calórico de 200-300kcal, énfasis en proteínas', 'Dieta alta en proteínas y carbohidratos complejos', 'A'),
(5, 'Rendimiento Deportivo', 'Optimizar energía para entrenamientos intensos', 'Adecuado consumo de carbohidratos pre-entreno', 'Plan enfocado en timing de nutrientes', 'A'),
(6, 'Vegetariano Fitness', 'Alcanzar objetivos fitness con dieta vegetariana', 'Combinar proteínas vegetales para aminoácidos completos', 'Plan basado en plantas con suplementación estratégica', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idProducto` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT 0,
  `categoria` enum('Suplementos','Ropa','Accesorios','Equipamiento') NOT NULL,
  `fechaCreacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `nombre`, `descripcion`, `precio`, `stock`, `categoria`, `fechaCreacion`) VALUES
(17, 'Proteína Whey Gold Standard', 'Proteína de suero de leche de alta calidad, sabor chocolate, 2.27kg (5lb)', 899.99, 50, 'Suplementos', '2025-07-30 17:28:33'),
(18, 'Creatina Monohidratada', 'Creatina pura micronizada, 300g, aumenta fuerza y rendimiento', 499.99, 30, 'Suplementos', '2025-07-30 17:28:33'),
(19, 'Camiseta Térmica Elite', 'Camiseta de compresión transpirable para entrenamiento intenso', 349.99, 100, 'Ropa', '2025-07-30 17:28:33'),
(20, 'Short Deportivo Pro', 'Short con tecnología dry-fit y bolsillo para llaves', 279.99, 80, 'Ropa', '2025-07-30 17:28:33'),
(21, 'Guantes para Levantamiento', 'Guantes con soporte para muñeca y agarre antideslizante', 399.99, 40, 'Accesorios', '2025-07-30 17:28:33'),
(22, 'Cinturón de Levantamiento', 'Cinturón de cuero genuino para powerlifting', 649.99, 25, 'Accesorios', '2025-07-30 17:28:33'),
(23, 'Mancuernas Ajustables 20kg', 'Par de mancuernas ajustables de 2.5-20kg cada una', 2199.99, 15, 'Equipamiento', '2025-07-30 17:28:33'),
(24, 'Barra Olímpica Elite', 'Barra profesional para levantamiento de pesas, 20kg', 1599.99, 10, 'Equipamiento', '2025-07-30 17:28:33'),
(25, 'Banda de Resistencia', 'Set de 5 bandas con diferentes niveles de resistencia', 299.99, 50, 'Equipamiento', '2025-07-30 17:28:33'),
(26, 'Colchoneta Yoga Premium', 'Colchoneta antideslizante de 6mm de grosor', 249.99, 60, 'Equipamiento', '2025-07-30 17:28:33');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutinas`
--

CREATE TABLE `rutinas` (
  `idRutina` int(11) NOT NULL,
  `nombreRutina` varchar(100) NOT NULL,
  `descripcionRutina` text DEFAULT NULL,
  `objetivoRutina` enum('Tonificar','Cardio','Perder grasa','Aumentar masa muscular','Resistencia') NOT NULL,
  `dificultadRutina` enum('Baja','Estándar','Avanzada') NOT NULL,
  `diasPorSemana` tinyint(4) NOT NULL CHECK (`diasPorSemana` between 1 and 7),
  `horasEstimadas` decimal(3,1) NOT NULL,
  `fechaCreacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rutinas`
--

INSERT INTO `rutinas` (`idRutina`, `nombreRutina`, `descripcionRutina`, `objetivoRutina`, `dificultadRutina`, `diasPorSemana`, `horasEstimadas`, `fechaCreacion`) VALUES
(6, 'Fuerza Avanzada', 'Programa de 6 semanas para desarrollo de fuerza máxima', 'Aumentar masa muscular', 'Avanzada', 4, 1.5, '2025-07-30 17:30:30'),
(7, 'Definición Muscular', 'Rutina para perder grasa manteniendo músculo', 'Perder grasa', 'Estándar', 5, 1.2, '2025-07-30 17:30:41'),
(8, 'Resistencia Funcional', 'Entrenamiento para mejorar movimientos cotidianos', 'Resistencia', 'Estándar', 3, 1.0, '2025-07-30 17:30:54'),
(9, 'Preparación Física General', 'Rutina completa para condición física integral', 'Tonificar', 'Estándar', 4, 1.5, '2025-07-30 17:31:29'),
(10, 'CrossFit Inicial', 'Introducción al CrossFit con movimientos básicos', 'Cardio', 'Baja', 3, 1.0, '2025-07-30 17:31:45');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contrasenia` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `edad` float DEFAULT NULL,
  `rol` varchar(20) NOT NULL DEFAULT 'cliente',
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `contrasenia`, `correo`, `edad`, `rol`, `fecha_registro`) VALUES
(1, 'admin', 'admin123', 'admin@ellit.com', 30, 'admin', '2025-07-25 05:34:32'),
(2, 'jesus', '1234', 'jesusvirrueta233@gmail.com', 19, 'admin', '2025-07-29 15:13:05'),
(3, 'entrenador1', '$2y$10$BcTZRjEg1UOQZxZ5WUfEeX6XJ9yYbW1JdKjLm3pQrSsTvN1KzZbG', 'entrenador1@ellit.com', 28, 'cliente', '2025-07-30 17:26:26'),
(4, 'cliente1', '$2y$10$TcTZRjEg1UOQZxZ5WUfEeX6XJ9yYbW1JdKjLm3pQrSsTvN1KzZbG', 'cliente1@email.com', 25, 'cliente', '2025-07-30 17:26:26'),
(5, 'cliente2', '$2y$10$UcTZRjEg1UOQZxZ5WUfEeX6XJ9yYbW1JdKjLm3pQrSsTvN1KzZbG', 'cliente2@email.com', 32, 'cliente', '2025-07-30 17:26:26'),
(6, 'cliente3', '$2y$10$VcTZRjEg1UOQZxZ5WUfEeX6XJ9yYbW1JdKjLm3pQrSsTvN1KzZbG', 'cliente3@email.com', 29, 'cliente', '2025-07-30 17:26:26');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idVenta` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  `total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `idUsuario`, `fecha`, `total`) VALUES
(6, 3, '2025-07-30 17:29:46', 1749.98),
(7, 4, '2025-07-30 17:29:46', 899.99),
(8, 5, '2025-07-30 17:29:46', 2199.99);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_usuarioId` (`usuarioId`),
  ADD KEY `idx_fecha` (`fecha`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`idDetalle`),
  ADD KEY `idVenta` (`idVenta`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `ejercicios_rutina`
--
ALTER TABLE `ejercicios_rutina`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rutinaId` (`rutinaId`);

--
-- Indices de la tabla `entrenadores`
--
ALTER TABLE `entrenadores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `plan_comidas`
--
ALTER TABLE `plan_comidas`
  ADD PRIMARY KEY (`idComida`),
  ADD KEY `idPlan` (`idPlan`);

--
-- Indices de la tabla `plan_nutricional`
--
ALTER TABLE `plan_nutricional`
  ADD PRIMARY KEY (`idPlan`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `rutinas`
--
ALTER TABLE `rutinas`
  ADD PRIMARY KEY (`idRutina`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idVenta`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `idDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `ejercicios_rutina`
--
ALTER TABLE `ejercicios_rutina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT de la tabla `entrenadores`
--
ALTER TABLE `entrenadores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `plan_comidas`
--
ALTER TABLE `plan_comidas`
  MODIFY `idComida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `plan_nutricional`
--
ALTER TABLE `plan_nutricional`
  MODIFY `idPlan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `rutinas`
--
ALTER TABLE `rutinas`
  MODIFY `idRutina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`usuarioId`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD CONSTRAINT `detalle_ventas_ibfk_1` FOREIGN KEY (`idVenta`) REFERENCES `ventas` (`idVenta`),
  ADD CONSTRAINT `detalle_ventas_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`);

--
-- Filtros para la tabla `ejercicios_rutina`
--
ALTER TABLE `ejercicios_rutina`
  ADD CONSTRAINT `ejercicios_rutina_ibfk_1` FOREIGN KEY (`rutinaId`) REFERENCES `rutinas` (`idRutina`) ON DELETE CASCADE;

--
-- Filtros para la tabla `plan_comidas`
--
ALTER TABLE `plan_comidas`
  ADD CONSTRAINT `plan_comidas_ibfk_1` FOREIGN KEY (`idPlan`) REFERENCES `plan_nutricional` (`idPlan`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
