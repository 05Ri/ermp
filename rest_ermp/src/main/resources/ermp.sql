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

CREATE TABLE IF NOT EXISTS `exercise_log` (
	`user_id` varchar(20),
    `day` DATE NOT NULL,
    `start_time` INT,
    `ent_time` INT,
    `type` varchar(20),
    `amount` INT,
    `unit` varchar(20),
    
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)
ENGINE = InnoDB;

INSERT INTO `user`
VALUES ("ssafy", "김싸피", '1234', 'ssafy@ssafy.com');
