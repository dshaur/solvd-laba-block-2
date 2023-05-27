-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BankDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BankDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BankDB` DEFAULT CHARACTER SET utf8 ;
USE `BankDB` ;

-- -----------------------------------------------------
-- Table `BankDB`.`Customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Customers` (
  `customer_id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `contact_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Accounts` (
  `account_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `account_type` VARCHAR(45) NULL,
  `balance` DECIMAL NULL,
  PRIMARY KEY (`account_id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `accounts_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `BankDB`.`Customers` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Transactions` (
  `transaction_id` INT NOT NULL,
  `account_id` INT NOT NULL,
  `transaction_date` DATETIME NULL,
  `amount` DECIMAL NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `account_id_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `transactions_account_id`
    FOREIGN KEY (`account_id`)
    REFERENCES `BankDB`.`Accounts` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Credit_Applications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Credit_Applications` (
  `application_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `application_date` DATETIME NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`application_id`),
  INDEX `credit_applications_customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `credit_applications_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `BankDB`.`Customers` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Credit_Decisions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Credit_Decisions` (
  `decision_id` INT NOT NULL,
  `application_id` INT NOT NULL,
  `decision_date` DATETIME NULL,
  `approved_amount` DECIMAL NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`decision_id`),
  INDEX `application_id_idx` (`application_id` ASC) VISIBLE,
  CONSTRAINT `credit_decisions_application_id`
    FOREIGN KEY (`application_id`)
    REFERENCES `BankDB`.`Credit_Applications` (`application_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Branches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Branches` (
  `branch_id` INT NOT NULL,
  `branch_name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `contact_number` VARCHAR(45) NULL,
  PRIMARY KEY (`branch_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Bank_Operations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Bank_Operations` (
  `operation_id` INT NOT NULL,
  `branch_id` INT NOT NULL,
  `operation_type` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`operation_id`),
  INDEX `branch_id_idx` (`branch_id` ASC) VISIBLE,
  CONSTRAINT `operations_branch_id`
    FOREIGN KEY (`branch_id`)
    REFERENCES `BankDB`.`Branches` (`branch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Customer_Bank_Operations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Customer_Bank_Operations` (
  `customer_id` INT NOT NULL,
  `operation_id` INT NOT NULL,
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  INDEX `operation_id_idx` (`operation_id` ASC) VISIBLE,
  CONSTRAINT `customer_BO_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `BankDB`.`Customers` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_BO_operation_id`
    FOREIGN KEY (`operation_id`)
    REFERENCES `BankDB`.`Bank_Operations` (`operation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Account_Bank_Operations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Account_Bank_Operations` (
  `account_id` INT NOT NULL,
  `operation_id` INT NOT NULL,
  INDEX `account_id_idx` (`account_id` ASC) VISIBLE,
  INDEX `operation_id_idx` (`operation_id` ASC) VISIBLE,
  CONSTRAINT `account_BO_account_id`
    FOREIGN KEY (`account_id`)
    REFERENCES `BankDB`.`Accounts` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `account_BO_operation_id`
    FOREIGN KEY (`operation_id`)
    REFERENCES `BankDB`.`Bank_Operations` (`operation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Credit_Application_Bank_Operations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Credit_Application_Bank_Operations` (
  `application_id` INT NOT NULL,
  `operation_id` INT NOT NULL,
  INDEX `application_id_idx` (`application_id` ASC) VISIBLE,
  INDEX `operation_id_idx` (`operation_id` ASC) VISIBLE,
  CONSTRAINT `credit_application_BO_application_id`
    FOREIGN KEY (`application_id`)
    REFERENCES `BankDB`.`Credit_Applications` (`application_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `credit_application_BO_operation_id`
    FOREIGN KEY (`operation_id`)
    REFERENCES `BankDB`.`Bank_Operations` (`operation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Transaction_Bank_Operations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Transaction_Bank_Operations` (
  `transaction_id` INT NOT NULL,
  `operation_id` INT NOT NULL,
  INDEX `transaction_id_idx` (`transaction_id` ASC) VISIBLE,
  INDEX `operation_id_idx` (`operation_id` ASC) VISIBLE,
  CONSTRAINT `transaction_BO_transaction_id`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `BankDB`.`Transactions` (`transaction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transaction_BO_operation_id`
    FOREIGN KEY (`operation_id`)
    REFERENCES `BankDB`.`Bank_Operations` (`operation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BankDB`.`Loans`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BankDB`.`Loans` (
  `loan_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `loan_amount` DECIMAL NULL,
  `interest_rate` FLOAT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`loan_id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `loans_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `BankDB`.`Customers` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
