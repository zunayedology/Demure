from django.db import models

class Station(models.Model):
    station_id = models.CharField(max_length=100, unique=True, primary_key=True)
    station_name = models.CharField(max_length=100)
    latitude = models.FloatField()
    longitude = models.FloatField()

    class Meta:
        abstract = True
