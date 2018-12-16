CREATE TABLE users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	date_of_birth DATE
);

INSERT INTO users VALUES (1, 'Manuel', 'Iannone', '1987-01-20');
INSERT INTO users VALUES (2, 'Paolino', 'Paperino', '1920-06-09');
INSERT INTO users VALUES (3, 'Paperon', 'de Paperoni', '1867-01-01');