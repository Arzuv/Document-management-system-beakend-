create database document;

CREATE TYPE ROLES_TYPE as ENUM ('USER', 'ADMIN');
CREATE TYPE STATUS_TYPE as ENUM ('ACTIVE', 'NOT_ACTIVE', 'DELETED')
create table users (
    id SERIAL  primary key,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    created TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP,
    updated TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP,
    status STATUS_TYPE default 'ACTIVE'
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
    status STATUS_TYPE default 'ACTIVE',
    FOREIGN key (user_id) REFERENCES users (id) ON DELETE CASCADE
);

insert into users (email, password, firstname, lastname) 
            VALUES ('admin@mail.com', '12345', 'admin', 'admin');
insert into users (email, password, firstname, lastname) 
            VALUES ('user@mail.com', '12345', 'user', 'user');
insert into users (email, password, firstname, lastname) 
            VALUES ('maccalen@mail.com', '12345', 'maccalen', 'tomos');
insert into users (email, password, firstname, lastname) 
            VALUES ('jack@mail.com', '12345', 'adam', 'jack');            
insert into users (email, password, firstname, lastname) 
            VALUES ('twopack@mail.com', '12345', 'two', 'pack');            
INSERT INTO roles (user_id, my_role) VALUES (5, 'admin');