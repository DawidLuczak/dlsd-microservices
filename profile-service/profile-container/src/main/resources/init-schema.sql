DROP SCHEMA IF EXISTS profile CASCADE;

CREATE SCHEMA profile;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS profile.profiles CASCADE;

CREATE TABLE profile.profiles
(
    id uuid NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nickname VARCHAR(20) NOT NULL
);