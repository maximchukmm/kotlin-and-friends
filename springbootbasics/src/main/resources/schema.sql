create table author
(
  id   bigint  not null
    constraint author_pkey
    primary key,
  full_name varchar not null
);

create table book
(
  id        bigint  not null
    constraint book_pkey
    primary key,
  title     varchar not null,
  author_id bigint  not null
    constraint book_author_id_fk
    references author
);

create unique index book_title_uindex
  on book (title);