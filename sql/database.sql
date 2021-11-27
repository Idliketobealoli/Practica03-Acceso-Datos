set names utf8;
set time_zone = '+00:00';

create database if not exists `database`;

drop table if exists "Department";
create table if not exists "Department" (
    "id" character(36) not null,
    "name" varchar(255) not null,
    "boss_id" character(36) not null,
    "budget" double not null,
    "finishedProjects_ids" text,
    "developingProjects_ids" text,
    "anualBudget" double not null,
    "bossHistory_ids" text,
    primary key ("id")
);

drop table if exists "Project";
create table if not exists "Project" (
    "id" character(36) not null,
    "department_id" character(36) not null,
    "projectManager_id" character(36) not null,
    "name" varchar(255) not null,
    "budget" double not null,
    "startDate" character(10) not null,
    "endDate" character(10),
    "technologies" text,
    "repository_id" character(36) not null,
    "isFinished" integer not null,
    "programmers_ids" text,
    primary key ("id")
);

drop table if exists "Programmer";
create table if not exists "Programmer" (
    "id" character(36) not null,
    "name" varchar(255) not null,
    "registerDate" character(10) not null,
    "department_id" character(36) not null,
    "activeProjects_ids" text,
    "commits_ids" text,
    "issues_ids" text,
    "technologies" text,
    "salary" double not null,
    "isDepBoss" integer not null,
    "isProjectManager" integer not null,
    "isActive" integer not null,
    primary key ("id")
);

drop table if exists "Repository";
create table if not exists "Repository" (
    "id" character(36) not null,
    "name" varchar(255) not null,
    "creationDate" character(10) not null,
    "project_id" character(36) not null,
    "commits_ids" text,
    "issues_ids" text,
    primary key ("id")
);

drop table if exists "Issue";
create table if not exists "Issue" (
    "id" character(36) not null,
    "author_id" character(36) not null,
    "title" varchar(255) not null,
    "text" text,
    "date" character(10) not null,
    "programmers_ids" text,
    "project_id" character(36) not null,
    "repository_id" character(36) not null,
    "isFinished" integer not null,
    primary key ("id")
);

drop table if exists "Commit";
create table if not exists "Commit" (
    "id" character(36) not null,
    "title" varchar(255) not null,
    "text" text,
    "date" character(10) not null,
    "repository_id" character(36) not null,
    "project_id" character(36) not null,
    "author_id" character(36) not null,
    "issue_id" character(36) not null,
    primary key ("id")
);

drop table if exists "Programmer_Issue";
create table if not exists "Programmer_Issue" (
    "id" character(36) not null,
    "programmer_id" character(36) not null,
    "issue_id" character(36) not null
);

drop table if exists "Project_Programmer";
create table if not exists "Project_Programmer" (
    "id" character(36) not null,
    "project_id" character(36) not null,
    "programmer_id" character(36) not null
);
