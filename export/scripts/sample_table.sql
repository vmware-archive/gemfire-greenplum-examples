drop table if exists export;
create table export (
	id serial,
	value text
) distributed by (id);

insert into export (value) values ('Alice');
insert into export (value) values ('Bob');
insert into export (value) values ('Charlie');
insert into export (value) values ('Eve');

insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;

insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;

insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;
insert into export (value) select value from export;
