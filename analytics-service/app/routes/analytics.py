from fastapi import APIRouter
from app.models import riding_data
from app.config import database

router = APIRouter()

@router.get("/total_distance")
async def get_total_distance(user_id: str):
    query = riding_data.select().where(riding_data.c.user_id == user_id)
    result = await database.fetch_all(query)

    total_distance = sum(r["distance"] for r in result)
    return {"total_distance": total_distance}
