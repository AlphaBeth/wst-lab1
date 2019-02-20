CREATE TABLE exterminatus
(
  id        BIGINT PRIMARY KEY,
  initiator VARCHAR(512) NOT NULL,
  reason    VARCHAR(2048),
  method    VARCHAR(255) NOT NULL,
  planet    VARCHAR(255) NOT NULL,
  date      TIMESTAMP    NOT NULL
);

CREATE SEQUENCE exterminatus_sequence;
