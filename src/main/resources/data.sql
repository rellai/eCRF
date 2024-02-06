merge into users as dst
    using
        (
            select 'admin' as username, '$2a$12$53QbNpfZbl.LZR0dKtP5Wun7Sn6.4szJ2i3c678eNnckt0Q3E14LW' as password, 'ROLE_ADMIN' as authorities union all
            select 'user' as username, '$2a$12$5epbQWWJrtSSyJbGfoChiO3QDqM6IbLboCijE9j1wuVxlu9t0nBr6' as password, 'ROLE_USER' as authorities
        ) src ON src.username = dst.username
when not matched then
    insert (username, password, authorities)
    values (src.username, src.password, src.authorities)
when matched then
    update
    set username = src.username,
        password = src.password,
        authorities = src.authorities;