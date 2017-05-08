#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER gateway WITH PASSWORD '123456';
    CREATE DATABASE gatewaydb;
    GRANT ALL PRIVILEGES ON DATABASE gatewaydb TO gateway;
EOSQL
