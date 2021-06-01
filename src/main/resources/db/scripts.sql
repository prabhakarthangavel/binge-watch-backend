CREATE TABLE POSTS( id DOUBLE NOT NULL AUTO_INCREMENT,  
					PRIMARY KEY (id),  
                    USER_ID DOUBLE, 
                    MOVIE_ID VARCHAR(64), 
                    POST_DATE DATE, 
                    STARS NUMERIC,
                    MOVIE_LIKE CHAR,
                    REVIEW VARCHAR(500),
                    TAGS VARCHAR(200),
                    CREATED_DATE DATE);

CREATE TABLE MOVIES(ID DOUBLE NOT NULL AUTO_INCREMENT,  
					PRIMARY KEY (id),
                    MOVIE_ID VARCHAR(64),
                    MOVIE_NAME VARCHAR(100),
                    MOVIE_IMG VARCHAR(255),
                    YEAR YEAR,
                    CAST VARCHAR(255),
                    CREATED_DATE DATE);

CREATE TABLE USERS (ID DOUBLE NOT NULL AUTO_INCREMENT,
                    PRIMARY KEY (ID),
                    FIRSTNAME VARCHAR(100),
                    LASTNAME VARCHAR(100),
                    USERNAME VARCHAR(100),
                    PASSWORD VARCHAR(100),
                    USER_ROLE VARCHAR(15)
                    );

CREATE TABLE ROLES (ROLE_ID DOUBLE, ROLE VARCHAR(15));