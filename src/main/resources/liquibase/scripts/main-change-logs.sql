--liquibase formatted sql

--changeset pavel:1
create table candidates(
    id_candidate bigint unique not null,
    first_name_candidate text not null,
    second_name_candidate text,
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
    first_name_volunteer text not null,
    second_name_volunteer text,
    username_volunteer text,
    password varchar(16) not null
);

create table reports(
    id_report bigserial unique not null,
    path_image text,
    diet text,
    general_health text,
    date date,
    candidate_id_candidate bigint references candidates(id_candidate)
);

create table periods(
    id_period bigserial unique not null,
    start_date date not null,
    trial_days int default 30,
    extra_days int
)
