-- read and fill from data.csv file
INSERT INTO PRODUCT(`type`,`properties`,`price`,`store_address`) 
SELECT * from CSVREAD('classpath:/data.csv');

-- fill product_name as separate column
UPDATE  product  p1
SET p1.property_name = (SELECT substring(properties, 0, LOCATE(':', properties)-1) FROM PRODUCT p2 where p1.id = p2.id );
-- fill product_value(color/gb_limit) as separate columns
UPDATE  product  p1
SET p1.property_color = (SELECT  substring(p2.properties,LOCATE(':', p2.properties)+1,LENGTH(p2.properties)) FROM PRODUCT p2 where p1.id = p2.id AND p1.property_name='color');
UPDATE  product  p1
SET p1.property_limit = (SELECT  substring(p2.properties,LOCATE(':', p2.properties)+1,LENGTH(p2.properties)) FROM PRODUCT p2 where p1.id = p2.id AND p1.property_name='gb_limit');
-- not needed column
ALTER TABLE product DROP COLUMN `properties`;

-- fill city as separate column
UPDATE  product  p1
SET p1.city = (SELECT  TRIM(substring(p2.store_address,LOCATE(',', p2.store_address)+1,LENGTH(p2.store_address))) FROM PRODUCT p2 where p1.id = p2.id );