create schema supplier;
create user supplier with password 'supplier';
grant all privileges on schema supplier to supplier;

grant select, insert, update, delete, truncate
    on all tables in schema supplier
    to supplier;

grant usage
    on all sequences in schema supplier
    to supplier;

grant execute
    on all functions in schema supplier
    to supplier;
