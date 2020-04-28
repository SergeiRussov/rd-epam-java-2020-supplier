create schema supplier;

drop user if exists supplier;
create user supplier with password 'supplier';

grant all on schema supplier TO supplier;

grant select, insert, update, delete, truncate
    on all tables in schema supplier
    to supplier;