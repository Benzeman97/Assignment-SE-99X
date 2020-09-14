create table Product(product_id int,product_name varchar(120),number_of_unit int,price_of_cartoon number(9,2),
  url_of_image varchar(2000),
  constraint pk_product_id primary key(product_id),constraint uq_product_name unique (product_name));