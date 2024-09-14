from databases import Database

DATABASE_URL = "postgresql://postgres:280899@localhost:5432/demure_has"
database = Database(DATABASE_URL)

async def connect_to_db():
    await database.connect()

async def disconnect_from_db():
    await database.disconnect()
