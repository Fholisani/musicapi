-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema musicapi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema musicapi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `musicapi` DEFAULT CHARACTER SET latin1 ;
USE `musicapi` ;

-- -----------------------------------------------------
-- Table `musicapi`.`content_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musicapi`.`content_file` (
  `content_id` INT(11) NOT NULL AUTO_INCREMENT,
  `song_name` VARCHAR(255) NOT NULL,
  `producer_name` VARCHAR(255) NOT NULL,
  `artists_names` VARCHAR(255) NOT NULL,
  `release_date` DATETIME NOT NULL,
  `location` VARCHAR(500) NOT NULL,
  `location_profile` VARCHAR(500) NOT NULL,
  `location_artwork` VARCHAR(500) NOT NULL,
  `status` VARCHAR(100) NOT NULL,
  `type` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`content_id`),
  UNIQUE INDEX `content_id_UNIQUE` (`content_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
