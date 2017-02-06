-- author Robert Östlin
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
    LarareId int,
    Huvudkategori int,
    primary key (Id),
    foreign key (LarareId) references Larare(Id),
    foreign key (Huvudkategori) references FormellaInlagg_Huvudkategori (Id)
);

create table FormellaInlagg_LastAv_Larare
(
    FormellaInlaggId int not null,
    LarareId int not null,
    unique (FormellaInlaggId, LarareId),
    primary key (FormellaInlaggId, LarareId),
    foreign key (FormellaInlaggId) references FormellaInlagg (Id),
    foreign key (LarareId) references Larare (Id)
);

create table FormellaInlagg_Huvudkategori
(
    Id int not null auto_increment,
    Kategori varchar(40),
    primary key (Id)
);

create table InformellaInlagg
(
    Id int not null auto_increment,
    Rubrik varchar(40) not null,
    `Text` varchar(3000) not null,
    Publiceringsdatum datetime not null,
    isDeleted boolean,
    LarareId int,
    primary key (Id),
    foreign key (LarareId) references Larare(Id)
);

create table InformellaInlagg_Bild
(
    InformellainlaggId int not null,
    Bild longblob not null,
    Bildtext varchar(60),
    Ordning int not null,
    unique (InformellainlaggId, Ordning),
    foreign key (InformellainlaggId) references InformellaInlagg (Id)
);

create table InformellaInlagg_LastAv
(
    InformellaInlaggId int not null,
    LarareId int not null,
    unique (InformellaInlaggId, LarareId),
    primary key (InformellaInlaggId, LarareId),
    foreign key (InformellaInlaggId) references InformellaInlagg (Id),
    foreign key (LarareId) references Larare (Id)
);

create table Inbjudning
(
    Id int not null auto_increment,
    LarareId int not null,
    Rubrik varchar(40) not null,
    Beskrivning varchar(3000) not null,
    BestamtTillfalle int,
    primary key (Id),
    foreign key (LarareId) references Larare (Id),
    foreign key (BestamtTillfalle) references Inbjudning_TillfalleForslag (Id)
);

create table Inbjudning_DeltagareSomKan
(
    Tillfalle int not null,
    Deltagare int not null,
    primary key (Tillfalle, Deltagare), 
    foreign key (Tillfalle) references Inbjudning_TillfalleForslag (Id),
    foreign key (Deltagare) references Larare (Id)
);

create table Inbjudning_TillfalleForslag
(
    Id int not null auto_increment,
    InbjudningId int not null,
    Datum date not null,
    Timslag char(2) not null,
    Minutslag char(2) not null,
    DeltagareSomKan int,
    primary key (Id),
    foreign key (InbjudningId) references Inbjudning (Id),
    foreign key (DeltagareSomKan) references Inbjudning_DeltagareSomKan (Tillfalle)
);

create table Inbjudning_Deltagare
(
    InbjudningId int not null,
    Deltagare int not null,
    primary key (InbjudningId, Deltagare),
    foreign key (InbjudningId) references Inbjudning (Id),
    foreign key (Deltagare) references Larare (Id)
);

-- Test data
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

insert into FormellaInlagg_Huvudkategori (Id, Kategori) values (default, 'Ämnesgrupp');
insert into FormellaInlagg_Huvudkategori (Id, Kategori) values (default, 'Handledarkollegium');
insert into FormellaInlagg_Huvudkategori (Id, Kategori) values (default, 'Forskarkollegium');

-- Formella inlägg
insert into FormellaInlagg(Id, Rubrik, Text, Publiceringsdatum, isDeleted, LarareId, Huvudkategori)
values (default, "Möte", "Onsdag den 15 är det möte gällande vad som ska finnas för redovisnings verktyg i klassrummen. Plats: Aula Nova\n\n Mvh Anders!", "2014-03-05 15:00:00", false, 1, (select Id from FormellaInlagg_Huvudkategori where Kategori = 'Ämnesgrupp'));

insert into FormellaInlagg(Id, Rubrik, Text, Publiceringsdatum, isDeleted, LarareId, Huvudkategori)
values (default, "Löneförhöjning", "Allas löner ökar med 10%.", "2015-04-08 16:00:00", false, 4, (select Id from FormellaInlagg_Huvudkategori where Kategori = 'Handledarkollegium'));

insert into FormellaInlagg(Id, Rubrik, Text, Publiceringsdatum, isDeleted, LarareId, Huvudkategori)
values (default, "Teddy bear",
"Once upon a time, there was a little boy his name was Ben. On his first birthday, his parents got him a soft cuddly brown teddy bear. The little baby boy carried his teddy bear with him every where he went. At night the teddy bear always got to sleep next to the baby boy in his crib.
Ben grew up and got his own bedroom and a big bed to sleep in. The teddy bear got to sleep next to him in bed every night. He was his favorite toy.  Ben took his teddy bear everywhere he went. The teddy bear went with the boy to the zoo, all the parks in the area, and even to school.
Ben became a teenager and he got more and more interested in video games, and going out with his friends. He stopped taking his teddy bear with him, which made the bear a little bit sad. Still, he would put his teddy bear next to him in bed every night.",
"2015-04-08 17:00:00", false, (select Id from larare where Anvandarnamn = 'lsbg'), (select Id from FormellaInlagg_Huvudkategori where Kategori = 'Forskarkollegium'));


-- Informella inlägg
insert into InformellaInlagg (Id, Rubrik, Text, Publiceringsdatum, isDeleted, LarareId)
values (default, "Semester", "Skönt med semester!", "2017-01-02 11:15", false, (select Id from Larare where Anvandarnamn = 'asan'));

insert into InformellaInlagg (Id, Rubrik, Text, Publiceringsdatum, isDeleted, LarareId)
values (default, "The teddy bear",
"Ben graduated from high school and had to go to a university in the big city. The boy was now a young man.  He would come back home a couple of times every year for just a few days.
Mom took all of Ben's toys and put them in a big box in the attic.  But, the teddy bear was always special so Mom decided to put him on one of the empty shelves so that when Ben was
back home to visit he could see his favorite bear. Ben's visits were too short and he spent most of his time visiting with his old friends, neighbors, and family. He would go to bed late at night,
and often forgot to put the bear in bed anymore. The bear was so sad and missed the nice old days when he got to sleep every night next to Ben in his bed.",
"2017-02-02 11:30", false, (select Id from Larare where Anvandarnamn = 'lsbg'));

-- insert into formellainlagg_lastav_larare (F)

set FOREIGN_KEY_CHECKS = 1;