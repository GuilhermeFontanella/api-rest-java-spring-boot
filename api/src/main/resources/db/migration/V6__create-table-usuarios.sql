CREATE TABLE usuarios(
    id bigint not null auto_increment,
    login varchar(100) unique not null,
    senha varchar(20) not null,

    primary key(id)
);