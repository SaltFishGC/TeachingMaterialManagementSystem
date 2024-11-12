create table software.msg
(
    msg_id      int auto_increment
        primary key,
    msg_title   varchar(255) null,
    msg_content text         null,
    msg_from_id int          null,
    msg_to_id   int          null,
    msg_date    datetime     null,
    msg_state   tinyint(1)   null
);

