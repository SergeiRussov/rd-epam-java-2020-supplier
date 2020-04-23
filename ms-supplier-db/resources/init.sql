SET client_encoding = 'UTF-8';
SET check_function_bodies = false;
SET client_min_messages = warning;

CREATE SCHEMA IF NOT exists supplier;

 CREATE TABLE IF NOT EXISTS supplier.items ( 
  id uuid not null,
  name varchar not null,
  description varchar not null,
  price int8 not null,
  creation_date timestamptz not null,
  update_date timestamptz,
  PRIMARY KEY (id)
) WITHOUT OIDS;

CREATE TABLE IF NOT EXISTS supplier.production ( 
  id uuid not null,
  item_id uuid not null,
  order_id uuid not null,
  production_date timestamptz not null,
  creation_date timestamptz not null,
  update_date timestamptz,
  PRIMARY KEY (id)
) WITHOUT OIDS;

ALTER TABLE supplier.production ADD CONSTRAINT Item_id_product_foreign FOREIGN KEY (item_id) REFERENCES supplier.items (id) ;

CREATE TABLE IF NOT EXISTS supplier.orders ( 
  id uuid not null,
  customer varchar not null,
  status varchar not null,
  cost int not null,
  creation_date timestamptz not null,
  update_date timestamptz,
  PRIMARY KEY (id)
) WITHOUT OIDS;

ALTER TABLE supplier.orders ADD CONSTRAINT orders_id_foreign FOREIGN KEY (id) REFERENCES supplier.production (order_id) ;
ALTER TABLE supplier.orders ADD CONSTRAINT orders_id_foreign FOREIGN KEY (id) REFERENCES supplier.payment (order_id) ;

CREATE TABLE IF NOT EXISTS supplier.payment ( 
  id uuid not null,
  order_id uuid not null,
  ogrn varchar not null,
  kpp varchar not null,
  inn varchar not null,
  r_s varchar not null,
  cost int8 not null,
  payment_external_id uuid not null,
  payment_callback_url varchar not null,
  status varchar not null,
  output_id uuid not null,
  store_calling_url varchar not null,
  creation_date timestamptz not null,
  update_date timestamptz ,
  PRIMARY KEY (id)
) WITHOUT OIDS;

CREATE LOGIN <supplier> WITH PASSWORD = '<supplier>';
    
grant SELECT, INSERT, UPDATE, DELETE on ALL tables in schema supplier TO supplier;
grant SELECT, USAGE on ALL sequences in schema supplier TO supplier;
grant SELECT, USAGE on ALL FUNCTIONS in schema supplier TO supplier;
