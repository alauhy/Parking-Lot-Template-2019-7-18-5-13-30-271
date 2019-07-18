create table `ticket` (
`id` int auto_increment not null primary key,
`car_number` varchar(255) not null,
`parkinglot_name` varchar (255) not null ,
`create_time` long not null ,
`leave_time` long ,
`status`  BOOLEAN(1),
`parkinglot_id` int ,
foreign key (parkinglot_id) references parkinglot(id)
)