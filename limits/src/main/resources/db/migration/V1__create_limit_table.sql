CREATE TABLE limits (
                      id bigserial PRIMARY KEY,
                      limit_value bigint NOT NULL DEFAULT 10000,
                      user_id bigint NOT NULL UNIQUE,
                      version bigint NOT NULL
);