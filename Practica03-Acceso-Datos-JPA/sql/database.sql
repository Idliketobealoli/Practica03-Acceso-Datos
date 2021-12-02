drop table if exists "Department";
create table if not exists "Department" (
    "id" character(36) not null,
    "name" varchar(255) not null,
    "boss_id" character(36) not null,
    "budget" double not null,
    "finishedProjects_ids" text,
    "developingProjects_ids" text,
    "anualBudget" double not null,
    "bossHistory_ids" text not null,
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

drop table if exists "Commite";
create table if not exists "Commite" (
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

insert into "Programmer" values(
                                             1, "prog", "26/05/2002",
                                             "depart01-0000-0000-0000-000000000000",
                                             "proj0001-0000-0000-0000-000000000000",
                                             "comm0001-0000-0000-0000-000000000000,comm0002-0000-0000-0000-000000000000,comm0003-0000-0000-0000-000000000000,comm0004-0000-0000-0000-000000000000",
                                             "issu0001-0000-0000-0000-000000000000",
                                             "JAVA,KOTLIN", 2.22, 0,0,1
                                         );
insert into "Programmer" values(
                                   2, "boss", "06/05/2002",
                                   "depart01-0000-0000-0000-000000000000",
                                   null,
                                   null,
                                   null,
                                   "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
                               );
insert into "Programmer" values(
                                   4, "boss2", "06/05/2002",
                                   "depart01-0000-0000-0000-000000000000",
                                   null,
                                   null,
                                   null,
                                   "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
                               );
insert into "Programmer" values(
                                   3, "manager", "26/08/2002",
                                   "depart01-0000-0000-0000-000000000000",
                                   "proj0001-0000-0000-0000-000000000000,proj0002-0000-0000-0000-000000000000,projDTO1-0000-0000-0000-000000000000",
                                   null,
                                   "issu0001-0000-0000-0000-000000000000",
                                   "KOTLIN", 2222.22, 0,1,0
                               );
insert into "Project" values(
                                1,
                                "depart02-0000-0000-0000-000000000000", "prog0003-0000-0000-0000-000000000000",
                                "project 1", 3333.3, "02/02/2000", null,
                                "JAVA,C", "repo0001-0000-0000-0000-000000000000",
                                0, "prog0001-0000-0000-0000-000000000000"
                            );
insert into "Project" values(
                                2,
                                "depart02-0000-0000-0000-000000000000", "prog0003-0000-0000-0000-000000000000",
                                "project 2", 3333.3, "02/02/2000", "31/12/2020",
                                "C", "repo0002-0000-0000-0000-000000000000",
                                1, null
                            );
insert into "Repository" values(
                                   1, "repo 1", "22/02/6006",
                                   "proj0001-0000-0000-0000-000000000000", "comm0001-0000-0000-0000-000000000000",
                                   "issu0001-0000-0000-0000-000000000000"
                               );
insert into "Repository" values(
                                   2, "repo 2", "22/02/6006",
                                   "proj0002-0000-0000-0000-000000000000", "comm0002-0000-0000-0000-000000000000",
                                   "issu0002-0000-0000-0000-000000000000"
                               );
insert into "Issue" values(
                              1, "prog0003-0000-0000-0000-000000000000", "issue 1",
                              "akaskjfnefbufbksfcjafiw", "22/02/2022",
                              "prog0001-0000-0000-0000-000000000000", "proj0001-0000-0000-0000-000000000000", "repo0001-0000-0000-0000-000000000000", 1
                          );
insert into "Issue" values(
                              2, "prog0003-0000-0000-0000-000000000000", "issue 2",
                              null, "22/02/2022",
                              "prog0001-0000-0000-0000-000000000000", "proj0002-0000-0000-0000-000000000000", "repo0001-0000-0000-0000-000000000000", 1
                          );
insert into "Commite" values(
                                1, "commit 1", null, "11/11/2001",
                                "repo0001-0000-0000-0000-000000000000", "proj0001-0000-0000-0000-000000000000",
                                "prog0001-0000-0000-0000-000000000000", "issu0001-0000-0000-0000-000000000000"
                            );
insert into "Commite" values(
                                2, "commit 2", "adsfasfa", "11/11/2001",
                                "repo0002-0000-0000-0000-000000000000", "proj0002-0000-0000-0000-000000000000",
                                "prog0001-0000-0000-0000-000000000000", "issu0002-0000-0000-0000-000000000000"
                            );
insert into "Department" values(
                                   2, "dep3", "prog0002-0000-0000-0000-000000000000", 11.0,
                                   null, null,0.0, ""
                               );