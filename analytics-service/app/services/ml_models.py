import numpy as np
from sklearn.linear_model import LinearRegression

def train_model(data):
    X = np.array([d['distance'] for d in data]).reshape(-1, 1)
    y = np.array([d['calories_burned'] for d in data])
    model = LinearRegression()
    model.fit(X, y)
    return model

def predict_goal(model, distance):
    return model.predict([[distance]])
