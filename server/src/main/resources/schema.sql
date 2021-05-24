if not exists (select * from sysobjects where id = object_id('users') 
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table users
(
    id char(256) primary key,
    username varchar(30) not null,
    CONSTRAINT check_users_username CHECK (LEN(username) BETWEEN 4 and 30),
    password varchar(30) not null,
    CONSTRAINT check_users_password CHECK (LEN(username) BETWEEN 5 and 30)
)
    go
