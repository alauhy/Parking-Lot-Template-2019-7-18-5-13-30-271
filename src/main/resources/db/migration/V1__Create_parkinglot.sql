create table parkinglot(
name varchar(255) unique ,
address varchar (1024) ,
capacity int check(capacity>0)
)
