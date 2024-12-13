drop
    database if exists sm;
create
    database sm;

use
    sm;

create table class
(
    id             bigint auto_increment
        primary key,
    course_id      bigint        not null,
    total_students int default 0 not null,
    semester       varchar(255)  not null,
    class_id       bigint        null
);

create table course
(
    id          bigint auto_increment
        primary key,
    course_code varchar(255) not null,
    course_name varchar(255) not null,
    course_id   bigint       null,
    description varchar(255) null
);

create table user
(
    id         bigint auto_increment
        primary key,
    name       varchar(255)       not null,
    gender     varchar(10)        not null,
    email      varchar(255)       null,
    identifier bigint             null,
    password   varchar(255)       null,
    user_role  smallint default 2 not null
);

create table score
(
    id            bigint auto_increment
        primary key,
    regular_score int    not null,
    midterm_score int    not null,
    lab_score     int    not null,
    final_score   int    not null,
    total_score   int    not null,
    value         double null,
    classes_id    bigint null,
    student_id    bigint null,
    constraint FK63n0cgwksn1i65xekx8re5hxk
        foreign key (classes_id) references class (id),
    constraint FKnap51mbove93yjb09idc9jic6
        foreign key (student_id) references user (id)
);

create table user_class___mapping
(
    user_id    bigint not null,
    class___id bigint not null,
    constraint FKauni2ax6ngqp4w4n8fd2uik13
        foreign key (user_id) references user (id),
    constraint FKhn5wwq1olymr64p4acqthj9ba
        foreign key (class___id) references class (id)
);

insert into user (id, name, gender, email, identifier, password, user_role)
values (1, 'super_user', 'Male', 'super_user@gmail.com', 100001, '21a80bcb4d7a906834fe8854e1737cf61ec28f03e8dde247a2aa203ee2c4aad8', 1);