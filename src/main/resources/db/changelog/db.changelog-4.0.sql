-- liquibase formatted sql

--changeset roman_gurinovich:1
delete from hotel_amenity;
delete from hotels;
delete from addresses;
delete from countries;
delete from amenities;
delete from arrival_times;
delete from brands;