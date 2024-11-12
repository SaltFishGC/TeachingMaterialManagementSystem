create table software.book
(
    book_id          int auto_increment comment 'Primary Key'
        primary key,
    book_name        varchar(255)   null,
    book_author      varchar(255)   null,
    book_publisher   varchar(255)   null,
    book_publishdate date           null,
    book_price       decimal(10, 2) null,
    book_quantity    int            null,
    book_description text           null
);

