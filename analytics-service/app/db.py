from motor.motor_asyncio import AsyncIOMotorClient
import os

class MongoDB:
    def __init__(self):
        self.client = AsyncIOMotorClient(os.environ.get("MONGODB"))
        self.db = self.client.demure_has

mongodb = MongoDB()
