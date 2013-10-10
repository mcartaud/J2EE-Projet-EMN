CREATE TABLE CATALOGUE (
	ca_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	ca_code VARCHAR(2) NOT NULL,
	ca_name VARCHAR(255) NOT NULL,
	ca_price DOUBLE NOT NULL,
	ca_stock INT NOT NULL,
	CONSTRAINT catalogue_pk PRIMARY KEY (ca_id)
	);
	
CREATE TABLE USERINFO (
	us_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	us_pseudo VARCHAR(255) NOT NULL,
	us_password VARCHAR(255) NOT NULL,
	us_name VARCHAR(255) NOT NULL,
	us_firstName VARCHAR(255) NOT NULL,
	us_adress VARCHAR(255) NOT NULL,
	us_postcode INT NOT NULL,
	us_town VARCHAR(255) NOT NULL,
	us_country INT NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (us_id)
	);
	
CREATE TABLE COMMAND (
	co_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	co_us_id INT NOT NULL,
	co_ar_id VARCHAR(2) NOT NULL,
	CONSTRAINT command_pk PRIMARY KEY (co_id),
	CONSTRAINT user_fc FOREIGN KEY (co_us_id) REFERENCES userinfo (us_id),
	CONSTRAINT article_fc FOREIGN KEY (co_ar_id) REFERENCES article (ar_id)
	);
	
CREATE TABLE ARTICLE (
	ar_id VARCHAR(2) NOT NULL,
	ar_quantity INT NOT NULL,
	ar_co_id INT NOT NULL,
	ar_ca_id INT NOT NULL,
	CONSTRAINT article_pk PRIMARY KEY (ar_id),
	CONSTRAINT command_fc FOREIGN KEY (ar_co_id) REFERENCES command (co_id),
	CONSTRAINT catalogue_fc FOREIGN KEY (ar_ca_id) REFERENCES catalogue (ca_id)
	);
	
INSERT INTO CATALOGUE (id,name,prix,stock) VALUES ('T1','Tee Shirt',55.6,12);
INSERT INTO CATALOGUE (id,name,prix,stock) VALUES ('C2','Cle USB',9.5,45);
INSERT INTO CATALOGUE (id,name,prix,stock) VALUES ('S3','Stylo',3.7,34);
INSERT INTO CATALOGUE (id,name,prix,stock) VALUES ('C4','Calculatrice',20.0,4);