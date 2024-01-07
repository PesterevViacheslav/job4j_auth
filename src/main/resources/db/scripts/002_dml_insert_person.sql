insert into person (login, password) values ('parsentev', '123')
                                           ,('ban', '123')
                                           ,('ivan', '123') ON CONFLICT DO NOTHING;
