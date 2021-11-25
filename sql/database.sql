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
    primary key ("id"),
    foreign key ("boss_id") references Programmer("id")
);

drop table if exists "Project";
create table if not exists "Project" (
    "id" character(36) not null,
    "department_id" character(36) not null,
    "projectManager_id" character(36) not null,
    "name" varchar(255) not null,
    "budget" double not null,
    "startDate" date not null,
    "endDate" date,
    "technologies" text,
    "repository_id" character(36) not null,
    "isFinished" integer not null,
    "programmers_ids" text,
    primary key ("id")
    foreign key ("department_id") references Department("id"),
    foreign key ("project_manager_id") references Programmer("id"),
    foreign key ("repository_id") references Repository("id")
);

drop table if exists "Programmer";
create table if not exists "Programmer" (
    "id" character(36) not null,
    "name" varchar(255) not null,
    "registerDate" date not null,
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
    foreign key ("department_id") references Department("id")
);

drop table if exists "Repository";
create table if not exists "Repository" (
    "id" character(36) not null,
    "name" varchar(255) not null,
    "creationDate" date not null,
    "project_id" character(36) not null,
    "commits_ids" text,
    "issues_ids" text,
    primary key ("id"),
    foreign key ("project_id") references Project("id")
);

drop table if exists "Issue";
create table if not exists "Issue" (
    "id" character(36) not null,
    "author_id" character(36) not null,
    "title" varchar(255) not null,
    "text" text,
    "date" date not null,
    "programmers_ids" text,
    "project_id" character(36) not null,
    "repository_id" character(36) not null,
    "isFinished" integer not null,
    primary key ("id"),
    foreign key ("author_id") references Programmer("id"),
    foreign key ("project_id") references Project("id"),
    foreign key ("repository_id") references Repository("id")
);

drop table if exists "Commit";
create table if not exists "Commit" (
    "id" character(36) not null,
    "title" varchar(255) not null,
    "text" text,
    "date" date not null,
    "repository_id" character(36) not null,
    "project_id" character(36) not null,
    "author_id" character(36) not null,
    "issue_id" character(36) not null,
    primary key ("id"),
    foreign key ("repository_id") references Repository("id"),
    foreign key ("project_id") references Project("id"),
    foreign key ("author_id") references Programmer("id"),
    foreign key ("issue_id") references Issue("id")
);

drop table if exists "Programmer_Issue";
create table if not exists "Programmer_Issue" (
    "programmer_id" character(36) not null,
    "issue_id" character(36) not null,
    foreign key ("programmer_id") references Programmer("id"),
    foreign key ("issue_id") references Issue("id")
);

drop table if exists "Project_Programmer";
create table if not exists "Project_Programmer" (
    "project_id" character(36) not null,
    "programmer_id" character(36) not null,
    foreign key ("project_id") references Project("id"),
    foreign key ("programmer_id") references Programmer("id")
);
