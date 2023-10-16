create table person
(
    id         serial
        primary key,
    first_name varchar(255),
    last_name  varchar(255),
    phone      varchar(20),
    address    text
);

alter table person
    owner to postgres;

create table client
(
    code      varchar(10) not null
        primary key,
    person_id integer
        references person,
    deleted   boolean default false
);

alter table client
    owner to postgres;

create table employee
(
    number           varchar(10) not null
        constraint employee_number_pk
            primary key,
    recruitment_date date,
    email            varchar(255),
    person_id        integer
        references person,
    deleted          boolean default false
);

alter table employee
    owner to postgres;

create table operation
(
    number        varchar(20) not null
        primary key,
    creation_date date,
    amount        double precision
);

alter table operation
    owner to postgres;

create table mission
(
    code        varchar(20) not null
        primary key
        unique,
    name        varchar(255),
    description text,
    deleted     boolean default false
);

alter table mission
    owner to postgres;

create table affectation
(
    id              serial
        primary key,
    start_date      date,
    end_date        date,
    employee_number varchar(20)
        references employee,
    mission_code    varchar(20)
        constraint fk_affectation_mission
            references mission
);

alter table affectation
    owner to postgres;

create table agency
(
    code    varchar(255) not null
        primary key,
    name    varchar(255),
    address varchar(255),
    phone   varchar(20),
    deleted boolean default false
);

alter table agency
    owner to postgres;

create table account
(
    number         varchar(20) not null
        primary key
        unique,
    sold           double precision,
    creation_date  date,
    state          varchar(10)
        constraint check_state
            check ((state)::text = ANY ((ARRAY ['active'::character varying, 'inactive'::character varying])::text[])),
    client_code    varchar(20)
        references client,
    employe_number varchar(20)
        constraint account_employee_number_fkey
            references employee,
    deleted        boolean default false,
    agency_code    varchar(20)
        references agency
);

alter table account
    owner to postgres;

create table currentaccount
(
    id             serial
        primary key,
    overdraft      double precision,
    account_number varchar(255)
        constraint account_code_fkey
            references account
);

alter table currentaccount
    owner to postgres;

create table savingaccount
(
    id             serial
        primary key,
    interest_rate  double precision,
    account_number varchar(255)
        constraint account_code_fkey
            references account
);

alter table savingaccount
    owner to postgres;

create table employeeagency
(
    employee_id varchar(20)
        references employee,
    agency_id   varchar(20)
        references agency,
    debut       date,
    fin         date
);

alter table employeeagency
    owner to postgres;

create table simpleoperation
(
    type         varchar(20)
        constraint simpleoperation_type_check
            check ((type)::text = ANY
                   ((ARRAY ['withdrawal'::character varying, 'payment'::character varying])::text[])),
    operation_id varchar(20)
        references operation,
    account_id   varchar(20)
        references account,
    employee_id  varchar(20)
        references employee
);

alter table simpleoperation
    owner to postgres;

create table transaction
(
    operation_id           varchar(20) not null
        primary key
        references operation,
    source_account_id      varchar(20)
        references account,
    destination_account_id varchar(20)
        references account
);

alter table transaction
    owner to postgres;

create table credit
(
    code        varchar(20) not null
        primary key,
    date        date,
    amount      double precision,
    deadline    varchar(10),
    description text,
    status      varchar(20)
        constraint credit_status_check
            check ((status)::text = ANY
                   ((ARRAY ['pending'::character varying, 'accepted'::character varying, 'refused'::character varying, 'canceled'::character varying])::text[])),
    agency_id   varchar(20)
        references agency,
    client_id   varchar(20)
        references client,
    employee_id varchar(20)
        references employee
);

alter table credit
    owner to postgres;

create function deactivate_accounts_after_client_delete() returns trigger
    language plpgsql
as
$$
BEGIN
    IF NEW.deleted = true THEN
        UPDATE account
        SET state = 'inactive'
        WHERE client_code = OLD.code;
    END IF;
    RETURN NEW;
END;
$$;

alter function deactivate_accounts_after_client_delete() owner to postgres;

create trigger client_update_trigger
    after update
    on client
    for each row
execute procedure deactivate_accounts_after_client_delete();

create function handle_simple_operation() returns trigger
    language plpgsql
as
$$
DECLARE
    operation_amount double precision;
BEGIN
    -- Fetch the amount from the associated operation
    SELECT amount INTO operation_amount FROM operation WHERE number = NEW.operation_id;

    IF NEW.type = 'payment' THEN
        UPDATE account SET sold = sold + operation_amount WHERE number = NEW.account_id;
    ELSIF NEW.type = 'withdrawal' THEN
        UPDATE account SET sold = sold - operation_amount WHERE number = NEW.account_id;
    END IF;
    RETURN NEW;
END;
$$;

alter function handle_simple_operation() owner to postgres;

create trigger simple_operation_trigger
    after insert
    on simpleoperation
    for each row
execute procedure handle_simple_operation();

create function handle_transaction() returns trigger
    language plpgsql
as
$$
BEGIN
    -- Debugging: Log a message to check if the trigger is executed
    RAISE NOTICE 'Transaction trigger executed';

    -- Your existing trigger logic here

    RETURN NEW;
END;
$$;

alter function handle_transaction() owner to postgres;

create trigger transaction_trigger
    after insert
    on transaction
    for each row
execute procedure handle_transaction();


