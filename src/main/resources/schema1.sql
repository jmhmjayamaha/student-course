
    drop table if exists address cascade ;
 
    drop table if exists course cascade ;

    drop table if exists student cascade ;

    drop table if exists student_course cascade ;

    drop sequence if exists address_seq;
 
    drop sequence if exists course_seq;

    drop sequence if exists student_seq;
 
    create sequence address_seq start with 1 increment by 50;
 
    create sequence course_seq start with 1 increment by 50;
 
    create sequence student_seq start with 1 increment by 50;

    create table address (
        id integer not null,
        created_at timestamp(6),
        updated_at timestamp(6),
        city varchar(255),
        created_by varchar(255),
        state varchar(255),
        street varchar(255),
        updated_by varchar(255),
        primary key (id)
    );

    create table course (
        fee float(53) not null,
        id integer not null,
        created_at timestamp(6),
        updated_at timestamp(6),
        created_by varchar(255),
        name varchar(255),
        updated_by varchar(255),
        primary key (id)
    );

    create table student (
        address_id integer unique,
        age integer not null,
        dob date,
        id integer not null,
        created_at timestamp(6),
        updated_at timestamp(6),
        created_by varchar(255),
        email varchar(255) unique,
        first_name varchar(255),
        last_name varchar(255),
        middle_name varchar(255),
        nic varchar(255) unique,
        tel_no varchar(255) unique,
        updated_by varchar(255),
        primary key (id)
    );
 
    create table student_course (
        course_id integer not null,
        student_id integer not null,
        primary key (course_id, student_id)
    );

    alter table if exists student 
       add constraint FKcaf6ht0hfw93lwc13ny0sdmvo 
       foreign key (address_id) 
       references address;

    alter table if exists student_course 
       add constraint FKejrkh4gv8iqgmspsanaji90ws 
       foreign key (course_id) 
       references course;

    alter table if exists student_course 
       add constraint FKq7yw2wg9wlt2cnj480hcdn6dq 
       foreign key (student_id) 
       references student;