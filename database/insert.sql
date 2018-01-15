set foreign_key_checks = 0;

-- delete all rows
truncate table enrollment; 
truncate table student;
truncate table course;
truncate table profesor;
truncate table uplate_studenta;
truncate table polaganje_ispita;
truncate table korisnik;

set foreign_key_checks = 1;

insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra1-2014', 'Marko', 'Marković', 'Svetog Save 39', 'markom@yahoo.com', 3, 5);
insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra2-2014', 'Milan', 'Milanović', 'Bulevar Oslobodjenja 3', 'mmilanovic92@gmail.com', 1, 1);
insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra3-2014', 'Ivana', 'Novaković', 'Narodnog Fronta 54', 'ivananovakovic@gmail.com', 1, 1);
insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra4-2014', 'Dejan', 'Dejanović', 'Fruskogorska 12', 'dejandejanovic@yahoo.com', 3, 5);
insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra5-2014', 'Jelena', 'Marković', 'Zmaj Jovina 13', 'jmarkovic2@gmail.com', 3, 5);
insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra6-2014', 'Zoran', 'Zoranović', 'Kralja Petra 1', 'zokizoki@gmail.com', 2, 4);
insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra7-2014', 'Milica', 'Petrović', 'Momo Kapora 52', 'milicapetrovic@gmail.com', 2, 4);
insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra8-2014', 'Petar', 'Petrović', 'Ratarska 21', 'peracar@yahoo.com', 3, 5);
insert into student (br_indexa, ime, prezime, adresa, mail, godina, semestar) values ('ra9-2014', 'Igor', 'Jović', 'Cara Dusana 43', 'igorjovic@outlook.com', 1, 1);

insert into course (naziv) values ('Matematika 1');
insert into course (naziv) values ('Osnove programiranja');
insert into course (naziv) values ('Objektno programiranje');
insert into course (naziv) values ('Baze Podataka');
insert into course (naziv) values ('eObrazovanje');
insert into course (naziv) values ('Upravljanje projektima');
insert into course (naziv) values ('Matematika 2');
insert into course (naziv) values ('Web Dizajn');
insert into course (naziv) values ('Web Programiranje');

insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 1, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 2, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 3, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 4, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 1, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 2, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 3, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 5, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-12-28', '2015-06-01', 6, 2);

insert into profesor (ime, prezime, uloga, mail) values ('Marko', 'Novakovic', 'predavac', 'mnovakovic@gmail.com');
insert into profesor (ime, prezime, uloga, mail) values ('Petar', 'Jovicic', 'asistent', 'petarjovic@yahoo.com');
insert into profesor (ime, prezime, uloga, mail) values ('Miladin', 'Markovic', 'predavac', 'miladinm@gmail.com');
insert into profesor (ime, prezime, uloga, mail) values ('Zivko', 'Aksentijevic', 'predavac', 'zivko234@gmail.com');
insert into profesor (ime, prezime, uloga, mail) values ('Aleksa', 'Zivkovic', 'predavac', 'azivkovic@gmail.com');
insert into profesor (ime, prezime, uloga, mail) values ('Sasa', 'Stanojevic', 'predavac', 'sasastanojevic@outlook.com');
insert into profesor (ime, prezime, uloga, mail) values ('Aleksandar', 'Krstajic', 'predavac', 'aleksandarkrstajic@yahoo.com');
insert into profesor (ime, prezime, uloga, mail) values ('Mateja', 'Petrovic', 'asistent', 'petrovicm@yahoo.com');
insert into profesor (ime, prezime, uloga, mail) values ('Snezana', 'Zoric', 'predavac', 'snezanazoric@gmail.com');


insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-06-07' , 'Polaganje',500,1);
insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-06-09' , 'Skolarina',70000,1);
insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-08-12' , 'Kolokvijum',200,1);
insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-06-07' , 'Polaganje',500,2);
insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-06-09' , 'Skolarina',70000,2);
insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-08-12' , 'Kolokvijum',200,2);
insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-06-07' , 'Polaganje',500,3);
insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-06-09' , 'Skolarina',70000,3);
insert into uplate_studenta (datum_uplate, svrha_uplate, iznos_uplate, student_id) values ('2015-08-12' , 'Kolokvijum',200,3);

insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (6, true, 1, 1);
insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (8, true, 1, 2);
insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (6, true, 1, 3);
insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (5, false, 1, 4);
insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (9, true, 2, 1);
insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (5, false, 2, 2);
insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (7, true, 2, 3);
insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (10, true, 1, 5);
insert into polaganje_ispita (ocena, polozen, course_id, student_id) values (6, true, 2, 6);

insert into korisnik (username, password, uloga) values ('admin','admin123','administrator');
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Marko', 'Marković','markomar','123','student',1);
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Milan', 'Milanović','milanmil','111','student',2);
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Ivana', 'Novaković','ivananov','aaa','student',3);
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Dejan', 'Dejanović','dejande','ddd','student',4);
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Jelena', 'Marković','jelenamar','mmm','student',5);
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Zoran', 'Zoranović','zorzor','zrzr','student',6);
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Milica', 'Petrović','milpet','mpt','student',7);
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Petar', 'Petrović','petpet','ppt','student',8);
insert into korisnik (ime, prezime, username, password, uloga, student_id) values ('Igor', 'Jović','igorjov','igj','student',9);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Marko', 'Novakovic','markonov','ooo','nastavnik',1);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Petar', 'Jovicic','petarjov','zzz','nastavnik',2);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Miladin', 'Markovic','miladinmar','xyz','nastavnik',3);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Zivko', 'Aksentijevic','zivkoaks','aks','nastavnik',4);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Aleksa', 'Zivkovic','aleksziv','ksz','nastavnik',5);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Sasa', 'Stanojevic','sasastan','sst','nastavnik',6);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Aleksandar', 'Krstajic','alekrst','krk','nastavnik',7);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Mateja', 'Petrovic','matejapet','mjpt','nastavnik',8);
insert into korisnik (ime, prezime, username, password, uloga, profesor_id) values ('Snezana', 'Zoric','snezazor','snz','nastavnik',9);

