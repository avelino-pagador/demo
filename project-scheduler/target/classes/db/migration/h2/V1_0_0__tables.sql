drop table project if exists
drop table task if exists
drop sequence if exists hibernate_sequence
create sequence hibernate_sequence start with 1 increment by 1
create table project (id bigint not null, project_name varchar(255), primary key (id))
create table task (id bigint not null, duration bigint, end_date date, start_date date, task_name varchar(255), primary key (id))
alter table project add constraint UK_pbfm0xrdqw1andx15423o4ltf unique (project_name)