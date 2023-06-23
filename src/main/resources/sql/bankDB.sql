-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema david_bankdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema david_bankdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `david_bankdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `david_bankdb` ;

-- Table `david_bankdb`.`branches`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`branches` (
  `branch_id` INT NOT NULL AUTO_INCREMENT,
  `branch_name` VARCHAR(100) NULL DEFAULT NULL,
  `location` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`branch_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- Table `david_bankdb`.`accounts`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`accounts` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `account_type` VARCHAR(50) NULL DEFAULT NULL,
  `balance` DECIMAL(10,2) NULL DEFAULT NULL,
  `open_date` DATE NULL DEFAULT NULL,
  `last_transaction_date` DATE NULL DEFAULT NULL,
  `branch_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  INDEX `branch_id` (`branch_id` ASC),
  CONSTRAINT `accounts_ibfk_1`
    FOREIGN KEY (`branch_id`)
    REFERENCES `david_bankdb`.`branches` (`branch_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- Table `david_bankdb`.`customers`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`customers` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NULL DEFAULT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `phone_number` VARCHAR(20) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- Table `david_bankdb`.`account_holders`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`account_holders` (
  `account_holder_id` INT NOT NULL AUTO_INCREMENT,
  `account_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`account_holder_id`, `account_id`, `customer_id`),
  INDEX `customer_id` (`customer_id` ASC),
  INDEX `account_holders_ibfk_1` (`account_id` ASC),
  CONSTRAINT `account_holders_ibfk_1`
    FOREIGN KEY (`account_id`)
    REFERENCES `david_bankdb`.`accounts` (`account_id`),
  CONSTRAINT `account_holders_ibfk_2`
    FOREIGN KEY (`customer_id`)
    REFERENCES `david_bankdb`.`customers` (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- Table `david_bankdb`.`branch_employees`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`branch_employees` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `branch_id` INT NULL DEFAULT NULL,
  `first_name` VARCHAR(50) NULL DEFAULT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  `position` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `branch_id` (`branch_id` ASC),
  CONSTRAINT `branch_employees_ibfk_1`
    FOREIGN KEY (`branch_id`)
    REFERENCES `david_bankdb`.`branches` (`branch_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- Table `david_bankdb`.`credit_cards`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`credit_cards` (
  `credit_card_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NULL DEFAULT NULL,
  `credit_card_number` VARCHAR(45) NULL DEFAULT NULL,
  `expiry_date` DATE NULL DEFAULT NULL,
  `credit_limit` DECIMAL(10,2) NULL DEFAULT NULL,
  `outstanding_balance` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`credit_card_id`),
  INDEX `customer_id` (`customer_id` ASC),
  CONSTRAINT `credit_cards_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `david_bankdb`.`customers` (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- Table `david_bankdb`.`debit_cards`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`debit_cards` (
  `debit_card_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NULL DEFAULT NULL,
  `debit_card_number` VARCHAR(45) NULL DEFAULT NULL,
  `expiry_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`debit_card_id`),
  INDEX `customer_id` (`customer_id` ASC),
  CONSTRAINT `debit_cards_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `david_bankdb`.`customers` (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- Table `david_bankdb`.`loans`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`loans` (
  `loan_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NULL DEFAULT NULL,
  `loan_type` VARCHAR(50) NULL DEFAULT NULL,
  `loan_amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `interest_rate` DECIMAL(5,2) NULL DEFAULT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`loan_id`),
  INDEX `customer_id` (`customer_id` ASC),
  CONSTRAINT `loans_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `david_bankdb`.`customers` (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- Table `david_bankdb`.`transaction_type`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`transaction_type` (
  `transaction_type_id` INT NOT NULL,
  `type_name` VARCHAR(45) NULL,
  PRIMARY KEY (`transaction_type_id`))
ENGINE = InnoDB;


-- Table `david_bankdb`.`transactions`
CREATE TABLE IF NOT EXISTS `david_bankdb`.`transactions` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `transaction_type_id` INT NULL,
  `account_id` INT NULL DEFAULT NULL,
  `amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `transaction_date` DATE NULL DEFAULT NULL,
  `source_account_id` INT NULL,
  `destination_account_id` INT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `account_id` (`account_id` ASC),
  INDEX `fk_transactions_transaction_type1_idx` (`transaction_type_id` ASC),
  CONSTRAINT `transactions_ibfk_1`
    FOREIGN KEY (`account_id`)
    REFERENCES `david_bankdb`.`accounts` (`account_id`),
  CONSTRAINT `fk_transactions_transaction_type1`
    FOREIGN KEY (`transaction_type_id`)
    REFERENCES `david_bankdb`.`transaction_type` (`transaction_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
