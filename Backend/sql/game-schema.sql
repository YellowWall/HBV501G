CREATE SEQUENCE IF NOT EXISTS game_id_seq 
INCREMENT 1
START 4;
--Table: public.game

CREATE TABLE IF NOT EXISTS public."game"
(
    id integer NOT NULL DEFAULT nextval('game_id_seq'::regclass),
    field_id integer NOT NULL DEFAULT 0,
    player_id integer NOT NULL DEFAULT 0,
    game_date date,
    score character VARYING(120),
    CONSTRAINT game_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."game"
    OWNER to folf_tracker_user;