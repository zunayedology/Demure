from fastapi import APIRouter, WebSocket
from app.websocket.manager import WebSocketManager

router = APIRouter()
manager = WebSocketManager()

@router.websocket("/ride")
async def websocket_endpoint(websocket: WebSocket):
    await manager.connect(websocket)
    try:
        while True:
            data = await websocket.receive_text()
            # Process incoming ride data and broadcast
            await manager.broadcast(f"Received data: {data}")
    except:
        await manager.disconnect(websocket)
