CREATE TABLE POSTS( id NUMERIC,
                    user_id NUMERIC, 
                    movie_id VARCHAR(64), 
                    post_date DATE, 
                    stars NUMERIC,
                    movie_like CHAR,
                    review VARCHAR(500),
                    tags VARCHAR(200),
                    created_date DATE);

CREATE TABLE MOVIES(ID NUMERIC,
                    movie_id NUMERIC,
                    movie_name VARCHAR(100),
                    movie_img VARCHAR(255),
                    year YEAR,
                    cast VARCHAR(255),
                    created_date DATE);

                    