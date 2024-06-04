-- liquibase formatted sql

--changeset roman_gurinovich:1
CREATE TABLE brands
(
    id bigserial primary key,
    name varchar(255) not null
);

--changeset roman_gurinovich:2
CREATE TABLE countries
(
    id bigserial primary key,
    name varchar(170) not null
);

--changeset roman_gurinovich:3
CREATE TABLE addresses
(
    id bigserial primary key,
    country_id bigint references countries(id) not null,
    city varchar(255) not null,
    street varchar(255) not null,
    house_number varchar(255) not null,
    post_code varchar(255) not null
);


--changeset roman_gurinovich:4
CREATE TABLE amenities
(
    id bigserial primary key,
    name varchar(255) not null
);

--changeset roman_gurinovich:5
CREATE TABLE arrival_times
(
    id bigserial primary key,
    check_in time not null,
    check_out time
);

--changeset roman_gurinovich:6
CREATE TABLE hotels
(
    id bigserial primary key,
    brand_id bigint references brands(id) not null,
    name varchar(255) not null,
    description varchar(255) not null,
    address_id bigint references addresses(id) not null,
    phone varchar(30) not null,
    email varchar(255) not null,
    arrival_time bigint references arrival_times(id) not null
);

--changeset roman_gurinovich:7
CREATE TABLE hotel_amenity
(
    hotel_id bigint references hotels(id) not null,
    amenity_id bigint references amenities(id) not null,
    primary key (hotel_id, amenity_id)
);