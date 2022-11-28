CREATE TABLE IF NOT EXISTS movie_theatre_system.card (
	id int PRIMARY KEY auto_increment,
    card_number varchar(16) NOT NULL,
    expiry_date varchar(4) NOT NULL,
    cvv varchar(3) NOT NULL
);

INSERT INTO movie_theatre_system.card (id, card_number, expiry_date, cvv)
VALUES
	(1, "1234123412341234", "0123", "123"),
    (2, "1111222233334444", "0123", "999"),
    (3, "4444333322221111", "0123", "999"),
    (4, "5555666677778888", "0123", "321");

CREATE TABLE IF NOT EXISTS movie_theatre_system.payment(
	id int PRIMARY KEY auto_increment,
    card_id int NOT NULL,
    amount FLOAT NOT NULL,
    payment_date datetime NOT NULL,
    CONSTRAINT FOREIGN KEY (card_id) REFERENCES card (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.payment (id, card_id, amount, payment_date)
VALUES
	(1, 1, 20, "2022-11-24 10:00:00"),
	(2, 1, 10, "2022-11-24 10:30:00"),
    (3, 2, 10, "2022-11-11 11:11:11"),
    (4, 3, 10, "2022-11-20 20:00:00");

CREATE TABLE IF NOT EXISTS movie_theatre_system.annual_payment(
	payment_id int NOT NULL,
    next_payment_date DATETIME NOT NULL,
    CONSTRAINT FOREIGN KEY (payment_id) REFERENCES payment (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.annual_payment(payment_id, next_payment_date)
VALUES
	(1, "2023-11-24 10:00:00");

CREATE TABLE IF NOT EXISTS movie_theatre_system.ticket_payment(
	payment_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (payment_id) REFERENCES payment (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.ticket_payment(payment_id)
VALUES
	(2),
    (3),
    (4);

CREATE TABLE IF NOT EXISTS movie_theatre_system.credit (
	id int PRIMARY KEY auto_increment,
    amount FLOAT NOT NULL,
    expiry_date datetime NOT NULL
);

INSERT INTO movie_theatre_system.credit (id, amount, expiry_date)
VALUES
	(1, 2.50, "2023-11-24 12:00:00");
    
CREATE TABLE IF NOT EXISTS movie_theatre_system.user (
	id int PRIMARY KEY auto_increment,
    name varchar(30) NOT NULL,
    email_address varchar(50) NOT NULL,
    card_id int,
    CONSTRAINT FOREIGN KEY (card_id) REFERENCES card (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.user (id, name, email_address, card_id)
VALUES 
	(1, "Trevor", "trevor.le1@ucalgary.ca", 1),
    (2, "Levi", "levi@gmail.com", 2),
    (3, "Jaime", "jaime@gmail.com", 3),
    (4, "Shelly", "shelly@gmail.com", 4);

CREATE TABLE IF NOT EXISTS movie_theatre_system.guest_user (
	user_id int,
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.guest_user 
VALUES 
	(2),
    (3),
    (4);

CREATE TABLE IF NOT EXISTS movie_theatre_system.registered_user (
	user_id int,
    address varchar (100) NOT NULL,
    password varchar(30) NOT NULL,
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.registered_user (user_id, address, password)
VALUES 
    (1, "124 Conch Street", "1");

CREATE TABLE IF NOT EXISTS movie_theatre_system.movie (
	id int PRIMARY KEY auto_increment,
    name varchar(50) NOT NULL,
    image_url varchar(250) NOT NULL,
    description varchar(200) NOT NULL,
    duration int NOT NULL,
    is_released boolean NOT NULL
);

INSERT INTO movie_theatre_system.movie (id, name, image_url, description, duration, is_released)
VALUES 
    (1, "Black Panther: Wakanda Forever", "https://m.media-amazon.com/images/M/MV5BNTM4NjIxNmEtYWE5NS00NDczLTkyNWQtYThhNmQyZGQzMjM0XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_FMjpg_UX1000_.jpg", "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death.", 161, true),
    (2, "Avatar: The Way of Water", "https://preview.redd.it/k2u12aobjgy81.jpg?auto=webp&s=c1b783b53f910ee9a18c451a38015ce5c9c1f7fc", "Jake Sully and Ney'tiri have formed a family and are doing everything to stay together. However, they must leave their home and explore the regions of Pandora.", 190, false),
    (3, "Black Adam", "https://www.theilluminerdi.com/wp-content/uploads/2022/09/black-adam-heroes.jpg", "In ancient Kahndaq, Teth Adam was bestowed the almighty powers of the gods. After using these powers for vengeance, he was imprisoned, becoming Black Adam.", 125, false);

CREATE TABLE IF NOT EXISTS movie_theatre_system.theatre (
	id int PRIMARY KEY auto_increment,
    name varchar(50) NOT NULL
);

INSERT INTO movie_theatre_system.theatre (id, name)
VALUES 
	(1, "Ticket Kings Cinemas");

CREATE TABLE IF NOT EXISTS movie_theatre_system.showtime(
	id int PRIMARY KEY auto_increment,
    time DATETIME NOT NULL
);

INSERT INTO movie_theatre_system.showtime (id, time)
VALUES 
	(1, '2022-12-01 20:00:00'),
    (2, '2022-12-02 20:00:00'),
    (3, '2022-12-01 20:00:00'),
    (4, '2022-12-02 20:00:00'),
    (5, '2022-12-01 20:00:00'),
    (6, '2022-11-30 20:00:00');

CREATE TABLE IF NOT EXISTS movie_theatre_system.showing (
	id int PRIMARY KEY auto_increment,
	movie_id int NOT NULL,
    theatre_id int NOT NULL,
    showtime_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (theatre_id) REFERENCES theatre (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (showtime_id) REFERENCES showtime (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.showing (id, movie_id, theatre_id, showtime_id)
VALUES 
	(1, 1, 1, 1),
    (2, 1, 1, 2),
    (3, 2, 1, 3),
    (4, 2, 1, 4),
    (5, 3, 1, 5),
    (6, 3, 1, 6);

CREATE TABLE IF NOT EXISTS movie_theatre_system.seat(
	id int PRIMARY KEY auto_increment,
    price FLOAT NOT NULL,
    seat_number varchar(3) NOT NULL,
    premium boolean NOT NULL,
    reserved boolean NOT NULL,
    showtime_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (showtime_id) REFERENCES showtime (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.seat (id, price, seat_number, premium, reserved, showtime_id)
VALUES
	(1, 10, "A1",  true,  true, 1),
    (2, 10, "A2",  true, false, 1),
    (3, 10, "A3", false,  true, 1),
    (4, 10, "A4", false,  true, 1),
    (5, 10, "A5", false, false, 1),
    (6, 10, "B1", false, false, 1),
    (7, 10, "B2", false, false, 1),
    (8, 10, "B3", false, false, 1),
    (9, 10, "B4", false, false, 1),
    (10, 10, "B5", false, false, 1),
	(11, 10, "A1",  true, false, 2),
    (12, 10, "A2",  true, false, 2),
    (13, 10, "A3", false, false, 2),
    (14, 10, "A4", false, false, 2),
    (15, 10, "A5", false, false, 2),
    (16, 10, "B1", false, false, 2),
    (17, 10, "B2", false, false, 2),
    (18, 10, "B3", false, false, 2),
    (19, 10, "B4", false, false, 2),
    (20, 10, "B5", false, false, 2),
	(21, 10, "A1",  true, false, 3),
    (22, 10, "A2",  true, false, 3),
    (23, 10, "A3", false, false, 3),
    (24, 10, "A4", false, false, 3),
    (25, 10, "A5", false, false, 3),
    (26, 10, "B1", false, false, 3),
    (27, 10, "B2", false, false, 3),
    (28, 10, "B3", false, false, 3),
    (29, 10, "B4", false, false, 3),
    (30, 10, "B5", false, false, 3),
	(31, 10, "A1",  true, false, 4),
    (32, 10, "A2",  true, false, 4),
    (33, 10, "A3", false, false, 4),
    (34, 10, "A4", false, false, 4),
    (35, 10, "A5", false, false, 4),
    (36, 10, "B1", false, false, 4),
    (37, 10, "B2", false, false, 4),
    (38, 10, "B3", false, false, 4),
    (39, 10, "B4", false, false, 4),
    (40, 10, "B5", false, false, 4),
	(41, 10, "A1",  true, false, 5),
    (42, 10, "A2",  true, false, 5),
    (43, 10, "A3", false, false, 5),
    (44, 10, "A4", false, false, 5),
    (45, 10, "A5", false, false, 5),
    (46, 10, "B1", false, false, 5),
    (47, 10, "B2", false, false, 5),
    (48, 10, "B3", false, false, 5),
    (49, 10, "B4", false, false, 5),
    (50, 10, "B5", false, false, 5),
	(51, 10, "A1",  true, false, 6),
    (52, 10, "A2",  true, false, 6),
    (53, 10, "A3", false, false, 6),
    (54, 10, "A4", false, false, 6),
    (55, 10, "A5", false, false, 6),
    (56, 10, "B1", false, false, 6),
    (57, 10, "B2", false, false, 6),
    (58, 10, "B3", false, false, 6),
    (59, 10, "B4", false, false, 6),
    (60, 10, "B5", false, false, 6);

CREATE TABLE IF NOT EXISTS movie_theatre_system.ticket(
	id int PRIMARY KEY auto_increment,
    seat_id int NOT NULL,
    payment_id int NOT NULL,
    credit_id int,
    CONSTRAINT FOREIGN KEY (seat_id) REFERENCES seat (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (payment_id) REFERENCES ticket_payment (payment_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (credit_id) REFERENCES credit (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.ticket (id, seat_id, payment_id, credit_id)
VALUES
	(1, 1, 2, null),
    (2, 3, 3, null),
    (3, 4, 4, null);

