
CREATE TABLE IF NOT EXISTS public.tb_users(
    user_id uuid NOT NULL,
    cpf character varying(20) COLLATE pg_catalog."default",
    creation_date timestamp without time zone NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    full_name character varying(150) COLLATE pg_catalog."default" NOT NULL,
    image_url character varying(255) COLLATE pg_catalog."default",
    last_updated_date timestamp without time zone NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying(20) COLLATE pg_catalog."default",
    user_status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tb_users_pkey PRIMARY KEY (user_id),
    CONSTRAINT uk_8n82lwp7lflhwda2v2v3wckc9 UNIQUE (username),
    CONSTRAINT uk_grd22228p1miaivbn9yg178pm UNIQUE (email)
)