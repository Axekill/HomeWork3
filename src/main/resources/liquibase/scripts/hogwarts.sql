--liquibase formatted sql
--changeset axekill:1
create index students_name_idx on student (name);

--changeset axekill:2
create index faculty_color_idx on faculty (color);