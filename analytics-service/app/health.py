from fastapi import APIRouter
from app.db import mongodb
from app.health_data import HealthData
from bson import ObjectId
from typing import List

router = APIRouter()

@router.post("/add", response_model=HealthData)
async def add_health_data(data: HealthData):
    collection = await mongodb.get_collection("health_data")
    health_record = data.model_dump()
    health_record["_id"] = str(ObjectId())
    await collection.insert_one(health_record)
    return data

@router.get("/user/{user_id}", response_model=List[HealthData])
async def get_health_data(user_id: str):
    collection = await mongodb.get_collection("health_data")
    health_records = await collection.find({"user_id": user_id}).to_list(100)
    return [HealthData(**record) for record in health_records]
