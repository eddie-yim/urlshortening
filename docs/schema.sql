CREATE TABLE IF NOT EXISTS URL (
    ID BIGINT PRIMARY KEY,
    ORIGINAL VARCHAR(1024) NOT NULL,
    COUNT BIGINT NOT NULL,
    CREATED TIMESTAMP NOT NULL,
    CONSTRAINT UNIQ_URL_SOURCE UNIQUE (ORIGINAL)
);
CREATE SEQUENCE IF NOT EXISTS URL_SEQ START WITH 1000000 INCREMENT BY 1;