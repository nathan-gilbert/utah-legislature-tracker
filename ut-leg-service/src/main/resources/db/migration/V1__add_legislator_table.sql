create table LEGISLATOR
(
    id bigserial primary key,
    first_name text null,
    middle_name text null,
    last_name text null,
    suffix text null,
    office text null,
    created_at timestamp with time zone default TIMEZONE('UTC'::TEXT, now()),
    updated_at timestamp with time zone default TIMEZONE('UTC'::TEXT, now()),
    deleted_at timestamp null
);