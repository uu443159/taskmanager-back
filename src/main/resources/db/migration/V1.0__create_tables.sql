create table if not exists task
(
    id bigint auto_increment
        primary key,
    description varchar(255) not null,
    title varchar(100) not null,
    user_name varchar(100) not null
)
    charset=utf8;

create table if not exists user_role
(
    id bigint auto_increment
        primary key,
    role_name varchar(255) not null,
    unique (role_name)
)
    charset=utf8;

create table if not exists user
(
    id bigint auto_increment
        primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    password varchar(255) not null,
    user_name varchar(100) not null,
    role_id bigint,
    unique (user_name),
    foreign key (role_id) references user_role (id)
)
    charset=utf8;