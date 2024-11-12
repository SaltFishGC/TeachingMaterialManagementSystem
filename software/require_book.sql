create table software.require_book
(
    req_id          int auto_increment
        primary key,
    req_demender_id int  null,
    req_isfinished  int  null,
    req_book_id     int  null,
    req_quantity    int  null,
    req_date        date null,
    req_reason      text null,
    req_to_id       int  null
);

