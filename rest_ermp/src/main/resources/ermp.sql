CREATE DATABASE IF NOT EXISTS `ERMP`;

USE `ERMP`;

CREATE TABLE IF NOT EXISTS `user` (
	`user_id` varchar(20) PRIMARY KEY,
    `name` varchar(20) NOT NULL,
    `password` varchar(20) NOT NULL,
    `email` varchar(50) UNIQUE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `attendence` (
	`user_id` varchar(20),
    `day` DATE DEFAULT (current_date),
    
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `plan` (
	`user_id` varchar(20),
    `day` DATE NOT NULL,
    `start_time` INT,
    `ent_time` INT,
    `type` varchar(20),
    `goal_amount` INT,
    `unit` varchar(20),
    
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `exercise_log`(
	`user_id` varchar(20),
    `type` varchar(20),
    `day` DATE DEFAULT (current_date),
    `amount` INT,
    `unit` varchar(20),
    
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)
ENGINE = InnoDB;

