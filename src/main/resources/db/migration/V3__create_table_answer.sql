CREATE TABLE answer
(
    id            SERIAL PRIMARY KEY,
    message       TEXT      NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    topic_id      INTEGER   NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topics (id)
);