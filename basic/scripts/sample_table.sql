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
