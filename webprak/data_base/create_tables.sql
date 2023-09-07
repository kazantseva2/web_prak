CREATE TABLE company (
  id int PRIMARY KEY,
  login varchar(100) NOT NULL, 
  password varchar(100) NOT NULL,
  name varchar(200),
  info text,
  end_registration TIMESTAMP,
  count_vacancies int
);

CREATE TABLE vacancy (
  id int PRIMARY KEY,
  id_company int REFERENCES company(id),
  title varchar(200) NOT NULL,
  position text,
  salary int,
  requirements text
);

CREATE TABLE job_seeker (
  id int PRIMARY KEY,
  login varchar(100) NOT NULL, 
  password varchar(100) NOT NULL,
  full_name varchar(200),
  contact_info json,
  education_info text,
  status text,
  desired_position text,
  desired_salary int
);

CREATE TABLE prev_job (
  id int PRIMARY KEY,
  id_seeker int REFERENCES job_seeker(id),
  company text,
  position text,
  salary int
);

CREATE TABLE response (
  id serial PRIMARY KEY,
  id_vacancy int REFERENCES vacancy (id),
  id_seeker int REFERENCES job_seeker (id)
);





