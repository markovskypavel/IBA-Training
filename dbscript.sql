-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema FestivalOrganisation
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FestivalOrganisation
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FestivalOrganisation` DEFAULT CHARACTER SET utf8 ;
USE `FestivalOrganisation` ;

-- -----------------------------------------------------
-- Table `FestivalOrganisation`.`Place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalOrganisation`.`Place` (
  `place_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `capacity` INT NOT NULL,
  PRIMARY KEY (`place_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalOrganisation`.`Artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalOrganisation`.`Artist` (
  `artist_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(45) NULL,
  PRIMARY KEY (`artist_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalOrganisation`.`Activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalOrganisation`.`Activity` (
  `activity_id` INT NOT NULL AUTO_INCREMENT,
  `place_id` INT NOT NULL,
  `activityType` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `date` DATE NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`activity_id`),
  INDEX `fk_Activity_Place1_idx` (`place_id` ASC),
  CONSTRAINT `fk_Activity_Place1`
    FOREIGN KEY (`place_id`)
    REFERENCES `FestivalOrganisation`.`Place` (`place_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalOrganisation`.`Identity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalOrganisation`.`Identity` (
  `identity_id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NULL,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NULL,
  PRIMARY KEY (`identity_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalOrganisation`.`WebIdentity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalOrganisation`.`WebIdentity` (
  `webIdentity_id` INT NOT NULL AUTO_INCREMENT,
  `identity_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telephone` VARCHAR(45) NULL,
  `status` TINYINT NOT NULL,
  PRIMARY KEY (`webIdentity_id`, `identity_id`),
  INDEX `fk_WebIdentity_Identity1_idx` (`identity_id` ASC),
  CONSTRAINT `fk_WebIdentity_Identity1`
    FOREIGN KEY (`identity_id`)
    REFERENCES `FestivalOrganisation`.`Identity` (`identity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalOrganisation`.`Activity_has_Artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalOrganisation`.`Activity_has_Artist` (
  `activity_id` INT NOT NULL,
  `artist_id` INT NOT NULL,
  PRIMARY KEY (`activity_id`, `artist_id`),
  INDEX `fk_Activity_has_Artist_Artist1_idx` (`artist_id` ASC),
  INDEX `fk_Activity_has_Artist_Activity1_idx` (`activity_id` ASC),
  CONSTRAINT `fk_Activity_has_Artist_Activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `FestivalOrganisation`.`Activity` (`activity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Activity_has_Artist_Artist1`
    FOREIGN KEY (`artist_id`)
    REFERENCES `FestivalOrganisation`.`Artist` (`artist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalOrganisation`.`Activity_has_WebIdentity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalOrganisation`.`Activity_has_WebIdentity` (
  `activity_id` INT NOT NULL,
  `webIdentity_id` INT NOT NULL,
  PRIMARY KEY (`activity_id`, `webIdentity_id`),
  INDEX `fk_Activity_has_WebIdentity_WebIdentity1_idx` (`webIdentity_id` ASC),
  INDEX `fk_Activity_has_WebIdentity_Activity1_idx` (`activity_id` ASC),
  CONSTRAINT `fk_Activity_has_WebIdentity_Activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `FestivalOrganisation`.`Activity` (`activity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Activity_has_WebIdentity_WebIdentity1`
    FOREIGN KEY (`webIdentity_id`)
    REFERENCES `FestivalOrganisation`.`WebIdentity` (`webIdentity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
