DROP DATABASE IF EXISTS `ERMP_DB`;
CREATE DATABASE IF NOT EXISTS `ERMP_DB`;

USE `ERMP_DB`;

CREATE TABLE IF NOT EXISTS `user` (
	`user_id` varchar(20) PRIMARY KEY,
    `name` varchar(20) NOT NULL,
    `password` varchar(20) NOT NULL,
    `email` varchar(50) UNIQUE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `attendance` (
	`user_id` varchar(20),
    `day` DATE DEFAULT (current_date),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    PRIMARY KEY (`user_id`, `day`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `routine` (
	`log_id` INT AUTO_INCREMENT PRIMARY KEY,
	`user_id` varchar(20),
    `day` DATE NOT NULL,
    `start_time` INT,
    `end_time` INT,
    `type` varchar(20),
    `goal_amount` INT,
    `achieve_amount` INT,
    `unit` varchar(20),
    
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)
ENGINE = InnoDB;

