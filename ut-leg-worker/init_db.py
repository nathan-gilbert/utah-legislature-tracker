#!/usr/local/bin/python3

import sys
import psycopg2


def create_tables(pgUser):
    """ create tables in the PostgreSQL database"""
    commands = (
        """
        CREATE TABLE legislators (
            id SERIAL PRIMARY KEY,
            first_name TEXT NOT NULL,
            middle_name TEXT NOT NULL,
            last_name TEXT NOT NULL,
            suffix TEXT NOT NULL,
            house VARCHAR(6) NOT NULL,
            party TEXT NOT NULL
        )
        """,
        """
        CREATE TABLE bills (
            id SERIAL PRIMARY KEY,
            name TEXT NOT NULL
        )
        """
        )
    conn = None
    try:
        # connect to the PostgreSQL server
        # assume you have a database named 'utleg'
        conn = psycopg2.connect(host="localhost", database="utleg",
                                user=pgUser, password="")
        cur = conn.cursor()
        # create table one by one
        for command in commands:
            cur.execute(command)
        # close communication with the PostgreSQL database server
        cur.close()
        # commit the changes
        conn.commit()
    except psycopg2.DatabaseError as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()


if __name__ == '__main__':
    # grab the first argument and use it as the postgres username
    create_tables(sys.argv[1])
