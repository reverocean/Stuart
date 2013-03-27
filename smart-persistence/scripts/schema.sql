DROP DATABASE IF EXISTS SMART;
CREATE DATABASE SMART;
USE SMART;

drop table if exists BRAND_SIMILARITY;
drop table if exists CUSTOMER;
drop table if exists CUSTOMER_PROFILE;
drop table if exists INDIVIDUAL;
drop table if exists PROFILE;

create table BRAND_SIMILARITY (
        ID integer not null,
        THIS_BRAND varchar(255) not null,
        THAT_BRAND varchar(255) not null,
        SIMILARITY double precision not null,
        primary key (ID)
    );

create table CUSTOMER (
        ID integer not null,
        NAME varchar(255) not null,
        primary key (ID)
    );

create table CUSTOMER_PROFILE (
        CUSTOMER_ID integer not null,
        PROFILE_ID integer not null,
        primary key (CUSTOMER_ID, PROFILE_ID)
    );

create table INDIVIDUAL (
        ID integer not null,
        NAME varchar(255) not null,
        EMAIL varchar(255) not null,
        DATE_OF_BIRTH date not null,
        REGISTER_TIME datetime not null,
        STATE varchar(255),
        POST_CODE varchar(255),
        CITY varchar(255),
        STREET varchar(255),
        ADDRESS_LINE varchar(255),
        primary key (ID)
    );

create table PROFILE (
        ID integer not null,
        BRAND varchar(255) not null,
        INDIVIDUAL_ID integer,
        primary key (ID)
    );

alter table CUSTOMER_PROFILE
    add index FK802A6E285A8F0AD3 (PROFILE_ID),
    add constraint FK802A6E285A8F0AD3
    foreign key (PROFILE_ID)
    references PROFILE (ID);

alter table CUSTOMER_PROFILE
        add index FK802A6E28E2F54E61 (CUSTOMER_ID),
        add constraint FK802A6E28E2F54E61
        foreign key (CUSTOMER_ID)
        references CUSTOMER (ID);

alter table PROFILE
        add constraint INDIVIDUAL_ID_ unique (INDIVIDUAL_ID);

alter table PROFILE
        add index FK185A1589BDB052C1 (INDIVIDUAL_ID),
        add constraint FK185A1589BDB052C1
        foreign key (INDIVIDUAL_ID)
        references INDIVIDUAL (ID);