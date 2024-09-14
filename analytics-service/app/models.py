from sqlalchemy import Table, Column, String, Float, MetaData, Integer

metadata = MetaData()

riding_data = Table(
    "riding_data",
    metadata,
    Column("id", Integer, primary_key=True),
    Column("user_id", String(50)),
    Column("bicycle_id", String(50)),
    Column("distance", Float),
    Column("speed", Float),
    Column("time", Float),
    Column("calories_burned", Float),
)
