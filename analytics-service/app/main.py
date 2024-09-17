from fastapi import FastAPI
from app.api import health, batch, analytics

app = FastAPI()

# Register routes
app.include_router(health.router)
app.include_router(batch.router)
app.include_router(analytics.router)

@app.get("/")
async def root():
    return {"message": "Health and Analytics Service is running"}
