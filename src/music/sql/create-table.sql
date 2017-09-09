create table music (
  id serial primary key,
	composer varchar(100),
	title varchar(100),
	original_url varchar(256),
	url varchar(256),
	genre varchar(32),
	year integer
);
