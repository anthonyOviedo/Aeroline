
conn system/root 
SET LINESIZE 32767
-- alter session set container = XEPDB1;
-- DROP user hr cascade;
-- CREATE user hr IDENTIFIED by hr123;
-- grant dba to hr;
-- conn hr/hr123 as sysdba

show user
--PROMPT DROPping resources
PROMPT=======================================
PROMPT DROPPING RESOURCES;
PROMPT=======================================
DROP TABLE lab01_Plane CASCADE CONSTRAINTS;
DROP TABLE lab01_Flight CASCADE CONSTRAINTS;
DROP TABLE lab01_User CASCADE CONSTRAINTS;
DROP TABLE lab01_Ticket CASCADE CONSTRAINTS;
DROP TABLE lab01_Purchase CASCADE CONSTRAINTS;
drop table lab01_Location CASCADE CONSTRAINTS;



--loaction
PROMPT=======================================
PROMPT CREATE TABLE location;
PROMPT=======================================
CREATE TABLE lab01_Location(
    id INT NOT NULL,
    city_name VARCHAR2(25),
    airport_name VARCHAR2(25),
    country VARCHAR2(25),
    PRIMARY KEY ( id )
);

--=======================================
--creating TABLEs.
--=======================================

--plane
PROMPT=======================================
PROMPT CREATE TABLE Plane;
PROMPT=======================================
CREATE TABLE lab01_Plane(
    plane_id INT NOT NULL,
    plane_name VARCHAR(100) NOT NULL,
    plane_sits INT NOT NULL,
    PRIMARY KEY ( plane_id )
);


--Flight
PROMPT=======================================
PROMPT CREATE TABLE Flight;
PROMPT=======================================
CREATE TABLE lab01_Flight(
    flight_id INT NOT NULL,
    flight_plane_id  INT NULL,
    flight_from  VARCHAR(10)  NOT NULL,
    flight_to  VARCHAR(10)  NOT NULL,
    flight_time  date  NOT NULL,
    flight_price INT NOT NULL,
    FOREIGN KEY (flight_plane_id) REFERENCES lab01_Plane(plane_id),
    PRIMARY KEY ( flight_id )
);


--User 
PROMPT=======================================
PROMPT CREATE TABLE User;
PROMPT=======================================
CREATE TABLE lab01_User(
    user_id INT NOT NULL,
    user_name VARCHAR(10) NOT NULL,
    user_nameuser VARCHAR(10) NOT NULL,
    user_password  VARCHAR(10) NOT NULL,
    user_type  VARCHAR(10) NOT NULL,
    user_lastnames VARCHAR(10)  NOT NULL,
    user_email  VARCHAR(20)  NOT NULL,
    user_birthday  DATE  NOT NULL,
    user_address  VARCHAR(25)  NOT NULL,
    user_workphone  INT NULL ,
    user_personalphone INT NOT NULL,
    PRIMARY KEY (user_id)
);


--ticket
PROMPT=======================================
PROMPT CREATE TABLE Ticket;
PROMPT=======================================
CREATE TABLE lab01_Ticket(
    ticket_id INT NOT NULL,
    ticket_flight_code INT NULL,
    ticket_user_id  INT  NOT NULL,
    ticket_duration_time  DATE  NOT NULL,
    ticket_price INT NOT NULL,
    ticket_seat VARCHAR(4) NULL,
    FOREIGN KEY (ticket_flight_code) REFERENCES lab01_Flight(flight_id),
    FOREIGN KEY (ticket_user_id) REFERENCES lab01_User(user_id),
    PRIMARY KEY (ticket_id )
);


--Purchase
PROMPT=======================================
PROMPT CREATE TABLE Purchase;
PROMPT=======================================
CREATE TABLE lab01_Purchase(
    purchase_id INT NOT NULL,
    purchase_user_id INT NOT NULL,
    purchase_ticket_id INT NOT NULL,
    purchase_date DATE NOT NULL,
    FOREIGN KEY (purchase_user_id) REFERENCES lab01_User(user_id),
    FOREIGN KEY (purchase_ticket_id) REFERENCES lab01_Ticket(ticket_id),
    PRIMARY KEY (purchase_id )
);

