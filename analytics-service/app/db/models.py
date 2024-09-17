from psycopg2 import connect
import os

conn = connect(
    dbname=os.getenv('DB_NAME'),
    user=os.getenv('DB_USER'),
    password=os.getenv('DB_PASS'),
    host=os.getenv('DB_HOST'),
    port=os.getenv('DB_PORT')
)

def create_rides_table():
    cur = conn.cursor()
    cur.execute("""
        CREATE TABLE IF NOT EXISTS rides (
            id SERIAL PRIMARY KEY,
            user_id INT,
            start_time TIMESTAMPTZ,
            end_time TIMESTAMPTZ,
            distance FLOAT,
            speed FLOAT,
            created_at TIMESTAMPTZ DEFAULT NOW()
        );
    """)
    conn.commit()
    cur.close()
