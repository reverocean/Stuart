DROP DATABASE IF EXISTS SMART;
CREATE DATABASE SMART;
USE SMART;

CREATE TABLE CUSTOMER(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR (100) NOT NULL UNIQUE,
EMAIL VARCHAR (100) NOT NULL,
BRAND VARCHAR (20) NOT NULL,
DATE_OF_BIRTH DATE NOT NULL,
GENDER VARCHAR (10) NOT NULL,
REGISTER_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(ID)
);

CREATE TABLE ADDRESS(
ID INT NOT NULL AUTO_INCREMENT,
CUSTOMER_ID INT NOT NULL,
STATE VARCHAR (20) NOT NULL,
POST_CODE VARCHAR (20) NOT NULL,
CITY VARCHAR (20) NOT NULL,
STREET VARCHAR (50) NOT NULL,
ADDRESS_LINE VARCHAR (500) NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(ID) ON DELETE CASCADE
);