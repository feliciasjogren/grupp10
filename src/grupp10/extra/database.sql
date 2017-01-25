-- author Robert Östlin (scrum master)
DROP DATABASE IF EXISTS grupp10;
CREATE DATABASE grupp10;
use grupp10;

set FOREIGN_KEY_CHECKS = 0;

create table Larare
(
    Id int not null auto_increment,
    Anvandarnamn varchar(40),
    Losenord varchar(40),
    Fornamn varchar(40),
    Efternamn varchar(40),
    Email varchar(40),
    Titel varchar(40),
    Rumsnummer varchar(10),
    Telefon int,
    isAdmin boolean,
    isDeleted boolean,
    primary key(Id)
    -- foreign key (Telefon2) references Telefon (Id)
);

insert into Larare (Id, Anvandarnamn, Losenord, Fornamn, Efternamn, Email, Titel, Rumsnummer, Telefon, isAdmin, isDeleted)
values (default, "asan", "anders123", "Anders", "Andersson", "anders_andersson@gmail.com", "Professor", "N4056", "0730133769", TRUE, FALSE);

insert into Larare (Id, Anvandarnamn, Losenord, Fornamn, Efternamn, Email, Titel, Rumsnummer, Telefon, isAdmin, isDeleted)
values (default, "bagn", "britta123", "Britta", "Gustafsson", "britta_gustafsson@gmail.com", "Doktorand", "N3067", "0704315657", FALSE, FALSE);

insert into Larare (Id, Anvandarnamn, Losenord, Fornamn, Efternamn, Email, Titel, Rumsnummer, Telefon, isAdmin, isDeleted)
values (default, "jsln", "jens123", "Jens", "Larsson", "jens_larsson@gmail.com", "Lärare", "N3069", "0706850202", FALSE, FALSE);

insert into Larare (Id, Anvandarnamn, Losenord, Fornamn, Efternamn, Email, Titel, Rumsnummer, Telefon, isAdmin, isDeleted)
values (default, "lsbg", "lars123", "Lars", "Borg", "lars_borg@gmail.com", "Chefsprofessor", "N5069", "0700948282", TRUE, FALSE);

set FOREIGN_KEY_CHECKS = 1;