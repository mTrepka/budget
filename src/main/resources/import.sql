insert into role(role_id,role) values (1,'user');

    insert into user(user_id,email,password,surname,u_name,username) values (1,'example@email.pl','$2a$10$ONbxjPCAAgRsqEnE.qEWK.wGlqHJnJ5TfsKDtpoOP6/SkZtxwVqda','Surname','Name','UserName');
insert into user(user_id,email,password,surname,u_name,username) values (2,'example2@email.pl','$2a$10$ONbxjPCAAgRsqEnE.qEWK.wGlqHJnJ5TfsKDtpoOP6/SkZtxwVqda','Surname2','Name2','UserName2');
insert into user(user_id,email,password,surname,u_name,username) values (3,'example3@email.pl','$2a$10$ONbxjPCAAgRsqEnE.qEWK.wGlqHJnJ5TfsKDtpoOP6/SkZtxwVqda','Surname3','Name3','UserName3');
insert into user(user_id,email,password,surname,u_name,username) values (4,'example4@email.pl','$2a$10$ONbxjPCAAgRsqEnE.qEWK.wGlqHJnJ5TfsKDtpoOP6/SkZtxwVqda','Surname4','Name4','UserName4');
insert into user(user_id,email,password,surname,u_name,username) values (5,'example5@email.pl','$2a$10$ONbxjPCAAgRsqEnE.qEWK.wGlqHJnJ5TfsKDtpoOP6/SkZtxwVqda','Surname5','Name5','UserName5');

insert into user_role(user_id,role_id) values (1,1);
insert into user_role(user_id,role_id) values (2,1);
insert into user_role(user_id,role_id) values (3,1);
insert into user_role(user_id,role_id) values (4,1);
insert into user_role(user_id,role_id) values (5,1);

insert into category(category_id,name) values (1,'first');
insert into category(category_id,name) values (2,'second');
insert into category(category_id,name) values (3,'third');
insert into category(category_id,name) values (4,'fourth');

insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (1,'2020-01-01','2020-01-20','Nazwa','inc',100,1,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (2,'2020-01-01','2020-01-19','Nazwa','rev',50,2,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (3,'2020-01-01','2020-01-18','Nazwa','rev',170,2,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (4,'2020-01-01','2020-01-17','Nazwa','rev',800,3,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (5,'2020-01-01','2020-01-15','Nazwa','inc',120,1,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (6,'2020-01-01','2020-01-02','Nazwa','inc',2000,4,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (7,'2020-01-01','2020-01-14','Nazwa','rev',10,2,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (8,'2020-01-01','2020-01-13','Nazwa','rev',15,4,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (9,'2020-01-01','2020-01-22','Nazwa','inc',20,3,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (10,'2020-01-01','2020-01-27','Nazwa','inc',50,4,1);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (11,'2020-01-01','2020-01-16','Nazwa','inc',50,4,1);

insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (12,'2020-01-01','2020-01-19','Nazwa','rev',50,2,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (13,'2020-01-01','2020-01-18','Nazwa','rev',170,2,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (14,'2020-01-01','2020-01-17','Nazwa','rev',800,3,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (15,'2020-01-01','2020-01-15','Nazwa','inc',120,1,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (16,'2020-01-01','2020-01-02','Nazwa','inc',2000,4,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (17,'2020-01-01','2020-01-14','Nazwa','rev',10,2,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (18,'2020-01-01','2020-01-13','Nazwa','rev',15,4,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (19,'2020-01-01','2020-01-22','Nazwa','inc',20,3,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (20,'2020-01-01','2020-01-27','Nazwa','inc',50,4,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (21,'2020-01-01','2020-01-16','Nazwa','inc',50,4,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (22,'2020-01-01','2020-01-20','Nazwa','inc',100,1,2);

insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (23,'2020-01-01','2020-01-20','Nazwa','inc',100,1,2);
insert into event(id,creation_date,event_date,ev_name,type,value,category_category_id,owner_user_id) values (24,'2020-01-01','2020-01-20','Nazwa','inc',100,1,2);