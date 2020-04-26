insert into supplier.items
values ('27c7f1a1-2cb9-45ab-a006-5978feb97177', 'screw M1', 'Old plain M1 screw', 152, now()::timestamptz, null);
insert into supplier.items
values ('55a0dde8-6704-4971-be78-e7120452429b', 'screw M2', 'Old plain M2 screw', 164, now()::timestamptz, null);
insert into supplier.items
values ('235c15e3-9292-49d3-976e-d87fd9196530', 'screw M3', 'Old plain M3 screw', 193, now()::timestamptz, null);

insert into supplier.orders
values ('a2048f7c-0c7d-46c7-afe8-ed9ee7670f04', 'Diamond systems', 'arriving', 304, now()::timestamptz , null);
insert into supplier.orders
values ('9fb63a5a-29a5-4df5-9e65-9a13a122e788', 'Dogs security', 'delivered', 213, now()::timestamptz, null);

insert into supplier.payments
values ('539888dc-7e81-479e-be40-248ff24d6d2e', 'a2048f7c-0c7d-46c7-afe8-ed9ee7670f04', '3692581470357', '159487326', '324865791230', '12489657301245789630', 304, '21984093-076e-41b5-938a-16167ed8305b', 'https://yandex.ru/', 'payment in process', '357e40b1-2adb-472c-b9d2-b77b555a899f', 'https://www.google.ru/', now()::timestamptz, null);
insert into supplier.payments
values ('f98a5aeb-1b1c-4cfe-bbdd-37ea78e1c94d', '9fb63a5a-29a5-4df5-9e65-9a13a122e788', '1472583690159', '951623784', '512684597321', '78954632103695874210', 213, 'd6909cae-e7e4-4bc6-93fd-a1a9029e4bff', 'https://www.uuidgenerator.net/', 'payment agreed', '60c478d2-b18c-415b-866e-be7404f2cf74', 'https://www.google.ru/', now()::timestamptz, null);

insert into supplier.production
values ('27c7f1a1-2cb9-45ab-a006-5978feb97177', '27c7f1a1-2cb9-45ab-a006-5978feb97177', 'a2048f7c-0c7d-46c7-afe8-ed9ee7670f04', now()::timestamptz, null);
insert into supplier.production
values ('55a0dde8-6704-4971-be78-e7120452429b', '55a0dde8-6704-4971-be78-e7120452429b', 'a2048f7c-0c7d-46c7-afe8-ed9ee7670f04', now()::timestamptz, null);
insert into supplier.production
values ('235c15e3-9292-49d3-976e-d87fd9196530', '235c15e3-9292-49d3-976e-d87fd9196530', '9fb63a5a-29a5-4df5-9e65-9a13a122e788', now()::timestamptz, null);
