create database pm;

use pm;

create table if not exists project (
	id int not null auto_increment,
    name nvarchar(50) not null,
    overview tinytext,
    scope tinytext,
    technology tinytext,
    created_date date,
    primary key (id)
);

create table if not exists domain (
	id int not null auto_increment,
    name nvarchar(50) not null,
    overview tinytext,
    scope tinytext,
    technology tinytext,
    note text,
    view_setting json,
    requirement text,
    erd_diagram text not null,
    class_diagram text not null,
    created_date date,
    project_id int not null,
    primary key (id),
    foreign key (project_id) references project(id),
    constraint UC_project_domain unique (name, project_id)
);
create table if not exists plan_diagram (
	id int not null auto_increment,
    name nvarchar(50),
    diagram text not null,
    type varchar(10),
    domain_id int,
    primary key (id),
    foreign key (domain_id) references domain(id)
);
create table if not exists screen (
	id int not null auto_increment,
    name nvarchar(50) not null,
    wireframe_diagram text not null,
    usecase_diagram text not null,
    domain_id int not null,
    primary key (id),
    foreign key (domain_id) references domain(id)
);

create table if not exists usecase (
	id int not null auto_increment,
    name nvarchar(50) not null,
    activity_diagram text not null,
    sequence_diagram text not null,
    screen_id int,
    primary key (id),
    foreign key (screen_id) references screen(id)
);

create table if not exists usecase_specification (
	id int not null auto_increment,
    created_by nvarchar(50),
    created_date date,
    actors nvarchar(50) not null,
    trigger_context tinytext,
    description tinytext,
    preconditions tinytext,
    postconditions tinytext,
    normal_flow text,
    alternative_flow text,
    exceptions tinytext,
    priority nvarchar(20),
    frequency_of_use nvarchar(20),
    bussiness_rules nvarchar(50),
    other_information tinytext,
    assumptions tinytext,
    usecase_id int,
    primary key (id),
    foreign key (usecase_id) references usecase(id)
);

create table if not exists class_package(
	id int not null auto_increment,
    name nvarchar(255) not null unique,
    domain_id int,
    primary key (id),
    foreign key (domain_id) references domain(id)
);

create table if not exists class_specification (
	id int not null auto_increment,
    name nvarchar(255) not null,
    detail_content text,
    class_package_id int,
    primary key (id),
    foreign key (class_package_id) references class_package(id),
    constraint UC_class_package unique(name, class_package_id)
);

create table if not exists unit_testing (
	id int not null auto_increment,
    name nvarchar(50) not null,
    function_name nvarchar(255),
    test_requirement nvarchar(255),
    function_params nvarchar(100),
    function_return nvarchar(50),
    function_exceptions nvarchar(50),
    function_logs tinytext,
    test_plan_input text,
    test_plan_result tinytext,
    class_specification_id int,
    primary key (id),
    foreign key (class_specification_id) references class_specification(id)
);

create table if not exists sql_query (
	id int not null auto_increment,
    name nvarchar(50) not null,
    query_content text,
    domain_id int,
    primary key (id),
    foreign key (domain_id) references domain(id)
);

create table if not exists domain_image (
	id int not null auto_increment,
    name nvarchar(50) not null unique,
    url varchar(255),
    domain_id int,
    primary key (id),
    foreign key (domain_id) references domain (id)
);

insert into project (name) values ("Knowledge Management");
insert into domain (name, project_id) values ("Main", 1), ("Mindmap", 1),("Concept", 1);
insert into ui_style (name) values ("button blue");
insert into ui_package (name) values ("common");
insert into ui_group (name) values ("button");
insert into ui_group_style (ui_group_id, ui_style_id) values (1, 1);
