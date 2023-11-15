drop database if exists paintball;
create database if not exists paintball;
use paintball;

CREATE TABLE IF NOT EXISTS Playground
(
    `pg_ID`             INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `pg_Name`           VARCHAR(45)     NOT NULL,
    `pg_Description`    VARCHAR(45)     NOT NULL,
    `pg_Price`          INT             UNSIGNED NOT NULL,
    `pg_Area`           INT             UNSIGNED NOT NULL,
    `pg_Capacity`       INT             UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS User
(
    `u_ID`       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `u_Email`    VARCHAR(45)  NOT NULL UNIQUE,
    `u_Login`    VARCHAR(45)  NOT NULL UNIQUE,
    `u_Password` VARCHAR(100) NOT NULL,
    `u_Role`     VARCHAR(10)  NOT NULL
);

CREATE TABLE IF NOT EXISTS Orders
(
    `o_ID`             INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `o_Date`           VARCHAR(45) NOT NULL,
    `user_ID`          INT         NOT NULL,
    `playground_ID`    INT         NOT NULL,
    FOREIGN KEY (`user_ID`) REFERENCES User (`u_ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`playground_ID`) REFERENCES Playground (`pg_ID`) ON DELETE RESTRICT ON UPDATE CASCADE
);
