--liquibase formatted sql

--changeset pavel:1
create table candidates(
    id_candidate bigint unique not null primary key,
    first_name_candidate text not null,
    second_name_candidate text,
    username_candidate text,
    phone_number text unique
);

create table dogs(
    id_dog bigserial unique not null primary key,
    name_dog text not null,
    age real
);

create table volunteers(
    id_volunteer bigint unique not null primary key,
    first_name_volunteer text not null,
    second_name_volunteer text,
    username_volunteer text,
    password varchar(16) not null
);

create table reports(
    id_report bigserial unique not null primary key,
    path_image text,
    diet text,
    general_health text,
    date date,
    time time   
);

create table periods(
    id_period bigint unique not null primary key,
    start_date date not null,
    trial_days int default 30,
    extra_days int
);

-- changeSet ivan:2

CREATE TABLE dog_candidates
(
    id_candidate            bigint PRIMARY KEY,
    first_name_candidate    text not null,
    second_name_candidate   text,
    username_candidate      TEXT,
    phone_number            TEXT UNIQUE
);

CREATE TABLE cat_candidates
(
    id_candidate            bigint unique not null PRIMARY KEY,
    first_name_candidate    text not null,
    second_name_candidate   text,
    username_candidate      TEXT,
    phone_number            TEXT UNIQUE
);

   



