create database document;

CREATE TYPE ROLES_TYPE as ENUM ('USER', 'ADMIN');
create table users (
    id SERIAL  primary key,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    created TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP,
    updated TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP,
    status SMALLINT default 0
);

create table roles (
    id SERIAL primary key,
    user_id INTEGER not NULL,
    my_role ROLES_TYPE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

create table documents (
    id SERIAL primary key,
    user_id INTEGER not null,
    dname VARCHAR not null,
    whomContract VARCHAR NOT NULL,
    whoContracted VARCHAR NOT NULL,
    termOfExecution DATE NOT NULL,
    dfile bytea,
    created TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP,
    updated TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP,
    status SMALLINT default 0,
    FOREIGN key (user_id) REFERENCES users (id) ON DELETE CASCADE
);

insert into users (email, password, firstname, lastname) 
            VALUES ('admin@mail.com', '12345', 'admin', 'admin');
insert into users (email, password, firstname, lastname) 
            VALUES ('user@mail.com', '12345', 'user', 'user');
         
INSERT INTO roles (user_id, my_role) VALUES (1, 'ADMIN');
INSERT INTO roles (user_id, my_role) VALUES (2, 'USER');