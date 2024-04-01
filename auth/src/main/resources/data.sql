merge into users as dst
    using
        (
            select 'admin' as username, '{bcrypt}$2a$10$Ktl0Q3q6T3XRpBwaIWXru.8lRHzLacpeHfkQ5PZkU9JOc9XbSvTQe' as password, 'admin@ecrf.ru' as email union all
            select 'user' as username, '{bcrypt}$2a$10$4XOPE2AgCIIkCnTv9wOFiOepqlKvq5YJB0Qf0jJ9.fp74zw3TiXuS' as password, 'user@ecrf.ru' as email
        ) src ON src.username = dst.username
when not matched then
    insert (username, password, email)
    values (src.username, src.password, src.email)
when matched then
    update
    set username = src.username,
        password = src.password,
        email = src.email;

insert into menu(MENU_ID, NAME, URL, STATUS, PARENT_ID)
VALUES
    (1, 'Home', '/', 1, null),
    (1, 'Users', '/users', 1, null);


insert into roles(id, name)
VALUES
    (1, 'USER'),
    (2, 'ADMIN');


insert into users_roles(user_id, role_id)
values
    (1, 1),
    (1, 2),
    (2, 2);