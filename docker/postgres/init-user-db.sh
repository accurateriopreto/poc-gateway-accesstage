#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER gateway;
    CREATE DATABASE gatewaydb;
    GRANT ALL PRIVILEGES ON DATABASE gatewaydb TO gateway;
EOSQL
