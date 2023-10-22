CREATE SEQUENCE IF NOT EXISTS field_id_seq
INCREMENT 1
START 1;

CREATE TABLE IF NOT EXISTS public."field"
(
    id INTEGER NOT NULL DEFAULT nextval('field_id_seq'::regclass),
    name CHARACTER varying(50) COLLATE pg_catalog."default" NOT NULL,
    par CHARACTER varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT field_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."field"
    OWNER to folf_tracker_user;