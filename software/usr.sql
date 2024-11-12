create table software.usr
(
    user_id       int auto_increment
        primary key,
    user_name     varchar(255) null,
    user_type     varchar(255) null,
    user_phone    varchar(255) null,
    user_email    varchar(255) null,
    user_account  varchar(255) null,
    user_password varchar(255) null
);

