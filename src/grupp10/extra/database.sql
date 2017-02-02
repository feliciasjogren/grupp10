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
    unique (Anvandarnamn),
    unique (Email)
);

create table FormellaInlagg
(
    Id int not null auto_increment,
    Rubrik varchar(40) not null,
    `Text` varchar(3000) not null,
    Publiceringsdatum datetime not null,
    isDeleted boolean,
    AntalLasningar int not null,
    LarareId int,
    Huvudkategori int,
    primary key (Id),
    foreign key (LarareId) references Larare(Id)
);

create table FormellaInlaggLastAv
(
    FormellaInlaggId int not null,
    LarareId int not null,
    unique (FormellaInlaggId, LarareId)
);

create table FormellaInlagg_Huvudkategori
(
    Id int not null auto_increment,
    Kategori varchar(40),
    primary key (Id)
);

create table FormellaInlagg_Bild
(
    FormellainlaggId int not null,
    Bild longblob not null,
    Bildtext varchar(60),
    Ordning int not null,
    unique (FormellainlaggId, Ordning),
    foreign key (FormellainlaggId) references FormellaInlagg (Id)
);

create table Inbjudning
(
    Id int not null auto_increment,
    LarareId int not null,
    Rubrik varchar(40) not null,
    Beskrivning varchar(3000) not null,
    BestamdTidpunkt int,
    primary key (Id),
    foreign key (LarareId) references Larare (Id),
    foreign key (BestamdTidpunkt) references Inbjudning_Tidpunkt (Id)
);

create table Inbjudning_Tidpunkt
(
    Id int not null auto_increment,
    Datum date not null,
    Timslag char(2) not null,
    Minutslag char(2) not null,
    primary key (Id)
);

create table Inbjudning_Deltagare
(
    InbjudningId int not null,
    Deltagare int not null,
    primary key (InbjudningId, Deltagare),
    foreign key (InbjudningId) references Inbjudning (Id),
    foreign key (Deltagare) references Larare (Id)
);


-- create table BlobTest
-- (
--    Id int not null auto_increment,
--    Image longblob,
--    primary key (Id)
-- );

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

insert into FormellaInlagg_Huvudkategori(Id, Kategori) values (default, 'Ämnesgrupp');
insert into FormellaInlagg_Huvudkategori(Id, Kategori) values (default, 'Handledarkollegium');
insert into FormellaInlagg_Huvudkategori(Id, Kategori) values (default, 'Forskarkollegium');

set FOREIGN_KEY_CHECKS = 1;