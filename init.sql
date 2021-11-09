\connect "test-spring";

create table users (
id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

create table documents(
id integer primary key not null,
user_id integer not null,
name varchar(255) not null,
content text not null,
type varchar(20),
upload_date timestamp with time zone default current_timestamp
);

alter table documents add constraint doc_users_fk
foreign key (user_id) references users(id);


create sequence users_seq increment 1 start 1;
create sequence documents_seq increment 1 start 1;