--=======================================
--CRUD for USER table and others 
--=======================================
PROMPT=======================================
PROMPT FUNCTION INSERT User;
PROMPT=======================================
CREATE OR replace PROCEDURE lab01_proc_ins_User
(
  par_user_id NUMBER,
  par_user_name VARCHAR2,
  par_user_nameus  VARCHAR2,
  par_user_pass VARCHAR2,
  par_user_type VARCHAR2, 
  par_user_ltname VARCHAR2,
  par_user_email VARCHAR2,
  par_user_birthday date,
  par_user_add VARCHAR2,
  par_user_wkphone INT,
  par_user_perphone INT 
  ) IS
BEGIN
  INSERT INTO lab01_User (user_id,
  user_name,
  user_nameuser,
  user_password,
  user_type,
  user_lastnames,
  user_email,
  user_birthday,
  user_address,
  user_workphone,
  user_personalphone
  )
  VALUES(par_user_id,
    par_user_name,
    par_user_nameus,
    par_user_pass,
    par_user_type, 
    par_user_ltname,
    par_user_email,
    par_user_birthday,
    par_user_add ,
    par_user_wkphone,
    par_user_perphone
    );
END lab01_proc_ins_User;
/
show error

PROMPT=======================================
PROMPT FUNCTION LIST User;
PROMPT=======================================
CREATE OR REPLACE FUNCTION  lab01_fun_list_user RETURN SYS_REFCURSOR
AS
   VAR_REF SYS_REFCURSOR;
BEGIN
  OPEN VAR_REF FOR
    SELECT *
    FROM lab01_User;
  RETURN VAR_REF;
END;
/
show error


PROMPT=======================================
PROMPT FUNCTION DELETE User;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_del_user(par_user_id INT) AS
BEGIN
  DELETE FROM lab01_user WHERE user_id = par_user_id ;
END;
/
show error

PROMPT=======================================
PROMPT FUNCTION UPDATE User;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_upd_user(
  par_user_id NUMBER,
  par_user_name VARCHAR2,
  par_user_nameus VARCHAR2,
  par_user_pass VARCHAR2,
  par_user_type VARCHAR2, 
  par_user_ltname VARCHAR2,
  par_user_email VARCHAR2,
  par_user_birthday date,
  par_user_add VARCHAR2,
  par_user_wkphone INT,
  par_user_perphone INT      
)IS
BEGIN
  UPDATE lab01_User 
  SET
    user_name = par_user_name,
    user_nameuser = par_user_nameus,
    user_password = par_user_pass,
    user_type = par_user_type,
    user_lastnames = par_user_ltname,
    user_email = par_user_email,
    user_birthday = to_date(par_user_birthday,  'yyyy/mm/dd'),
    user_address = par_user_add,
    user_workphone = par_user_wkphone,
    user_personalphone = par_user_perphone    
  WHERE user_id = par_user_id;
END;
/
show error



--=======================================
--CRUD for PURCHASE table and others 
--=======================================
PROMPT=======================================
PROMPT FUNCTION INSERT Purchase;
PROMPT=======================================
CREATE OR replace PROCEDURE lab01_proc_ins_purchase
(par_purchase_id NUMBER,
    par_purchase_user_id NUMBER,
    par_purchase_ticket_id NUMBER
    ) IS
BEGIN
  INSERT INTO lab01_Purchase (purchase_id,
    purchase_user_id,
    purchase_ticket_id,
    purchase_date)
  VALUES(par_purchase_id,
    par_purchase_user_id,
    par_purchase_ticket_id,
    sysdate);
END lab01_proc_ins_purchase;
/
show error

PROMPT=======================================
PROMPT FUNCTION LIST Purchase;
PROMPT=======================================
CREATE OR REPLACE FUNCTION  lab01_fun_list_purchase RETURN SYS_REFCURSOR
AS
   VAR_REF SYS_REFCURSOR;
BEGIN
    OPEN VAR_REF FOR
        SELECT *
        FROM lab01_purchase;
    RETURN VAR_REF;
END;
/
show error

PROMPT=======================================
PROMPT FUNCTION DELETE Purchase;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_del_purchase(par_purchase_id INT) AS
BEGIN
  DELETE FROM lab01_purchase WHERE purchase_id = par_purchase_id ;
END;
/
show error

PROMPT=======================================
PROMPT FUNCTION UPDATE Purchase;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_upd_purchase(
  par_purchase_id NUMBER,
  par_purchase_user_id NUMBER,
  par_purchase_ticket_id NUMBER,
  purchase_date VARCHAR2
  )IS
