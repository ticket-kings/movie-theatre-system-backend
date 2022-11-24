CREATE SCHEMA IF NOT EXISTS movie_theatre_system;

CREATE TABLE IF NOT EXISTS payment(
	id int PRIMARY KEY auto_increment,
    card_id int NOT NULL,
    amount FLOAT NOT NULL,
    payment_type char(1) NOT NULL
);

INSERT INTO payment (card_id, amount, payment_type)
VALUES
	(1, 20, "A"),
	(1, 10, "T"),
    (2, 10, "T"),
    (3, 10, "T");
    
CREATE TABLE IF NOT EXISTS card (
	id int PRIMARY KEY auto_increment,
    card_number varchar(16) NOT NULL,
    expiry_date varchar(4) NOT NULL,
    cvv varchar(3) NOT NULL
);

INSERT INTO card (card_number, expiry_date, cvv) 
VALUES
	("1234123412341234", "0123", "123"),
    ("1111222233334444", "0123", "999"),
    ("4444333322221111", "0123", "999");
    
CREATE TABLE IF NOT EXISTS credit (
	id int PRIMARY KEY auto_increment,
    amount FLOAT NOT NULL,
    expiry_date datetime NOT NULL
);

INSERT INTO credit (amount, expiry_date)
VALUES
	(2.50, "2022-11-24 12:00:00");
    
