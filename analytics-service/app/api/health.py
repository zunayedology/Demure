from fastapi import APIRouter, WebSocket
from app.services.tracking import track_ride

router = APIRouter()

@router.websocket("/ws/track")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    await track_ride(websocket)
