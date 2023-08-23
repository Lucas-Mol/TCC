CREATE TABLE usuarios(
    id SERIAL,
    username varchar(50) NOT NULL,
    password varchar NOT NULL,
    PRIMARY KEY (id)
);