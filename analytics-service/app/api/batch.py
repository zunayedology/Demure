from fastapi import APIRouter
from app.services.batch_processing import process_ride_data

router = APIRouter()

@router.post("/batch")
async def trigger_batch():
    process_ride_data()
    return {"message": "Batch processing started"}
