insert into supplier.items (id, name, description, price, creation_date, update_date)
	values ('a0eebc999c0b4ef8bb6d6bb9bd380a11', 'item 1', 'item 1 description', 1000, timestamp '2020-01-01 12:00:00', NULL);
insert into supplier.items (id, name, description, price, creation_date, update_date)
	values ('a0eebc999c0b4ef8bb6d6bb9bd380a22', 'item 2', 'item 2 description', 2000, timestamp '2020-01-01 12:00:00', NULL);
insert into supplier.items (id, name, description, price, creation_date, update_date)
	values ('a0eebc999c0b4ef8bb6d6bb9bd380a33', 'item 3', 'item 3 description', 3000, timestamp '2020-01-01 12:00:00', NULL);

insert into supplier.orders (id, customer, status, cost, creation_date, update_date)
	values ('a0eebc999c0b4ef8bb6d6bb9bd380a44', 'customer 1', 'order status 1', 10000, timestamp '2020-01-01 12:00:00', NULL);
insert into supplier.orders (id, payment_id, customer, status, cost, creation_date, update_date)
	values ('a0eebc999c0b4ef8bb6d6bb9bd380a66', 'customer 2', 'order status 2', 15000, timestamp '2020-01-01 12:00:00', NULL);
insert into supplier.orders (id, payment_id, customer, status, cost, creation_date, update_date)
	values ('a0eebc999c0b4ef8bb6d6bb9bd380a88', 'customer 3', 'order status 3', 20000, timestamp '2020-01-01 12:00:00', NULL);

insert into supplier.production (id, item_id, order_id, production_date, creation_date, update_date)
	values ('a1eebc999c0b4ef8bb6d6bb9bd380a44', 'a0eebc999c0b4ef8bb6d6bb9bd380a11', 'a0eebc999c0b4ef8bb6d6bb9bd380a44', timestamp '2020-01-01 12:00:00', timestamp '2020-01-01 12:00:00', NULL);
insert into supplier.production (id, item_id, order_id, production_date, creation_date, update_date)
	values ('a1eebc999c0b4ef8bb6d6bb9bd380a55', 'a0eebc999c0b4ef8bb6d6bb9bd380a22', 'a0eebc999c0b4ef8bb6d6bb9bd380a66', timestamp '2020-01-01 12:00:00', timestamp '2020-01-01 12:00:00', NULL);
insert into supplier.production (id, item_id, order_id, production_date, creation_date, update_date)
	values ('a1eebc999c0b4ef8bb6d6bb9bd380a66', 'a0eebc999c0b4ef8bb6d6bb9bd380a33', 'a0eebc999c0b4ef8bb6d6bb9bd380a88', timestamp '2020-01-01 12:00:00', timestamp '2020-01-01 12:00:00', NULL);

insert into supplier.payments (id, order_id, ogrn, kpp, inn, r_s, cost, payment_external_id, payment_callback_url, status, output_id, store_calling_url, creation_date, update_date)
	values ('a2eebc999c0b4ef8bb6d6bb9bd380a44', 'a0eebc999c0b4ef8bb6d6bb9bd380a44', 'ogrn 1', 'kpp 1', 'inn 1', 'rs 1', 10000, 'a3eebc999c0b4ef8bb6d6bb9bd380a44',
	'https://payment_callback_url1', 'payment status 1', 'a4eebc999c0b4ef8bb6d6bb9bd380a44', 'https://store_calling_url1', timestamp '2020-01-01 12:00:00', NULL);
insert into supplier.payments (id, order_id, ogrn, kpp, inn, r_s, cost, payment_external_id, payment_callback_url, status, output_id, store_calling_url, creation_date, update_date)
	values ('a2eebc999c0b4ef8bb6d6bb9bd380a55', 'a0eebc999c0b4ef8bb6d6bb9bd380a66', 'ogrn 2', 'kpp 2', 'inn 2', 'rs 2', 15000, 'a3eebc999c0b4ef8bb6d6bb9bd380a55',
	'https://payment_callback_url2', 'payment status 2', 'a4eebc999c0b4ef8bb6d6bb9bd380a55', 'https://store_calling_url2', timestamp '2020-01-01 12:00:00', NULL);
insert into supplier.payments (id, order_id, ogrn, kpp, inn, r_s, cost, payment_external_id, payment_callback_url, status, output_id, store_calling_url, creation_date, update_date)
	values ('a2eebc999c0b4ef8bb6d6bb9bd380a66', 'a0eebc999c0b4ef8bb6d6bb9bd380a88', 'ogrn 3', 'kpp 3', 'inn 3', 'rs 3', 20000, 'a3eebc999c0b4ef8bb6d6bb9bd380a66',
	'https://payment_callback_url3', 'payment status 3', 'a4eebc999c0b4ef8bb6d6bb9bd380a66', 'https://store_calling_url3', timestamp '2020-01-01 12:00:00', NULL);
