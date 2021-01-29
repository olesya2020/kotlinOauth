create table if not exists test.role
(
    id serial not null
    constraint role_pk
    primary key,
    name varchar(20) not null
    );

alter table test.role owner to postgres;

create unique index if not exists role_id_uindex
	on test.role (id);

create table if not exists test.user
(
    id serial not null
    constraint user_pk
    primary key,
    username varchar(50),
    password varchar(100),
    roleId int not null references test.role (id)
   );

alter table test.user owner to postgres;

create unique index if not exists user_id_uindex
	on test.user (id);

insert into test.role (name) values
('ROLE_ADMIN'),
('ROLE_USER');