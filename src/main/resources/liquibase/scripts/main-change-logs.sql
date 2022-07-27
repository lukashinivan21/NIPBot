--liquibase formatted sql

--changeset pavel:1
create table candidates(
    id_candidate bigint unique not null,
    name_candidate text not null,
    username_candidate text,
    phone_number text unique
);

create table dogs(
    id_dog bigserial unique not null,
    name_dog text not null,
    age real
);

create table volunteers(
    id_volunteer bigint unique not null,
    name_volunteer text not null,
    username_volunteer text,
    password varchar(16) not null
);

create table reports(
    id_report bigserial unique not null,
    id_candidate bigint not null,
    path_image text,
    diet text,
    general_health text,
    date date,
    time time
)
