-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 28, 2025 at 03:11 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `p2h_oovp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `username`, `password`) VALUES
(1, 'admin1', 'adminpass123'),
(4, 'admin2', 'adminpass123');

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `id` int NOT NULL,
  `room_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `checkin_date` date DEFAULT NULL,
  `checkout_date` date DEFAULT NULL,
  `payment_status` enum('pending','paid') DEFAULT 'pending',
  `base_room_price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`id`, `room_id`, `customer_id`, `checkin_date`, `checkout_date`, `payment_status`, `base_room_price`) VALUES
(17, 20, 24, '2025-05-28', '2025-05-30', 'paid', '600000.00'),
(18, 23, 25, '2025-05-01', '2025-05-03', 'paid', '450000.00'),
(19, 19, 26, '2025-05-22', '2025-05-31', 'paid', '550000.00'),
(20, 25, 27, '2025-05-27', '2025-05-30', 'paid', '550000.00'),
(21, 26, 28, '2025-05-06', '2025-05-22', 'paid', '600000.00'),
(22, 15, 29, '2025-05-15', '2025-05-30', 'paid', '350000.00'),
(23, 21, 30, '2025-05-02', '2025-05-16', 'paid', '350000.00'),
(24, 16, 31, '2025-05-15', '2025-05-31', 'paid', '400000.00'),
(25, 22, 32, '2025-05-16', '2025-05-21', 'paid', '400000.00'),
(26, 17, 33, '2025-05-09', '2025-05-31', 'paid', '450000.00'),
(27, 23, 34, '2025-05-15', '2025-05-28', 'paid', '450000.00'),
(28, 18, 35, '2025-05-09', '2025-05-29', 'paid', '10000000.00'),
(29, 31, 36, '2025-06-01', '2025-06-10', 'paid', '4950000.00');

--
-- Triggers `bookings`
--
DELIMITER $$
CREATE TRIGGER `calculate_base_room_price` BEFORE INSERT ON `bookings` FOR EACH ROW BEGIN
    DECLARE roomPrice DECIMAL(10,2);
    DECLARE diffDays INT;

    -- Ambil harga kamar dari tabel rooms
    SELECT price INTO roomPrice
    FROM rooms
    WHERE id = NEW.room_id;

    -- Hitung jumlah hari
    SET diffDays = DATEDIFF(NEW.checkout_date, NEW.checkin_date);
    IF diffDays <= 0 THEN
        SET diffDays = 1;
    END IF;

    -- Hitung total harga dan set ke kolom base_room_price
    SET NEW.base_room_price = roomPrice * diffDays;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_set_base_room_price_before_booking_insert` BEFORE INSERT ON `bookings` FOR EACH ROW BEGIN
  DECLARE room_price DECIMAL(10,2);

  -- Ambil harga dari tabel rooms
  SELECT price INTO room_price
  FROM rooms
  WHERE id = NEW.room_id;

  -- Set nilai base_room_price otomatis
  SET NEW.base_room_price = room_price;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `booking_requests`
--

CREATE TABLE `booking_requests` (
  `id` int NOT NULL,
  `booking_id` int NOT NULL,
  `special_request_id` int NOT NULL,
  `quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Triggers `booking_requests`
--
DELIMITER $$
CREATE TRIGGER `trg_update_final_total_price_after_request_insert` AFTER INSERT ON `booking_requests` FOR EACH ROW BEGIN
  DECLARE room_price DECIMAL(10,2);
  DECLARE total_requests DECIMAL(10,2);

  -- Ambil harga kamar dari tabel rooms (kolomnya 'price')
  SELECT r.price INTO room_price
  FROM bookings b
  JOIN rooms r ON b.room_id = r.id
  WHERE b.id = NEW.booking_id;

  -- Hitung total harga special request
  SELECT SUM(sr.price * br.quantity) INTO total_requests
  FROM booking_requests br
  JOIN special_requests sr ON br.special_request_id = sr.id
  WHERE br.booking_id = NEW.booking_id;

  -- Update final_total_price di bookings
  UPDATE bookings
  SET final_total_price = room_price + IFNULL(total_requests, 0)
  WHERE id = NEW.booking_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nik` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `phone`, `email`, `nik`) VALUES
