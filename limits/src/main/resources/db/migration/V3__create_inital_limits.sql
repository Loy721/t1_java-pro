DO $$
DECLARE i INT;
BEGIN
FOR i IN 1..100 LOOP
        INSERT INTO limits (id, user_id, version) VALUES (nextval('limits_seq'), i, 1);
END LOOP;
END $$;