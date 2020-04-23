SET client_encoding = 'UTF-8';



insert into supplier.items (id, name, description, price, creation_date, update_date) 
	values ('307e627c-8560-11ea-bc55-0242ac130003', '�����', '����� ������������', 15, timestamptz '2020-01-27 18:14:10', NULL);
insert into supplier.items (id, name, description, price, creation_date, update_date) 
	values ('307e64b6-8560-11ea-bc55-0242ac130003', '�����', '����� ������������', 200, timestamptz '2020-01-27 18:14:10', NULL);
insert into supplier.items (id, name, description, price, creation_date, update_date) 
	values ('307e65a6-8560-11ea-bc55-0242ac130003', '������', '������ ������������', 30, timestamptz '2020-01-27 18:14:10', NULL);

insert into supplier.production (id, item_id, order_id, production_date, creation_date, update_date) 
	values ('307e6b3c-8560-11ea-bc55-0242ac130003', '307e627c-8560-11ea-bc55-0242ac130003', '307e6704-8560-11ea-bc55-0242ac130003', timestamptz '2020-01-27 18:14:10', timestamptz '2020-01-27 18:14:10', NULL);
insert into supplier.production (id, item_id, order_id, production_date, creation_date, update_date) 
	values ('307e6d76-8560-11ea-bc55-0242ac130003', '307e64b6-8560-11ea-bc55-0242ac130003', '307e6998-8560-11ea-bc55-0242ac130003', timestamptz '2020-01-27 18:14:10', timestamptz '2020-01-27 18:14:10', NULL);
insert into supplier.production (id, item_id, order_id, production_date, creation_date, update_date) 
	values ('307e70a0-8560-11ea-bc55-0242ac130003', '307e65a6-8560-11ea-bc55-0242ac130003', '307e6a74-8560-11ea-bc55-0242ac130003', timestamptz '2020-01-27 18:14:10', timestamptz '2020-01-27 18:14:10', NULL);

insert into supplier.orders (id, customer, status, cost, creation_date, update_date) 
	values ('307e6704-8560-11ea-bc55-0242ac130003',  '���������', '1', 10000, timestamptz '2020-01-27 18:14:10', NULL);
insert into supplier.orders (id, customer, status, cost, creation_date, update_date) 
	values ('307e6998-8560-11ea-bc55-0242ac130003', '���������', '1', 15000, timestamptz '2020-01-27 18:14:10', NULL);
insert into supplier.orders (id, customer, status, cost, creation_date, update_date) 
	values ('307e6a74-8560-11ea-bc55-0242ac130003', '������', '1', 20000, timestamptz '2020-01-27 18:14:10', NULL);


insert into supplier.payment (id, order_id, ogrn, kpp, inn, r_s, cost, payment_external_id, payment_callback_url, status, output_id, store_calling_url, creation_date, update_date) 
	values ('307e717c-8560-11ea-bc55-0242ac130003', '307e6704-8560-11ea-bc55-0242ac130003', '1177746415857', '770401001', '7704407589', '40702810500000000001', 10000, '307e73c0-8560-11ea-bc55-0242ac130003',
	'https://payment_callback_url1', '1', '307e7726-8560-11ea-bc55-0242ac130003', 'https://store_calling_url1', timestamptz '2020-01-27 18:14:10', NULL);
insert into supplier.payment (id, order_id, ogrn, kpp, inn, r_s, cost, payment_external_id, payment_callback_url, status, output_id, store_calling_url, creation_date, update_date) 
	values ('307e723a-8560-11ea-bc55-0242ac130003', '307e6998-8560-11ea-bc55-0242ac130003', '1177746424437', '772501001', '7725370467', '40702810500000001001', 15000, '307e7488-8560-11ea-bc55-0242ac130003',
	'https://payment_callback_url2', '2', '307e77ee-8560-11ea-bc55-0242ac130003', 'https://store_calling_url2', timestamptz '2020-01-27 18:14:10', NULL);
insert into supplier.payment (id, order_id, ogrn, kpp, inn, r_s, cost, payment_external_id, payment_callback_url, status, output_id, store_calling_url, creation_date, update_date) 
	values ('307e7302-8560-11ea-bc55-0242ac130003', '307e6a74-8560-11ea-bc55-0242ac130003', '1177746422996', '772801001', '7728369269', '40702810500000002001', 20000, '307e7546-8560-11ea-bc55-0242ac130003',
	'https://payment_callback_url3', '3', '307e773e-8560-11ea-bc55-0242ac130003', 'https://store_calling_url3', timestamptz '2020-01-27 18:14:10', NULL);

