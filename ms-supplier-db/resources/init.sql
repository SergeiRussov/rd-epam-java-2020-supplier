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

ALTER TABLE supplier.production ADD CONSTRAINT production_item_id_foreign FOREIGN KEY (item_id) REFERENCES supplier.items (id) ;

CREATE TABLE IF NOT EXISTS supplier.orders ( 
  id uuid not null,
  customer varchar not null,
  status varchar not null,
  cost int8 not null,
  creation_date timestamptz not null,
  update_date timestamptz,
  PRIMARY KEY (id)
) WITHOUT OIDS;

ALTER TABLE supplier.production ADD CONSTRAINT production_order_id_foreign FOREIGN KEY (order_id) REFERENCES supplier.orders (id) ;

CREATE TABLE IF NOT EXISTS supplier.payments ( 
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
  update_date timestamptz,
  PRIMARY KEY (id)
) WITHOUT OIDS;

ALTER TABLE supplier.payments ADD CONSTRAINT payments_order_id_foreign FOREIGN KEY (order_id) REFERENCES supplier.orders (id) ;

create ROLE supplier PASSWORD 'supplier';
ALTER ROLE supplier LOGIN;
GRANT select,  insert, update, delete, truncate
    ON ALL TABLES IN SCHEMA supplier TO supplier;

GRANT USAGE ON ALL SEQUENCES IN SCHEMA supplier TO supplier;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA supplier TO supplier;
