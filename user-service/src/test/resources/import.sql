INSERT INTO USER_PROFILE (ID, BIRTHDAY, EMAIL, GENDER, KEYCLOAK_ID, LAST_MODIFIED_TS, REGISTERED_TS, USERNAME, STATUS)
VALUES (nextval('USER_PROFILE_SEQ'), DATE '2000-02-02', 'exists@test.com', 'UNKNOWN', '8c7af4c3-5295-4766-a527-453d6fbe33ec', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP - INTERVAL '1 day', 'test-user', 'REGISTERED');