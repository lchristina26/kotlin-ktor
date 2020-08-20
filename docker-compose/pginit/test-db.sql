-- used when creating the local dev pg container
CREATE ROLE "task-test-db" WITH SUPERUSER LOGIN PASSWORD 'task-test-db';
CREATE DATABASE "task-test-db" OWNER 'task-test-db';
