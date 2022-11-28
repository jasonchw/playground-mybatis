create table person
(
    id            bigserial primary key,
    first_name    character varying(100)      not null,
    last_name     character varying(100)      not null,
    email         character varying(100)      not null,

    created_by    character varying(100)      not null,
    created_on    timestamp without time zone not null default now(),
    updated_by    character varying(100)      not null,
    updated_using character varying(100)      not null,
    updated_on    timestamp without time zone not null default now(),

    constraint email_uk unique (email)
);
