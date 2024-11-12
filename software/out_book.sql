create table software.out_book
(
    out_id          int auto_increment
        primary key,
    out_borrower_id int  null,
    out_bookid      int  null,
    out_quantity    int  null,
    out_date        date null,
    out_to_id       int  null
);

