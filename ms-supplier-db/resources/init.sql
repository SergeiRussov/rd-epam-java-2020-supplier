drop schema if exists supplier cascade;
create schema supplier

    create table items
    (
        id            uuid        not null,
        name          varchar     not null,
        description   varchar     not null,
        price         int8        not null,
        creation_date timestamptz not null default current_timestamp,
        update_date   timestamptz,
        primary key (id)
    )

    create table orders
    (
        id            uuid                                  not null,
        customer      varchar                               not null,
        status        varchar                               not null,
        cost          int8                                  not null,
        creation_date timestamptz default current_timestamp not null,
        update_date   timestamptz,
        primary key (id)
    )

    create table payments
    (
        id                   uuid        not null,
        order_id             uuid        not null,
        orgn                 varchar(13) not null,
        kpp                  varchar(9)  not null,
        inn                  varchar(12) not null,
        r_s                  varchar(20) not null,
        cost                 int8        not null,
        payment_external_id  uuid        not null,
        payment_callback_url varchar     not null,
        status               varchar     not null,
        output_id            uuid        not null,
        store_calling_url    varchar     not null,
        creation_date        timestamptz not null default current_timestamp,
        update_date          timestamptz,
        primary key (id),
        foreign key (order_id) references orders (id)
    )

    create table production
    (
        id            uuid                                  not null,
        item_id       uuid                                  not null,
        order_id      uuid                                  not null,
        creation_date timestamptz default current_timestamp not null,
        update_date   timestamptz,
        primary key (id),
        foreign key (item_id) references items (id),
        foreign key (order_id) references orders (id)
    );

drop user if exists supplier;
create user supplier with password 'supplier';

grant select, insert, update, delete, truncate
    on all tables in schema supplier
    to supplier;

grant usage
    on all sequences in schema supplier
    to supplier;

grant execute
    on all functions in schema supplier
    to supplier;
