-- author Robert Östlin, Lukas Lindgren
drop database if exists grupp10;
create database grupp10;
use grupp10;

set FOREIGN_KEY_CHECKS = 0;

create table Telefonnummer
(
    LarareId int,
    Nummer varchar(40),
    primary key (LarareId, Nummer),
    foreign key (LarareId) references Larare (Id)
);

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
    isAdmin boolean,
    isDeleted boolean,
    primary key(Id),
    unique (Anvandarnamn, Email)
);

create table FormellaInlagg
(
    Id int not null auto_increment,
    Rubrik varchar(40) not null,
    `Text` varchar(3000) not null,
    Publiceringsdatum date not null,
    isDeleted boolean,
    AntalLasningar int not null,
    LarareId int,
    primary key (Id),
    foreign key (LarareId) references Larare(Id)
);

-- sample data
insert into Larare (Id, Anvandarnamn, Losenord, Fornamn, Efternamn, Email, Titel, Rumsnummer, isAdmin, isDeleted)
values (default, "asan", "anders123", "Anders", "Andersson", "anders_andersson@gmail.com", "Professor", "N4056", true, false);

insert into Larare (Id, Anvandarnamn, Losenord, Fornamn, Efternamn, Email, Titel, Rumsnummer, isAdmin, isDeleted)
values (default, "bagn", "britta123", "Britta", "Gustafsson", "britta_gustafsson@gmail.com", "Doktorand", "N3067", false, false);

insert into Larare (Id, Anvandarnamn, Losenord, Fornamn, Efternamn, Email, Titel, Rumsnummer, isAdmin, isDeleted)
values (default, "jsln", "jens123", "Jens", "Larsson", "jens_larsson@gmail.com", "Lärare", "N3069", false, false);

insert into Larare (Id, Anvandarnamn, Losenord, Fornamn, Efternamn, Email, Titel, Rumsnummer, isAdmin, isDeleted)
values (default, "lsbg", "lars123", "Lars", "Borg", "lars_borg@gmail.com", "Chefsprofessor", "N5069", true, false);

insert into Telefonnummer (LarareId, Nummer) values ((select Id from Larare where Anvandarnamn = 'asan'), "0730133769");
insert into Telefonnummer (LarareId, Nummer) values ((select Id from Larare where Anvandarnamn = 'asan'), "0730133770");
insert into Telefonnummer (LarareId, Nummer) values ((select Id from Larare where Anvandarnamn = 'bagn'), "0704315657");
insert into Telefonnummer (LarareId, Nummer) values ((select Id from Larare where Anvandarnamn = 'jsln'), "0706850202");
insert into Telefonnummer (LarareId, Nummer) values ((select Id from Larare where Anvandarnamn = 'lsbg'), "0700948282");

insert into FormellaInlagg(Id, Rubrik, Text, Publiceringsdatum, isDeleted, AntalLasningar, LarareId)
values (default, "Möte!", "Imorgon bitti är det möte i aulan.", "2014.03.05", false, 10, 1);

insert into FormellaInlagg(Id, Rubrik, Text, Publiceringsdatum, isDeleted, AntalLasningar, LarareId)
values (default, "Löneförhöjning", "Allas löner ökar med 10%.", "2015.04.08", false, 2, 4);

set FOREIGN_KEY_CHECKS = 1;