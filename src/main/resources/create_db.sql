CREATE DATABASE `db_radmirtech`;
USE db_radmirtech;

CREATE TABLE `equipment` (
  `id` bigint(20) NOT NULL,
  `equipment_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `serial_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `details_id` bigint(20) DEFAULT NULL,
  `manufacturer_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `equipment` (`id`, `equipment_type`, `serial_number`, `details_id`, `manufacturer_id`) VALUES
(12, 'Type 1', 'b9a53a48-b89b-11eb-8529-0242ac130003', 4, 2),
(13, 'Type 2', 'b9a53cdc-b89b-11eb-8529-0242ac130003', 5, 1),
(14, 'Type 1', 'b9a53dd6-b89b-11eb-8529-0242ac130003', 6, 3),
(15, 'Type 3', 'b9a5438a-b89b-11eb-8529-0242ac130003', 7, 3),
(16, 'Type 2', 'b9a54484-b89b-11eb-8529-0242ac130003', 8, 1),
(17, 'Type 3', 'b9a5475e-b89b-11eb-8529-0242ac130003', 9, 2),
(18, 'Type 3', 'b9a54826-b89b-11eb-8529-0242ac130003', 10, 2),
(19, 'Type 1', 'b9a548e4-b89b-11eb-8529-0242ac130003', 11, 1);

CREATE TABLE `equipment_details` (
  `id` bigint(20) NOT NULL,
  `current_volume` int(11) NOT NULL,
  `has_error` bit(1) NOT NULL,
  `last_contact_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `equipment_details` (`id`, `current_volume`, `has_error`, `last_contact_date`) VALUES
(4, 1000, b'1', DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(5, 1100, b'0', DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(6, 1200, b'0', DATE_SUB(NOW(), INTERVAL 1 MINUTE)),
(7, 2200, b'0', DATE_SUB(NOW(), INTERVAL 30 MINUTE)),
(8, 5100, b'1', DATE_SUB(NOW(), INTERVAL 12 HOUR)),
(9, 7800, b'1', DATE_SUB(NOW(), INTERVAL 7 HOUR)),
(10, 4800, b'0', DATE_SUB(NOW(), INTERVAL 25 MINUTE)),
(11, 1500, b'1', DATE_SUB(NOW(), INTERVAL 8 HOUR));

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(20);

CREATE TABLE `manufacturer` (
  `id` bigint(20) NOT NULL,
  `contact_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `manufacturer` (`id`, `contact_number`, `country`, `name`) VALUES
(1, 'Contact number 1', 'Ukraine', 'Manufacturer 1'),
(2, 'Contact number 2', 'USA', 'Manufacturer 2'),
(3, 'Contact number 3', 'Germany', 'Manufacturer 3');

ALTER TABLE `equipment`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_agm98wn5ln6uoh6o9hx25ogic` (`serial_number`),
  ADD KEY `FK16ynv8rmvp4pm2m6bommc3mvu` (`details_id`),
  ADD KEY `FKp9pllcf6kbc9k1g5ngxin3myn` (`manufacturer_id`);

ALTER TABLE `equipment_details`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `manufacturer`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `equipment`
  ADD CONSTRAINT `FK16ynv8rmvp4pm2m6bommc3mvu` FOREIGN KEY (`details_id`) REFERENCES `equipment_details` (`id`),
  ADD CONSTRAINT `FKp9pllcf6kbc9k1g5ngxin3myn` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`id`);

DELIMITER $$

CREATE
    EVENT `update_equipment_status`
    ON SCHEDULE EVERY 1 HOUR STARTS NOW()
    DO BEGIN

        UPDATE equipment_details det
        SET det.has_error = 1
        WHERE TIMESTAMPDIFF(HOUR, det.last_contact_date, NOW()) >= 1;

        UPDATE equipment_details det
        SET det.has_error = 0
        WHERE TIMESTAMPDIFF(HOUR, det.last_contact_date, NOW()) < 1;

    END $$

DELIMITER ;