BEGIN
  UPDATE lab01_Purchase 
  SET
    purchase_user_id= par_purchase_user_id ,
    purchase_ticket_id=par_purchase_ticket_id,
    purchase_date = to_date(purchase_date,  'yyyy/mm/dd hh24:mi')
  WHERE purchase_id = par_purchase_id;
END;
/
show error


--=======================================
--CRUD for TICKET table and others 
--=======================================
PROMPT=======================================
PROMPT PROCEDURE INSERT ticket;
PROMPT=======================================
CREATE OR replace PROCEDURE lab01_proc_ins_ticket
(par_ticket_id NUMBER,
    par_ticket_flight_code NUMBER,
    par_ticket_user_id NUMBER,
    par_ticket_duration_time VARCHAR2,
    par_ticket_price NUMBER,
    par_ticket_seat VARCHAR2) IS
BEGIN
  INSERT INTO lab01_ticket (
    ticket_id,
    ticket_flight_code,
    ticket_user_id,
    ticket_duration_time,
    ticket_price, ticket_seat )
  VALUES(par_ticket_id,
    par_ticket_flight_code,
    par_ticket_user_id,
    to_date(par_ticket_duration_time,  'yyyy/mm/dd hh24:mi'),
    par_ticket_price,
    par_ticket_seat);
END lab01_proc_ins_ticket;
/
show error

PROMPT=======================================
PROMPT FUNCTION LIST ticket;
PROMPT=======================================
CREATE OR REPLACE FUNCTION  lab01_fun_list_ticket RETURN SYS_REFCURSOR
AS
   VAR_REF SYS_REFCURSOR;
BEGIN
    OPEN VAR_REF FOR
        SELECT *
        FROM lab01_ticket;
    RETURN VAR_REF;
END;
/
show error

PROMPT=======================================
PROMPT FUNCTION DELETE ticket;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_del_ticket(par_ticket_id INT) AS
BEGIN
        DELETE FROM lab01_ticket WHERE ticket_id = par_ticket_id ;
END;
/
show error

PROMPT=======================================
PROMPT FUNCTION UPDATE ticket;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_upd_ticket(par_ticket_id NUMBER,
    par_ticket_flight_code NUMBER,
    par_ticket_user_id NUMBER,
    par_ticket_duration_time VARCHAR2,
    par_ticket_price NUMBER,
    par_ticket_seat VARCHAR2)IS
BEGIN
  UPDATE lab01_ticket 
  SET
    ticket_flight_code= par_ticket_flight_code,
    ticket_user_id  =par_ticket_user_id, 
    ticket_duration_time= to_date(par_ticket_duration_time,  'yyyy/mm/dd hh24:mi'),
    ticket_price =par_ticket_price,
    ticket_seat = par_ticket_seat
  WHERE ticket_id = par_ticket_id;
END;
/
show error


--=======================================
--CRUD for FLIGHT table and others 
--=======================================
PROMPT=======================================
PROMPT PROCEDURE INSERT flight;
PROMPT=======================================
CREATE OR replace PROCEDURE lab01_proc_ins_flight
(par_flight_id NUMBER,
    par_flight_plane_id NUMBER,
    par_flight_from VARCHAR2,
    par_flight_to VARCHAR2,
    par_flight_time VARCHAR2,
    par_flight_price NUMBER) IS
BEGIN
  INSERT INTO lab01_Flight (flight_id,
    flight_plane_id,
    flight_from,
    flight_to,
    flight_time,
    flight_price
    )
  VALUES(par_flight_id,
    par_flight_plane_id,
    par_flight_from,
    par_flight_to,
    to_date(par_flight_time, 'yyyy/mm/dd hh24:mi'),
    par_flight_price
    );
END lab01_proc_ins_flight;
/
show error

PROMPT=======================================
PROMPT FUNCTION LIST flights;
PROMPT=======================================
CREATE OR REPLACE FUNCTION  lab01_fun_list_flights RETURN SYS_REFCURSOR
AS
   VAR_REF SYS_REFCURSOR;
BEGIN
    OPEN VAR_REF FOR
        SELECT *
        FROM lab01_Flight;
    RETURN VAR_REF;
END;
/
show error

PROMPT=======================================
PROMPT FUNCTION DELETE flight;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_del_flight(par_flight_id INT) AS
BEGIN
        DELETE FROM lab01_Flight WHERE flight_id = par_flight_id ;
