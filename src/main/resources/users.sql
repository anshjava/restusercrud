CREATE TABLE IF NOT EXISTS users (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
birthdate DATE NOT NULL
);

INSERT INTO users (firstname,lastname,birthdate) VALUES
('Andrey','Shevchenko','1990-02-18'),
('Evgeniy','Sharov','1975-01-01'),
('Anton','Gorobec','1978-02-01'),
('Nadezhda','Frolova','1985-03-28'),
('Svetlana','Popova','1980-05-10');
