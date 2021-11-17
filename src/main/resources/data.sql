insert into category(id, name, active, parent_category_id)
values (1, 'Elektronika', true, null),
       (2, 'Telefon', true, 1);
insert into measurement(id, name, active)
values (1, 'KG', true),
       (2, 'DONA', true);

insert into currency(id, name, active)
values (1, 'UZS', true),
       (2, 'DOLLAR', true);

insert into warehouse(id, name, active)
values (1, 'Sklad1', true),
       (2, 'Sklad2', true);
insert into client(id, name, active, phone_number)
values (1, 'TJU', true, '123');
insert into supplier(id, name, active, phone_number)
values (1, 'AAA', true, '12345');