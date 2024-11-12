create table software.buy_book
(
    buy_id       int auto_increment
        primary key,
    buy_buyer_id int  null,
    buy_book_id  int  null,
    buy_quantity int  null,
    buy_date     date null,
    buy_state    int  null
);

