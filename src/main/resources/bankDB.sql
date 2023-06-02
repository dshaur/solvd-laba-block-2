-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bankdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bankdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bankdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bankdb` ;

-- -----------------------------------------------------
-- Table `bankdb`.`branches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`branches` (
  `Branch_ID` INT NOT NULL AUTO_INCREMENT,
  `Branch_Name` VARCHAR(100) NULL DEFAULT NULL,
  `Location` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Branch_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`accounts` (
  `Account_ID` INT NOT NULL AUTO_INCREMENT,
  `Account_Type` VARCHAR(50) NULL DEFAULT NULL,
  `Balance` DECIMAL(10,2) NULL DEFAULT NULL,
  `Open_Date` DATE NULL DEFAULT NULL,
  `Last_Transaction_Date` DATE NULL DEFAULT NULL,
  `Branch_ID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Account_ID`),
  INDEX `Branch_ID` (`Branch_ID` ASC) VISIBLE,
  CONSTRAINT `accounts_ibfk_1`
    FOREIGN KEY (`Branch_ID`)
    REFERENCES `bankdb`.`branches` (`Branch_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`customers` (
  `Customer_ID` INT NOT NULL AUTO_INCREMENT,
  `First_Name` VARCHAR(50) NULL DEFAULT NULL,
  `Last_Name` VARCHAR(50) NULL DEFAULT NULL,
  `Address` VARCHAR(100) NULL DEFAULT NULL,
  `Phone_Number` VARCHAR(20) NULL DEFAULT NULL,
  `Email` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Customer_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`account_holders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`account_holders` (
  `account_holder_ID` INT NOT NULL AUTO_INCREMENT,
  `Account_ID` INT NOT NULL,
  `Customer_ID` INT NOT NULL,
  PRIMARY KEY (`account_holder_ID`, `Account_ID`, `Customer_ID`),
  INDEX `Customer_ID` (`Customer_ID` ASC) VISIBLE,
  INDEX `account_holders_ibfk_1` (`Account_ID` ASC) VISIBLE,
  CONSTRAINT `account_holders_ibfk_1`
    FOREIGN KEY (`Account_ID`)
    REFERENCES `bankdb`.`accounts` (`Account_ID`),
  CONSTRAINT `account_holders_ibfk_2`
    FOREIGN KEY (`Customer_ID`)
    REFERENCES `bankdb`.`customers` (`Customer_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`branch_employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`branch_employees` (
  `Employee_ID` INT NOT NULL AUTO_INCREMENT,
  `Branch_ID` INT NULL DEFAULT NULL,
  `First_Name` VARCHAR(50) NULL DEFAULT NULL,
  `Last_Name` VARCHAR(50) NULL DEFAULT NULL,
  `Position` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`Employee_ID`),
  INDEX `Branch_ID` (`Branch_ID` ASC) VISIBLE,
  CONSTRAINT `branch_employees_ibfk_1`
    FOREIGN KEY (`Branch_ID`)
    REFERENCES `bankdb`.`branches` (`Branch_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`credit_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`credit_cards` (
  `Credit_card_ID` INT NOT NULL AUTO_INCREMENT,
  `Customer_ID` INT NULL DEFAULT NULL,
  `credit_card_number` VARCHAR(45) NULL DEFAULT NULL,
  `Expiry_Date` DATE NULL DEFAULT NULL,
  `Credit_Limit` DECIMAL(10,2) NULL DEFAULT NULL,
  `Outstanding_Balance` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Card_card_ID`),
  INDEX `Customer_ID` (`Customer_ID` ASC) VISIBLE,
  CONSTRAINT `credit_cards_ibfk_1`
    FOREIGN KEY (`Customer_ID`)
    REFERENCES `bankdb`.`customers` (`Customer_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`debit_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`debit_cards` (
  `debit_card_ID` INT NOT NULL AUTO_INCREMENT,
  `Customer_ID` INT NULL DEFAULT NULL,
  `debit_card_number` VARCHAR(45) NULL DEFAULT NULL,
  `Expiry_Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`debit_card_ID`),
  INDEX `Customer_ID` (`Customer_ID` ASC) VISIBLE,
  CONSTRAINT `debit_cards_ibfk_1`
    FOREIGN KEY (`Customer_ID`)
    REFERENCES `bankdb`.`customers` (`Customer_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`transactions` (
  `Transaction_ID` INT NOT NULL AUTO_INCREMENT,
  `Account_ID` INT NULL DEFAULT NULL,
  `Transaction_Type` VARCHAR(50) NULL DEFAULT NULL,
  `Amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `Transaction_Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Transaction_ID`),
  INDEX `Account_ID` (`Account_ID` ASC) VISIBLE,
  CONSTRAINT `transactions_ibfk_1`
    FOREIGN KEY (`Account_ID`)
    REFERENCES `bankdb`.`accounts` (`Account_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`deposits`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`deposits` (
  `Deposit_ID` INT NOT NULL AUTO_INCREMENT,
  `Transaction_ID` INT NULL DEFAULT NULL,
  `Amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `Deposit_Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Deposit_ID`),
  INDEX `Transaction_ID` (`Transaction_ID` ASC) VISIBLE,
  CONSTRAINT `deposits_ibfk_1`
    FOREIGN KEY (`Transaction_ID`)
    REFERENCES `bankdb`.`transactions` (`Transaction_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`loans`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`loans` (
  `Loan_ID` INT NOT NULL AUTO_INCREMENT,
  `Customer_ID` INT NULL DEFAULT NULL,
  `Loan_Type` VARCHAR(50) NULL DEFAULT NULL,
  `Loan_Amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `Interest_Rate` DECIMAL(5,2) NULL DEFAULT NULL,
  `Start_Date` DATE NULL DEFAULT NULL,
  `End_Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Loan_ID`),
  INDEX `Customer_ID` (`Customer_ID` ASC) VISIBLE,
  CONSTRAINT `loans_ibfk_1`
    FOREIGN KEY (`Customer_ID`)
    REFERENCES `bankdb`.`customers` (`Customer_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`transfers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`transfers` (
  `Transfer_ID` INT NOT NULL AUTO_INCREMENT,
  `Transaction_ID` INT NULL DEFAULT NULL,
  `Source_Account_ID` INT NULL DEFAULT NULL,
  `Destination_Account_ID` INT NULL DEFAULT NULL,
  `Amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `Transfer_Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Transfer_ID`),
  INDEX `Transaction_ID` (`Transaction_ID` ASC) VISIBLE,
  INDEX `Source_Account_ID` (`Source_Account_ID` ASC) VISIBLE,
  INDEX `Destination_Account_ID` (`Destination_Account_ID` ASC) VISIBLE,
  CONSTRAINT `transfers_ibfk_1`
    FOREIGN KEY (`Transaction_ID`)
    REFERENCES `bankdb`.`transactions` (`Transaction_ID`),
  CONSTRAINT `transfers_ibfk_2`
    FOREIGN KEY (`Source_Account_ID`)
    REFERENCES `bankdb`.`accounts` (`Account_ID`),
  CONSTRAINT `transfers_ibfk_3`
    FOREIGN KEY (`Destination_Account_ID`)
    REFERENCES `bankdb`.`accounts` (`Account_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankdb`.`withdrawals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankdb`.`withdrawals` (
  `Withdrawal_ID` INT NOT NULL AUTO_INCREMENT,
  `Transaction_ID` INT NULL DEFAULT NULL,
  `Amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `Withdrawal_Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Withdrawal_ID`),
  INDEX `Transaction_ID` (`Transaction_ID` ASC) VISIBLE,
  CONSTRAINT `withdrawals_ibfk_1`
    FOREIGN KEY (`Transaction_ID`)
    REFERENCES `bankdb`.`transactions` (`Transaction_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
