async def track_ride(websocket):
    while True:
        data = await websocket.receive_text()
        # Parse data (e.g., speed, distance)
        print(f"Received: {data}")
        # Store to TimescaleDB or perform real-time analytics
        await websocket.send_text(f"Data received: {data}")
