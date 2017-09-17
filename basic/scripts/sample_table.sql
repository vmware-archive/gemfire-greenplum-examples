drop table if exists basic;
create table basic (	
	id	bigint,
	col1 	text,
	col2	float4
) distributed by (id);		
 

INSERT INTO basic VALUES (1, 'TEXT_1',  1);
INSERT INTO basic VALUES (2, 'TEXT_2',  2);
INSERT INTO basic VALUES (4, 'TEXT_3',  3);
INSERT INTO basic VALUES (5, 'TEXT_4',  4);
INSERT INTO basic VALUES (6, 'TEXT_5',  5);


drop table if exists basic2;
create table basic2 (	
	id	bigint,
	coltext 	text,
	coldatetime	Date
) distributed by (id);		
 

INSERT INTO basic2 (id, coltext, coldatetime) VALUES (1, 'San Francisco', '1994-11-29');
INSERT INTO basic2 (id, coltext, coldatetime) VALUES (2, 'San Jose', '2004-11-29');
INSERT INTO basic2 (id, coltext, coldatetime) VALUES (3, 'San Mateo', '2014-11-29');