END;
/
show error


PROMPT=======================================
PROMPT FUNCTION UPDATE flight;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_upd_flight(
  par_flight_id NUMBER,
  par_flight_plane_id NUMBER,
  par_flight_from VARCHAR2,
  par_flight_to VARCHAR2,
  par_flight_time VARCHAR2,
  par_flight_price NUMBER
  )IS
BEGIN
  UPDATE lab01_Flight 
  SET
    flight_plane_id = par_flight_plane_id,
    flight_from = par_flight_from,
    flight_to = par_flight_to,
    flight_time = par_flight_time,
    flight_price = par_flight_price
  WHERE flight_id = par_flight_id;
END;
/
show error


--=======================================
--CRUD for PLANE table and others 
--=======================================
PROMPT=======================================
PROMPT PROCEDURE INSERT plane;
PROMPT=======================================
CREATE OR replace PROCEDURE lab01_proc_ins_plane
(par_plane_id NUMBER,
    par_plane_name VARCHAR2,
    par_plane_sits NUMBER
    ) IS
BEGIN
  INSERT INTO lab01_Plane (plane_id,plane_name,plane_sits)
  VALUES(par_plane_id,
    par_plane_name,
    par_plane_sits
);
END lab01_proc_ins_plane;
/
show error


PROMPT=======================================
PROMPT FUNCTION LIST planes;
PROMPT=======================================
CREATE OR REPLACE FUNCTION  lab01_fun_list_planes RETURN SYS_REFCURSOR
AS
   VAR_REF SYS_REFCURSOR;
BEGIN
    OPEN VAR_REF FOR
        SELECT *
        FROM lab01_Plane;

    RETURN VAR_REF;
END;
/
show error


PROMPT=======================================
PROMPT FUNCTION DELETE plane;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_del_plane(par_plane_id INT) AS
BEGIN
        DELETE FROM lab01_Plane WHERE plane_id = par_plane_id ;
END;
/
show error


PROMPT=======================================
PROMPT FUNCTION UPDATE plane;
PROMPT=======================================
CREATE OR REPLACE PROCEDURE lab01_proc_upd_plane(
  par_plane_id NUMBER,
  par_plane_name VARCHAR2,
  par_plane_sits NUMBER
  ) IS
BEGIN
  UPDATE lab01_Plane 
  SET 
    plane_name = par_plane_name,
    plane_sits = par_plane_sits
  WHERE plane_id = par_plane_id;
END;
/
show error

PROMPT=======================================
PROMPT FUNCTION search plane;
PROMPT=======================================
CREATE OR REPLACE FUNCTION  lab01_fun_search_plane (par_plane_id NUMBER)  RETURN SYS_REFCURSOR
AS
   VAR_REF SYS_REFCURSOR;
BEGIN
    OPEN VAR_REF FOR
        SELECT *
        FROM lab01_Plane where plane_id = par_plane_id ;

    RETURN VAR_REF;
END;
/
show error

-------------------------------------------------------------------------------------------------------
--INSERTAR DATOS
-------------------------------------------------------------------------------------------------------
PROMPT=======================================
PROMPT testing crud from lab01_plane ;
PROMPT=======================================
-- insert users
--Admin / type 0
--EXEC lab01_proc_ins_User(1,'Carlos', 'admin1', '12345','0','Alvarado','cAlva@gmail.com','10/12/1994', 'Heredia',22222222,88888888)
--users / type 1

--EXEC lab01_proc_ins_User(2, 'Elmer', 'cliente1', '6789','1','Jimenez','mjimenez@gmail.comm','1/04/1992','Alajuela',22222222,88887777)
--EXEC lab01_proc_ins_User(3, 'Azu', 'cliente2', '0123','1','Lopez','AzuL@gmail.com','1/12/1992', 'Heredia',22221222,55558888)

--EXEC lab01_proc_del_user(3);

-- inserts plane
EXEC lab01_proc_ins_plane(123,'el crucero volador',80);
EXEC lab01_proc_ins_plane(122,'el crucero volador',81);
EXEC lab01_proc_upd_plane(122, 'el crucero volador 2',69);
EXEC lab01_proc_ins_plane(111,'el crucero volador',82);
EXEC lab01_proc_del_plane(111);

select * from lab01_user;
COMMIT;
select * from lab01_Plane;
COMMIT;
-- ..... ejecucion de stORe PROCEDUREs.
--show user
