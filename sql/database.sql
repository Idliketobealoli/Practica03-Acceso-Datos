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
            "prog0001-0000-0000-0000-000000000000", "prog", "26/05/2002",
            "depart01-0000-0000-0000-000000000000",
            "proj0001-0000-0000-0000-000000000000",
            "comm0001-0000-0000-0000-000000000000,comm0002-0000-0000-0000-000000000000",
            "issu0001-0000-0000-0000-000000000000",
            "JAVA,KOTLIN", 2.22, 0,0,1
    );
insert into "Programmer" values(
            "prog0002-0000-0000-0000-000000000000", "boss", "06/05/2002",
            "depart01-0000-0000-0000-000000000000",
            null,
            null,
            null,
            "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
    );
insert into "Programmer" values(
            "prog0004-0000-0000-0000-000000000000", "boss2", "06/05/2002",
            "depart01-0000-0000-0000-000000000000",
            null,
            null,
            null,
            "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
    );
insert into "Programmer" values(
            "prog0003-0000-0000-0000-000000000000", "manager", "26/08/2002",
            "depart01-0000-0000-0000-000000000000",
            "proj0001-0000-0000-0000-000000000000",
            null,
            "issu0001-0000-0000-0000-000000000000",
            "KOTLIN", 2222.22, 0,1,0
    );
insert into "Project" values(
            "proj0001-0000-0000-0000-000000000000",
            "depart01-0000-0000-0000-000000000000", manager.id,
            "project 1", 3333.3, "02/02/2000", null,
            "JAVA,C", "repo0001-0000-0000-0000-000000000000",
            0, prog.id
    );
insert into "Project" values(
            "proj0002-0000-0000-0000-000000000000",
            "depart01-0000-0000-0000-000000000000", manager.id,
            "project 2", 3333.3, "02/02/2000", "31/12/2020",
            "C", "repo0002-0000-0000-0000-000000000000",
            1, null
    );
insert into "Repository" values(
            "repo0001-0000-0000-0000-000000000000", "repo 1", "22/02/6006",
            proj1.id, "comm0001-0000-0000-0000-000000000000",
            "issu0001-0000-0000-0000-000000000000"
    );
insert into "Repository" values(
            "repo0002-0000-0000-0000-000000000000", "repo 2", "22/02/6006",
            proj2.id, "comm0002-0000-0000-0000-000000000000",
            "issu0002-0000-0000-0000-000000000000"
    );
insert into "Issue" values(
            "issu0001-0000-0000-0000-000000000000", manager.id, "issue 1",
            "akaskjfnefbufbksfcjafiw", "22/02/2022",
            prog.id, proj1.id, repo1.id, 1
    );
    insert into "Issue" values(
            "issu0002-0000-0000-0000-000000000000", manager.id, "issue 2",
            null, "22/02/2022",
            null, proj2.id, repo2.id, 1
    );
    insert into "Commite" values(
            "comm0001-0000-0000-0000-000000000000", "commit 1", null, "11/11/2001",
            repo1.id, proj1.id, prog.id, issu1.id
    );
    insert into "Commite" values(
            "comm0002-0000-0000-0000-000000000000", "commit 2", "adsfasfa", "11/11/2001",
            repo2.id, proj2.id, prog.id, issu2.id
    );