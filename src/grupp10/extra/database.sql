-- author Robert Östlin (scrum master)
DROP DATABASE IF EXISTS grupp10;
CREATE DATABASE grupp10;
use grupp10;

set FOREIGN_KEY_CHECKS = 0;

create table Teacher
(
    Id int not null auto_increment,
    Forename varchar(40),
    Surname varchar(40),
    Email varchar(40),
    primary key(Id)
);

insert into teacher (Id, Forename, Surname, Email) values (default, "Anders", "Andersson", "anders_andersson@gmail.com");
insert into teacher (Id, Forename, Surname, Email) values (default, "Malin", "Olsson", "malin_olsson@gmail.com");
insert into teacher (Id, Forename, Surname, Email) values (default, "Mia", "Svensson", "mia_svensson@gmail.com");

set FOREIGN_KEY_CHECKS = 1;