CREATE TABLE ARTICLE (
	ar_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	ar_code VARCHAR(2) NOT NULL,
	ar_name VARCHAR(255) NOT NULL,
	ar_price DOUBLE NOT NULL,
	ar_stock INT NOT NULL,
	CONSTRAINT article_pk PRIMARY KEY (ar_id)
	);
	
CREATE TABLE COUNTRY (
	co_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	co_name VARCHAR(255) NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY (co_id)
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
	us_co_country INT NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (us_id),
	CONSTRAINT country_fc FOREIGN KEY (us_co_country) REFERENCES country (co_id)
	);
	
	INSERT INTO ARTICLE (ar_code,ar_name,ar_price,ar_stock) VALUES ('T1','Tee Shirt',55.6,12);
	INSERT INTO ARTICLE (ar_code,ar_name,ar_price,ar_stock) VALUES ('C2','Cle USB',9.5,45);
	INSERT INTO ARTICLE (ar_code,ar_name,ar_price,ar_stock) VALUES ('S3','Stylo',3.7,34);
	INSERT INTO ARTICLE (ar_code,ar_name,ar_price,ar_stock) VALUES ('C4','Calculatrice',20.0,4);
	INSERT INTO COUNTRY (co_name) VALUES ('France (FR)');
	INSERT INTO COUNTRY (co_name) VALUES ('Angleterre (EN)');
	INSERT INTO COUNTRY (co_name) VALUES ('Etats Unis (US)');
	INSERT INTO USERINFO (us_pseudo, us_password, us_name, us_firstName, us_adress, us_postcode, us_town, us_co_country) VALUES ('toto', 'tata', 'doe', 'john', 'a la maison', 44300, 'rouen', 1);