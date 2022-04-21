CREATE DATABASE `hr-leave` /*!40100 DEFAULT CHARACTER SET utf8mb4 */

create table leave_allocations
(
    id                 int auto_increment
        primary key,
    date_created       datetime     not null,
    created_by         varchar(255) not null,
    last_modified_date datetime     not null,
    last_modified_by   varchar(255) not null,
    number_of_days     int          not null,
    leave_type_id      int          not null,
    period int not null
);

create table leave_requests
(
    id                 int auto_increment
        primary key,
    date_created       datetime     not null,
    created_by         varchar(255) not null,
    last_modified_date datetime     not null,
    last_modified_by   varchar(255) not null,
    start_date         datetime     not null,
    end_date           datetime     not null,
    leave_type_id      int          not null,
    date_requested     datetime     not null,
    request_comments   varchar(255) not null,
    date_actioned      datetime     not null,
    is_approved        tinyint(1)   not null,
    is_cancelled       tinyint(1)   not null
);

create table leave_types
(
    id                 int auto_increment
        primary key,
    date_created       datetime     not null,
    created_by         varchar(255) not null,
    last_modified_date datetime     not null,
    last_modified_by   varchar(255) not null,
    name               varchar(255) not null,
    default_days       int          not null
);

