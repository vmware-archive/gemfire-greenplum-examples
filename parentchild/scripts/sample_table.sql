--
-- =========================================================================
-- Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
-- This product is protected by U.S. and international copyright
-- and intellectual property laws. Pivotal products are covered by
-- one or more patents listed at http://www.pivotal.io/patents.
-- =========================================================================
--
drop table if exists parent;
create table parent (	
	id	bigint,
	name text,
	income	float4
) distributed by (id);		
 
drop table if exists child;
create table child (		
	id	 bigint,
	parent_id	 bigint,
	name text,
	age int
) distributed by (parent_id);

drop table if exists mapped_child;
create table mapped_child (		
	id	 bigint,
	parent_id	 bigint,
	name text,
	age int
) distributed by (parent_id);

drop table if exists orphan_child;
create table orphan_child (		
	id	 bigint,
	parent_id	 bigint,
	name text,
	age int
) distributed by (parent_id);

INSERT INTO parent VALUES (1, 'Alice',  123456.0);
INSERT INTO parent VALUES (2, 'Bob',  250000.0);
INSERT INTO parent VALUES (4, 'Charlie',  245457.0);
INSERT INTO parent VALUES (5, 'Dana',  345678.0);
INSERT INTO parent VALUES (6, 'Elle',  255000.0);

INSERT INTO child VALUES (1,1, 'Marsha', 16);
INSERT INTO child VALUES (2,2, 'Frank', 5);
INSERT INTO child VALUES (3,6, 'Grace', 22);
INSERT INTO child VALUES (66,66, 'Damion', 66);

