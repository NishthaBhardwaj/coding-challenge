insert into mobile_customer(id,first_name,last_name,company_name,email)
values(10001, 'Nishtha','Bhardwaj','JPMC','nishtha@gmail.com');
insert into mobile_customer(id,first_name,last_name,company_name,email)
values(10002,'Jone','Ream','HCL','john@gmail.com');
insert into mobile_customer(id,first_name,last_name,company_name,email)
values(10003,'Betty','sen','HITACHI','bettya@gmail.com');

insert into mobile_ad(id,make,model,description,category,price)
values(20001,'Honda','12','Super Car!','Car',1245.35);

insert into mobile_ad_customer(ad_id,customer_id)
values(20001,10001);



