create table if not exists passport
(
    id            bigint auto_increment primary key,
    serial_number varchar(255)
);

create table if not exists role
(
    id   bigint auto_increment primary key,
    name varchar(20) not null
);

create table if not exists system_user
(
    id          bigint auto_increment primary key,
    login       varchar(255),
    password    varchar(255),
    first_name  varchar(255),
    last_name   varchar(255),
    passport_id bigint,
    role_id     bigint,
    constraint passport_id_fk foreign key (passport_id) references passport (id),
    constraint role_id_fk foreign key (role_id) references role (id)
);

create table if not exists task
(
    id           bigint auto_increment primary key,
    created_date date,
    description  varchar(255),
    end_date     date,
    name         varchar(255),
    start_date   date,
    status       varchar(255),
    user_id      bigint,
    constraint task_user_id_fk foreign key (user_id) references system_user (id)
);
