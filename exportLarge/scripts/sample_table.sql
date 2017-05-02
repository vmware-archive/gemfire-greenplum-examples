drop table if exists usertable;

CREATE TABLE usertable (
	id    SERIAL PRIMARY KEY,
    YCSB_KEY VARCHAR(255),
    FIELD0 TEXT,
    FIELD1 TEXT,
    FIELD2 TEXT,
    FIELD3 TEXT,
    FIELD4 TEXT,
    FIELD5 TEXT,
    FIELD6 TEXT,
    FIELD7 TEXT,
    FIELD8 TEXT,
    FIELD9 TEXT,
    FIELD10 TEXT,
    FIELD11 TEXT,
    FIELD12 TEXT,
    FIELD13 TEXT,
    FIELD14 TEXT,
    FIELD15 TEXT,
    FIELD16 TEXT,
    FIELD17 TEXT,
    FIELD18 TEXT,
    FIELD19 TEXT,
    FIELD20 TEXT,
    FIELD21 TEXT,
    FIELD22 TEXT,
    FIELD23 TEXT,
    FIELD24 TEXT,
    FIELD25 TEXT,
    FIELD26 TEXT,
    FIELD27 TEXT,
    FIELD28 TEXT,
    FIELD29 TEXT,
    FIELD30 TEXT,
    FIELD31 TEXT,
    FIELD32 TEXT,
    FIELD33 TEXT,
    FIELD34 TEXT,
    FIELD35 TEXT,
    FIELD36 TEXT,
    FIELD37 TEXT,
    FIELD38 TEXT,
    FIELD39 TEXT,
    FIELD40 TEXT,
    FIELD41 TEXT,
    FIELD42 TEXT,
    FIELD43 TEXT,
    FIELD44 TEXT,
    FIELD45 TEXT,
    FIELD46 TEXT,
    FIELD47 TEXT,
    FIELD48 TEXT,
    FIELD49 TEXT,
    FIELD50 TEXT
  
    ) DISTRIBUTED BY (id);


/*
INSERT INTO sampletext50 VALUES (1, 'PK_1',  1);
INSERT INTO sampletext50 VALUES (2, 'PK_2',  2);
INSERT INTO sampletext50 VALUES (4, 'PK_3',  3);
INSERT INTO sampletext50 VALUES (5, 'PK_4',  4);
INSERT INTO sampletext50 VALUES (6, 'PK_5',  5);


insert into sampletext50 (FIELD0) select FIELD0 from sampletext50;
insert into sampletext50 (FIELD1) select FIELD1 from sampletext50;
insert into sampletext50 (FIELD2) select FIELD2 from sampletext50;
insert into sampletext50 (FIELD3) select FIELD3 from sampletext50;
insert into sampletext50 (FIELD4) select FIELD4 from sampletext50;
*/