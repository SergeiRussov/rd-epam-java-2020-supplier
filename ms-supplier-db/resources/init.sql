SET client_encoding = 'UTF-8';
SET check_function_bodies = false;
SET client_min_messages = warning;

drop schema if exists supplier cascade;
CREATE SCHEMA supplier

  CREATE TABLE items ( 
  id uuid not null,
  name varchar not null,
  description varchar not null,
  price int8 not null,
  creation_date timestamptz not null,
  update_date timestamptz,
  PRIMARY KEY (id)
) 

   CREATE TABLE orders ( 
  id uuid not null,
  customer varchar not null,
  status varchar not null,
  cost int not null,
  creation_date timestamptz not null,
  update_date timestamptz,
  PRIMARY KEY (id)
 
) 

  CREATE TABLE production ( 
  id uuid not null,
  item_id uuid not null,
  order_id uuid not null,
  production_date timestamptz not null,
  creation_date timestamptz not null,
  update_date timestamptz,
  PRIMARY KEY (id),
  foreign key (item_id) references items (id),
  foreign key (order_id) references orders (id)
)

 CREATE TABLE payment ( 
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
  PRIMARY KEY (id),
  foreign key (order_id) references orders (id)
);
drop user if exists supplier;
create user supplier with password 'supplier';
grant SELECT, INSERT, UPDATE, DELETE on ALL tables in schema supplier TO supplier;
grant SELECT, USAGE on ALL sequences in schema supplier TO supplier;
grant SELECT, USAGE on ALL FUNCTIONS in schema supplier TO supplier;
