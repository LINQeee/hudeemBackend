create table hudeem.user
(
    id bigint not null primary key auto_increment,
    username text,
    password text,
    gender char(1),
    height double,
    initial_weight double,
    goal_weight double
);


create table hudeem.record
(
    id             bigint not null primary key auto_increment,
    current_weight double,
    date           date,
    user_id        bigint not null,
    foreign key (user_id) references user (id)

);

alter table hudeem.user add column (progress double);

alter table hudeem.record drop column id;

alter table hudeem.record modify date date not null primary key;