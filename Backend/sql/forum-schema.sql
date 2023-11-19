CREATE SEQUENCE IF NOT EXISTS forum_id_seq 
INCREMENT 1
START 4;

DROP TABLE IF EXISTS public."forum";
CREATE TABLE IF NOT EXISTS public."forum"
(
    id integer NOT NULL DEFAULT nextval('forum_id_seq'::regclass),
    player_id integer NOT NULL DEFAULT 0,
    parent_post_id integer NOT NULL DEFAULT 0,
    post_date date,
    update_post date,
    title VARCHAR(124) NOT null,
    text VARCHAR(500) NOT null,
    CONSTRAINT post_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."forum"
    OWNER to folf_tracker_user;