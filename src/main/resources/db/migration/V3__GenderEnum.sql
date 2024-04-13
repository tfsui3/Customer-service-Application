CREATE TYPE gender AS ENUM ('MALE','FEMALE');

ALTER TABLE customer
ALTER COLUMN gender TYPE gender
USING (gender::gender);