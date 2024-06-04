-- liquibase formatted sql

--changeset roman_gurinovich:1
ALTER TABLE hotels RENAME COLUMN arrival_time TO arrival_time_id;
    
--changeset roman_gurinovich:2
INSERT INTO arrival_times(id, check_in, check_out) VALUES (1, '14:00', '12:00');

INSERT INTO brands(id, name) VALUES (1, 'Hilton');

INSERT INTO countries(id, name) VALUES (1, 'Belarus');

INSERT INTO addresses(id, country_id, city, street, house_number, post_code)
VALUES (
        1, 1, 'Minsk',
        'Pobediteley Avenue',
        9, '220004'
       );

INSERT INTO amenities(id, name) VALUES
                                    (1, 'Free parking'),
                                    (2, 'Free WiFi'),
                                    (3, 'Non-smoking rooms'),
                                    (4, 'Concierge'),
                                    (5, 'On-site restaurants'),
                                    (6, 'Fitness center'),
                                    (7, 'Pet-friendly rooms'),
                                    (8, 'Room service'),
                                    (9, 'Business center'),
                                    (10, 'Meeting rooms');

INSERT INTO hotels(id, brand_id, name, description, address_id, phone, email, arrival_time_id)
VALUES (
        1, 1, 'DoubleTree by Hilton Minsk',
        'The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel''s 20th floor ...',
        1, '+375 17 309-80-00', 'doubletreeminsk.info@hilton.com', 1
       );

INSERT INTO hotel_amenity(hotel_id, amenity_id) VALUES
                              (1, 1),
                              (1, 2),
                              (1, 3),
                              (1, 4),
                              (1, 5),
                              (1, 6),
                              (1, 7),
                              (1, 8),
                              (1, 9),
                              (1, 10);