(24, 'Anjelin', '082222222', 'anjelin@gmail.com', '001202400202'),
(25, 'alin', '0823', 'alin@gmail.com', '001220'),
(26, 'alin', '2412', 'sfs', '2142'),
(27, 'fghfg', 'dg', 'gd', 'd212'),
(28, 'hdfhd', 'ddf', 'dgfd', 'gffd'),
(29, 'gdgdf', 'bdf', 'sds', 'bdfbddb'),
(30, 'feffe', 'ergre', 'eeefe', 'erferfe'),
(31, 'gj', 'hjjh', 'jhj', 'nnm'),
(32, 'jass', '35456', 'fhd', '242'),
(33, 'sjfhsjehf', '82572', 'gaef', 'snsjfs'),
(34, 'aln', '08298732', '@gmail.com', '234284'),
(35, 'qeqqy482', '28472468', 'sfbjf', 'fsdjfewj'),
(36, 'anjelin', '082260272758', 'aln@gmail.com', '00111111005');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int NOT NULL,
  `booking_id` int DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_method` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`id`, `booking_id`, `amount`, `payment_date`, `payment_method`) VALUES
(4, 17, '600000.00', '2025-05-28', 'E-Wallet'),
(5, 18, '450000.00', '2025-05-01', 'Debit'),
(6, 19, '4950000.00', '2025-05-22', 'Tunai'),
(7, 20, '0.00', '2025-05-27', 'Tunai'),
(8, 21, '0.00', '2025-05-06', 'Unique Payment Card (UPC)'),
(9, 22, '5250000.00', '2025-05-15', 'Pembayaran Perusahaan'),
(10, 23, '4900000.00', '2025-05-02', 'Tunai'),
(11, 24, '6400000.00', '2025-05-15', 'Tunai'),
(12, 25, '2000000.00', '2025-05-16', 'Tunai'),
(13, 26, '9900000.00', '2025-05-09', 'E-Wallet'),
(14, 27, '5850000.00', '2025-05-15', 'Unique Payment Card (UPC)'),
(15, 28, '10000000.00', '2025-05-09', 'E-Wallet'),
(16, 29, '4950000.00', '2025-06-01', 'Unique Payment Card (UPC)');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int NOT NULL,
  `number` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `bed` enum('single','double') DEFAULT NULL,
  `availability` enum('available','booked') DEFAULT 'available',
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `number`, `type`, `bed`, `availability`, `price`) VALUES
(15, '101', 'Standard', 'single', 'booked', '350000.00'),
(16, '102', 'Standard', 'double', 'booked', '400000.00'),
(17, '103', 'Deluxe', 'single', 'booked', '450000.00'),
(18, '104', 'Deluxe', 'double', 'available', '500000.00'),
(19, '105', 'Suite', 'single', 'booked', '550000.00'),
(20, '106', 'Suite', 'double', 'booked', '600000.00'),
(21, '107', 'Standard', 'single', 'booked', '350000.00'),
(22, '108', 'Standard', 'double', 'booked', '400000.00'),
(23, '109', 'Deluxe', 'single', 'booked', '450000.00'),
(24, '110', 'Deluxe', 'double', 'available', '550000.00'),
(25, '111', 'Suite', 'single', 'booked', '550000.00'),
(26, '112', 'Suite', 'double', 'booked', '600000.00'),
(27, '113', 'Standard', 'single', 'available', '350000.00'),
(28, '114', 'Standard', 'double', 'available', '400000.00'),
(29, '115', 'Deluxe', 'single', 'available', '450000.00'),
(30, '116', 'Deluxe', 'double', 'available', '500000.00'),
(31, '117', 'Suite', 'single', 'available', '550000.00'),
(32, '118', 'Suite', 'double', 'available', '600000.00');

-- --------------------------------------------------------

--
-- Table structure for table `special_requests`
--

CREATE TABLE `special_requests` (
  `id` int NOT NULL,
  `item` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `special_requests`
--

INSERT INTO `special_requests` (`id`, `item`, `quantity`, `price`) VALUES
(1, 'Extra Pillow', 1, '25000.00'),
(2, 'Extra Blanket', 1, '35000.00'),
(3, 'Breakfast', 1, '100000.00'),
(4, 'Late Checkout (per hour)', 1, '75000.00'),
(6, 'Baby Crib', 1, '50000.00'),
(7, 'Extra Bed', 1, '50000.00'),
(8, 'Airport Pickup', 1, '250000.00'),
(9, 'Flower Bouquet', 1, '200000.00'),
(10, 'Birthday Decoration', 1, '300000.00'),
(11, 'Candlelight Dinner', 1, '25000.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `booking_requests`
--
ALTER TABLE `booking_requests`
  ADD PRIMARY KEY (`id`),
  ADD KEY `booking_id` (`booking_id`),
  ADD KEY `special_request_id` (`special_request_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `booking_id` (`booking_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `special_requests`
--
ALTER TABLE `special_requests`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `booking_requests`
--
ALTER TABLE `booking_requests`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `special_requests`
--
ALTER TABLE `special_requests`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `booking_requests`
--
ALTER TABLE `booking_requests`
  ADD CONSTRAINT `booking_requests_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `booking_requests_ibfk_2` FOREIGN KEY (`special_request_id`) REFERENCES `special_requests` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
