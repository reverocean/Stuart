DROP DATABASE IF EXISTS SMART;
CREATE DATABASE SMART;
USE SMART;

CREATE TABLE ADDRESS(
ID INT NOT NULL AUTO_INCREMENT,
STATE VARCHAR (20) NOT NULL,
POST_CODE VARCHAR (20) NOT NULL,
CITY VARCHAR (20) NOT NULL,
STREET VARCHAR (50) NOT NULL,
ADDRESS_LINE VARCHAR (500) NOT NULL,
PRIMARY KEY(ID)
);

CREATE TABLE INDIVIDUAL(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR (100) NOT NULL,
EMAIL VARCHAR (100) NOT NULL,
DATE_OF_BIRTH DATE NOT NULL,
GENDER VARCHAR (10) NOT NULL,
REGISTER_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADDRESS_ID INT NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(ADDRESS_ID) REFERENCES ADDRESS(ID)
);

CREATE TABLE PROFILE (
ID INT NOT NULL AUTO_INCREMENT,
BRAND VARCHAR(20) NOT NULL,
INDIVIDUAL_ID INT NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(INDIVIDUAL_ID) REFERENCES INDIVIDUAL(ID)
);

CREATE TABLE CUSTOMER (
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(50) NOT NULL,
PRIMARY KEY(ID)
);

CREATE TABLE CUSTOMER_BRAND (
ID INT NOT NULL AUTO_INCREMENT,
CUSTOMER_ID INT NOT NULL,
BRAND INT NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(ID)
);

CREATE TABLE BRAND_SIMILARITY(
ID INT NOT NULL AUTO_INCREMENT,
BRAND1 VARCHAR (20) NOT NULL,
BRAND2 VARCHAR (20) NOT NULL,
SIMILARITY DOUBLE NOT NULL,
PRIMARY KEY(ID)
);

