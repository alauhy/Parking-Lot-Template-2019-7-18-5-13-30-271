create table `parkinglot`(
`id` int NOT NULL AUTO_INCREMENT primary key,
`name` varchar(255) unique ,
`address` varchar (1024) ,
`capacity` int check(capacity>0)
)
