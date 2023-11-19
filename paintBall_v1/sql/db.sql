create sequence _orders_seq start with 1 increment by 50;
create sequence _playground_seq start with 1 increment by 50;
create sequence _user_seq start with 1 increment by 50;
create table _playground (
                             area integer not null,
                             capacity integer not null,
                             id integer not null,
                             price integer not null,
                             description varchar(255),
                             name varchar(255),
                             primary key (id)
);
create table _user (
                       id integer not null,
                       email varchar(255),
                       login varchar(255),
                       password varchar(255),
                       role varchar(255) check (role in ('ADMIN','USER')),
                       primary key (id)
);

create table _orders (
                         id integer not null,
                         playground_id integer,
                         user_id integer,
                         order_date varchar(255),
                         primary key (id),
                         foreign key (playground_id) references _playground (id),
                         foreign key (user_id) references _user (id)
);