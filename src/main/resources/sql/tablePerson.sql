CREATE TABLE test."person"
(
    id serial NOT NULL,
    name character varying,
    PRIMARY KEY (id)
);
INSERT INTO test."person" (name) VALUES ('John'), ('Griselda'), ('Bobby');