CREATE TABLE IF NOT EXISTS user (
	id int PRIMARY KEY auto_increment,
    name varchar(30) NOT NULL,
    email_address varchar(50) NOT NULL,
    credit_id int,
    card_id int,
    CONSTRAINT FOREIGN KEY (credit_id) REFERENCES credit (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (card_id) REFERENCES card (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO user (name, email_address, credit_id, card_id) 
VALUES 
	("Trevor", "trevor.le1@ucalgary.ca", 1, 1),
    ("Levi", "levi@gmail.com", null, null),
    ("Jaime", "jaime@gmail.com", null, null),
    ("Shelly", "shelly@gmail.com", null, null);

CREATE TABLE IF NOT EXISTS guest_user (
	user_id int,
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO guest_user 
VALUES 
	(2),
    (3),
    (4);

CREATE TABLE IF NOT EXISTS registered_user (
	user_id int,
    address varchar (100) NOT NULL,
    password varchar(30) NOT NULL,
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO registered_user (user_id, address, password)
VALUES 
    (1, "124 Conch Street", "1");

CREATE TABLE IF NOT EXISTS movie (
	id int PRIMARY KEY auto_increment,
    name varchar(50) NOT NULL,
    description varchar(200) NOT NULL,
    duration int NOT NULL,
    is_released boolean NOT NULL
);

INSERT INTO movie (name, description, duration, is_released)
VALUES 
    ("Black Panther: Wakanda Forever", "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death.", 161, false),
    ("Avatar: The Way of Water", "Jake Sully and Ney'tiri have formed a family and are doing everything to stay together. However, they must leave their home and explore the regions of Pandora.", 190, false),
    ("Black Adam", "In ancient Kahndaq, Teth Adam was bestowed the almighty powers of the gods. After using these powers for vengeance, he was imprisoned, becoming Black Adam.", 125, false);

CREATE TABLE IF NOT EXISTS theatre (
	id int PRIMARY KEY auto_increment,
    name varchar(50) NOT NULL
);

INSERT INTO theatre (name)
VALUES 
	("Ticket Kings Cinemas");

CREATE TABLE IF NOT EXISTS showtime(
	id int PRIMARY KEY auto_increment,
    time DATETIME NOT NULL
);

INSERT INTO showtime (time)
VALUES 
	('2022-12-01 20:00:00'),
    ('2022-12-02 20:00:00'),
    ('2022-12-01 20:00:00'),
    ('2022-12-02 20:00:00'),
    ('2022-12-01 20:00:00'),
    ('2022-12-02 20:00:00');

CREATE TABLE IF NOT EXISTS showing (
	movie_id int NOT NULL,
    theatre_id int NOT NULL,
    showtime_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (theatre_id) REFERENCES theatre (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (showtime_id) REFERENCES showtime (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO showing (movie_id, theatre_id, showtime_id)
VALUES 
	(1, 1, 1),
    (1, 1, 2),
    (2, 1, 3),
    (2, 1, 4),
    (3, 1, 5),
    (3, 1, 6);

CREATE TABLE IF NOT EXISTS seat(
	id int PRIMARY KEY auto_increment,
    price FLOAT NOT NULL,
    seat_number varchar(3) NOT NULL,
    reserved boolean NOT NULL,
    available boolean NOT NULL,
    showtime_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (showtime_id) REFERENCES showtime (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO seat (price, seat_number, reserved, available, showtime_id)
VALUES
	(10, "A1",  true, false, 1),
    (10, "A2",  true,  true, 1),
    (10, "A3", false, false, 1),
    (10, "A4", false, false, 1),
    (10, "A5", false,  true, 1),
    (10, "B1", false,  true, 1),
    (10, "B2", false,  true, 1),
    (10, "B3", false,  true, 1),
    (10, "B4", false,  true, 1),
    (10, "B5", false,  true, 1),
	(10, "A1",  true,  true, 2),
    (10, "A2",  true,  true, 2),
    (10, "A3", false,  true, 2),
    (10, "A4", false,  true, 2),
    (10, "A5", false,  true, 2),
    (10, "B1", false,  true, 2),
    (10, "B2", false,  true, 2),
    (10, "B3", false,  true, 2),
    (10, "B4", false,  true, 2),
    (10, "B5", false,  true, 2),
	(10, "A1",  true,  true, 3),
    (10, "A2",  true,  true, 3),
    (10, "A3", false,  true, 3),
    (10, "A4", false,  true, 3),
    (10, "A5", false,  true, 3),
    (10, "B1", false,  true, 3),
    (10, "B2", false,  true, 3),
    (10, "B3", false,  true, 3),
    (10, "B4", false,  true, 3),
    (10, "B5", false,  true, 3),
	(10, "A1",  true,  true, 4),
    (10, "A2",  true,  true, 4),
    (10, "A3", false,  true, 4),
    (10, "A4", false,  true, 4),
    (10, "A5", false,  true, 4),
    (10, "B1", false,  true, 4),
    (10, "B2", false,  true, 4),
    (10, "B3", false,  true, 4),
    (10, "B4", false,  true, 4),
    (10, "B5", false,  true, 4),
	(10, "A1",  true,  true, 5),
    (10, "A2",  true,  true, 5),
    (10, "A3", false,  true, 5),
    (10, "A4", false,  true, 5),
    (10, "A5", false,  true, 5),
    (10, "B1", false,  true, 5),
    (10, "B2", false,  true, 5),
    (10, "B3", false,  true, 5),
    (10, "B4", false,  true, 5),
    (10, "B5", false,  true, 5),
	(10, "A1",  true,  true, 6),
    (10, "A2",  true,  true, 6),
    (10, "A3", false,  true, 6),
    (10, "A4", false,  true, 6),
    (10, "A5", false,  true, 6),
    (10, "B1", false,  true, 6),
    (10, "B2", false,  true, 6),
    (10, "B3", false,  true, 6),
    (10, "B4", false,  true, 6),
    (10, "B5", false,  true, 6);

CREATE TABLE IF NOT EXISTS ticket(
	id int PRIMARY KEY auto_increment,
    seat_id int NOT NULL,
    payment_id int NOT NULL,
    CONSTRAINT FOREIGN KEY (seat_id) REFERENCES seat (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (payment_id) REFERENCES payment (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO ticket (seat_id, payment_id)
VALUES
	(1, 2),
    (3, 3),
    (4, 4);

