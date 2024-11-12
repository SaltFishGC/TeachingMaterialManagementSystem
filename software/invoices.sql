create table software.invoices
(
    id     int auto_increment
        primary key,
    num    varchar(255) null,
    sec    varchar(255) null,
    date   datetime     null,
    req_id int          null
);

