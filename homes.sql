drop database if exists homelocations;
create database homelocations;
use homelocations;
create table if not exists homes (
   id int(11) not null auto_increment,
   address1 varchar(255) not null,
   address2 varchar(255),
   city varchar(255) not null,
   state varchar(255) not null,
   zipcode varchar(255) not null,
   country varchar(255) not null,
   primary key (id)
)
engine=innodb
default charset=utf8
auto_increment=1;

insert into homes (address1, address2, city, state, zipcode, country) values
("Av. Mariano Otero 1105 Piso 4", "Colonia Rinconada del Bosque", "Guadalajara", "", "44530", "Mexico"),
("Luxoft Professional, LLC", "1-y Volokolamskiy pr-d", "Moscow", "", "10 123060", "Russia"),
("King Street West, 100", "Suite 5600", "Toronto", "", "M5X 1C9", "Canada"),
("Radishcheva Street 10/14", "Business Center IRVA", "Kiev", "", "03680", "Ukraine"),
("Excelian Limited", "44 Featherstone Street", "London", "", "EC1Y 8RN", "United Kingdom");