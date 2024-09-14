from fastapi import FastAPI
import asyncio
from app.routes import websocket, analytics
from app.services.batch_processor import start_batch_processing

app = FastAPI()

# Include routes
app.include_router(websocket.router, prefix="/ws")
app.include_router(analytics.router, prefix="/analytics")

@app.get("/")
async def root():
    return {"message": "Health and Analytics Service Running"}

@app.on_event("startup")
async def startup():
    await asyncio.create_task(start_batch_processing())
