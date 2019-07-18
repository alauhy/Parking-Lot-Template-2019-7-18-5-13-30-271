create table `parkinglot`(
`id` int auto_increment,
`name` varchar(255) unique ,
`address` varchar (1024) ,
`capacity` int check(capacity>0)
)
