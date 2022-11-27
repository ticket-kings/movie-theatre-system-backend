DROP DATABASE IF EXISTS movie_theatre_system;

CREATE DATABASE IF NOT EXISTS movie_theatre_system;

CREATE TABLE IF NOT EXISTS movie_theatre_system.card (
	id int PRIMARY KEY auto_increment,
    card_number varchar(16) NOT NULL,
    expiry_date varchar(4) NOT NULL,
    cvv varchar(3) NOT NULL
);

INSERT INTO movie_theatre_system.card (card_number, expiry_date, cvv) 
VALUES
	("1234123412341234", "0123", "123"),
    ("1111222233334444", "0123", "999"),
    ("4444333322221111", "0123", "999");

CREATE TABLE IF NOT EXISTS movie_theatre_system.payment(
	id int PRIMARY KEY auto_increment,
    card_id int NOT NULL,
    amount FLOAT NOT NULL,
    payment_date datetime NOT NULL,
    CONSTRAINT FOREIGN KEY (card_id) REFERENCES card (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.payment (card_id, amount, payment_date)
VALUES
	(1, 20, "2022-11-24 10:00:00"),
	(1, 10, "2022-11-24 10:30:00"),
    (2, 10, "2022-11-11 11:11:11"),
    (3, 10, "2022-11-20 20:00:00");

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

INSERT INTO movie_theatre_system.credit (amount, expiry_date)
VALUES
	(2.50, "2023-11-24 12:00:00");
    
CREATE TABLE IF NOT EXISTS movie_theatre_system.user (
	id int PRIMARY KEY auto_increment,
    name varchar(30) NOT NULL,
    email_address varchar(50) NOT NULL,
    credit_id int,
    card_id int,
    CONSTRAINT FOREIGN KEY (credit_id) REFERENCES credit (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (card_id) REFERENCES card (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.user (name, email_address, credit_id, card_id) 
VALUES 
	("Trevor", "trevor.le1@ucalgary.ca", 1, 1),
    ("Levi", "levi@gmail.com", null, 2),
    ("Jaime", "jaime@gmail.com", null, 3),
    ("Shelly", "shelly@gmail.com", null, null);

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

INSERT INTO movie_theatre_system.movie (name, image_url, description, duration, is_released)
VALUES 
    ("Black Panther: Wakanda Forever", "https://m.media-amazon.com/images/M/MV5BNTM4NjIxNmEtYWE5NS00NDczLTkyNWQtYThhNmQyZGQzMjM0XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_FMjpg_UX1000_.jpg", "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death.", 161, true),
    ("Avatar: The Way of Water", "https://preview.redd.it/k2u12aobjgy81.jpg?auto=webp&s=c1b783b53f910ee9a18c451a38015ce5c9c1f7fc", "Jake Sully and Ney'tiri have formed a family and are doing everything to stay together. However, they must leave their home and explore the regions of Pandora.", 190, false),
    ("Black Adam", "https://www.theilluminerdi.com/wp-content/uploads/2022/09/black-adam-heroes.jpg", "In ancient Kahndaq, Teth Adam was bestowed the almighty powers of the gods. After using these powers for vengeance, he was imprisoned, becoming Black Adam.", 125, false);

CREATE TABLE IF NOT EXISTS movie_theatre_system.theatre (
	id int PRIMARY KEY auto_increment,
    name varchar(50) NOT NULL
);

INSERT INTO movie_theatre_system.theatre (name)
VALUES 
	("Ticket Kings Cinemas");

CREATE TABLE IF NOT EXISTS movie_theatre_system.showtime(
	id int PRIMARY KEY auto_increment,
    time DATETIME NOT NULL
);

INSERT INTO movie_theatre_system.showtime (time)
VALUES 
	('2022-12-01 20:00:00'),
    ('2022-12-02 20:00:00'),
    ('2022-12-01 20:00:00'),
    ('2022-12-02 20:00:00'),
    ('2022-12-01 20:00:00'),
    ('2022-12-02 20:00:00');

CREATE TABLE IF NOT EXISTS movie_theatre_system.showing (
	id int PRIMARY KEY auto_increment,
	movie_id int NOT NULL,
    theatre_id int NOT NULL,
    showtime_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (theatre_id) REFERENCES theatre (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (showtime_id) REFERENCES showtime (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.showing (movie_id, theatre_id, showtime_id)
VALUES 
	(1, 1, 1),
    (1, 1, 2),
    (2, 1, 3),
    (2, 1, 4),
    (3, 1, 5),
    (3, 1, 6);

CREATE TABLE IF NOT EXISTS movie_theatre_system.seat(
	id int PRIMARY KEY auto_increment,
    price FLOAT NOT NULL,
    seat_number varchar(3) NOT NULL,
    premium boolean NOT NULL,
    reserved boolean NOT NULL,
    showtime_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (showtime_id) REFERENCES showtime (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.seat (price, seat_number, premium, reserved, showtime_id)
VALUES
	(10, "A1",  true,  true, 1),
    (10, "A2",  true, false, 1),
    (10, "A3", false,  true, 1),
    (10, "A4", false,  true, 1),
    (10, "A5", false, false, 1),
    (10, "B1", false, false, 1),
    (10, "B2", false, false, 1),
    (10, "B3", false, false, 1),
    (10, "B4", false, false, 1),
    (10, "B5", false, false, 1),
	(10, "A1",  true, false, 2),
    (10, "A2",  true, false, 2),
    (10, "A3", false, false, 2),
    (10, "A4", false, false, 2),
    (10, "A5", false, false, 2),
    (10, "B1", false, false, 2),
    (10, "B2", false, false, 2),
    (10, "B3", false, false, 2),
    (10, "B4", false, false, 2),
    (10, "B5", false, false, 2),
	(10, "A1",  true, false, 3),
    (10, "A2",  true, false, 3),
    (10, "A3", false, false, 3),
    (10, "A4", false, false, 3),
    (10, "A5", false, false, 3),
    (10, "B1", false, false, 3),
    (10, "B2", false, false, 3),
    (10, "B3", false, false, 3),
    (10, "B4", false, false, 3),
    (10, "B5", false, false, 3),
	(10, "A1",  true, false, 4),
    (10, "A2",  true, false, 4),
    (10, "A3", false, false, 4),
    (10, "A4", false, false, 4),
    (10, "A5", false, false, 4),
    (10, "B1", false, false, 4),
    (10, "B2", false, false, 4),
    (10, "B3", false, false, 4),
    (10, "B4", false, false, 4),
    (10, "B5", false, false, 4),
	(10, "A1",  true, false, 5),
    (10, "A2",  true, false, 5),
    (10, "A3", false, false, 5),
    (10, "A4", false, false, 5),
    (10, "A5", false, false, 5),
    (10, "B1", false, false, 5),
    (10, "B2", false, false, 5),
    (10, "B3", false, false, 5),
    (10, "B4", false, false, 5),
    (10, "B5", false, false, 5),
	(10, "A1",  true, false, 6),
    (10, "A2",  true, false, 6),
    (10, "A3", false, false, 6),
    (10, "A4", false, false, 6),
    (10, "A5", false, false, 6),
    (10, "B1", false, false, 6),
    (10, "B2", false, false, 6),
    (10, "B3", false, false, 6),
    (10, "B4", false, false, 6),
    (10, "B5", false, false, 6);

CREATE TABLE IF NOT EXISTS movie_theatre_system.ticket(
	id int PRIMARY KEY auto_increment,
    seat_id int NOT NULL,
    payment_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (seat_id) REFERENCES seat (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (payment_id) REFERENCES ticket_payment (payment_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO movie_theatre_system.ticket (seat_id, payment_id)
VALUES
	(1, 2),
    (3, 3),
    (4, 4);

