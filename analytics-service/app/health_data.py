# app/models/health_data.py
from pydantic import BaseModel
from typing import Optional
from datetime import datetime

class HealthData(BaseModel):
    user_id: str
    speed: float
    time: datetime
    distance: Optional[float]
    calories_burned: Optional[float]
