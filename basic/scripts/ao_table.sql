drop table if exists basic_ao;
create table basic_ao (	
	id	BIGINT,
	field text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field1 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field2 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field3 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field4 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field5 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field6 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field7 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field8 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768),
 	field9 text ENCODING (compresstype=zlib,compresslevel=5,blocksize=32768)

) WITH (appendonly=true, orientation=column)
distributed by (id);

CREATE SEQUENCE id_sequence;

ALTER SEQUENCE id_sequence OWNED BY basic_ao.id;

ALTER TABLE basic_ao
ALTER COLUMN id SET DEFAULT nextval('id_sequence');		


INSERT INTO basic_ao VALUES (1,'alice', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8','field8');
INSERT INTO basic_ao VALUES (2,'bob', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8', 'field8');
INSERT INTO basic_ao VALUES (3,'john', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8', 'field8');
INSERT INTO basic_ao VALUES (4,'jim', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8', 'field8');
INSERT INTO basic_ao VALUES (5,'vic', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8', 'field8');
INSERT INTO basic_ao VALUES (6,'jack', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8', 'field8');
INSERT INTO basic_ao VALUES (8,'alex', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8', 'field8');
INSERT INTO basic_ao VALUES (9,'alex', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8', 'field8');
INSERT INTO basic_ao VALUES (10,'alex', 'field1', 'field2', 'field3', 'field4', 'field5', 'field6','field7', 'field8', 'field8');


insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+10, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+20, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+30, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+40, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+50, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+60, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+70, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+80, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+90, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+100, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+110, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+120, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
insert into basic_ao (id, field, field1, field2, field3, field4, field5, field6, field7, field8, field9) select id+130, field, field1, field2, field3, field4, field5, field6, field7, field8, field9 from basic_ao;
