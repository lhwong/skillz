CREATE TABLE learning_path
(
    
    id uuid NOT NULL,
    name character varying(255) NOT NULL,
    path integer,
    sequence integer,
    CONSTRAINT learning_path_pkey PRIMARY KEY (id)
);