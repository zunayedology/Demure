from fastapi import FastAPI
from app.health import router as health_router

app = FastAPI()

app.include_router(health_router, prefix="/api/health")
@app.get("/")
async def index():
    return {"message": "Health and Analytics Service with MongoDB"}
