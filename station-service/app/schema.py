import graphene
from graphene import relay, Field
from graphene_subscriptions.events import SubscriptionEvent
from .models import Station, Dock, Bicycle


# Station Node
class StationNode(graphene.ObjectType):
    class Meta:
        model = Station
        interfaces = (relay.Node, )


# Dock Node
class DockNode(graphene.ObjectType):
    class Meta:
        model = Dock
        interfaces = (relay.Node, )


# Bicycle Node
class BicycleNode(graphene.ObjectType):
    class Meta:
        model = Bicycle
        interfaces = (relay.Node, )


# Query for retrieving all stations
class Query(graphene.ObjectType):
    station = relay.Node.Field(StationNode)
    all_stations = graphene.List(StationNode)

    # Function to resolve all stations
    def resolve_all_stations(self):
        return Station.nodes.all()


# CreateStation Mutation
class CreateStation(graphene.Mutation):
    station = graphene.Field(StationNode)

    class Arguments:
        name = graphene.String(required=True)
        latitude = graphene.Float(required=True)
        longitude = graphene.Float(required=True)
        address = graphene.String(required=True)

    def mutate(self, info, name, latitude, longitude, address):
        # Authentication check
        user = info.context.user
        if not user.is_authenticated or user.role != 'ROLE_ADMIN':
            raise Exception("Authentication credentials were not provided or insufficient permissions.")

        station = Station(
            name=name,
            latitude=latitude,
            longitude=longitude,
            address=address
        ).save()
        return CreateStation(station=station)


# UpdateStation Mutation
class UpdateStation(graphene.Mutation):
    station = graphene.Field(StationNode)

    class Arguments:
        station_id = graphene.ID(required=True)
        name = graphene.String()
        latitude = graphene.Float()
        longitude = graphene.Float()
        address = graphene.String()

    def mutate(self, info, station_id, name=None, latitude=None, longitude=None, address=None):
        user = info.context.user
        if not user.is_authenticated or user.role != 'ROLE_ADMIN':
            raise Exception("Authentication credentials were not provided or insufficient permissions.")

        station = Station.nodes.get_or_none(id=station_id)
        if not station:
            raise Exception("Station not found.")

        if name:
            station.name = name
        if latitude:
            station.latitude = latitude
        if longitude:
            station.longitude = longitude
        if address:
            station.address = address

        station.save()
        return UpdateStation(station=station)


# DeleteStation Mutation
class DeleteStation(graphene.Mutation):
    success = graphene.Boolean()

    class Arguments:
        station_id = graphene.ID(required=True)

    def mutate(self, info, station_id):
        user = info.context.user
        if not user.is_authenticated or user.role != 'ROLE_ADMIN':
            raise Exception("Authentication credentials were not provided or insufficient permissions.")

        station = Station.nodes.get_or_none(id=station_id)
        if not station:
            raise Exception("Station not found.")

        station.delete()
        return DeleteStation(success=True)


# Mutations
class Mutation(graphene.ObjectType):
    create_station = CreateStation.Field()
    update_station = UpdateStation.Field()
    delete_station = DeleteStation.Field()


class StationSubscription(SubscriptionEvent):
    class Meta:
        object_type = StationNode

    def subscribe(self):
        return ['station_updates']

    def publish(self, payload):
        return payload


class Subscription(graphene.ObjectType):
    station_updated = Field(StationSubscription)


# Schema definition
schema = graphene.Schema(
    query=Query(),
    mutation=Mutation(),
    subscription=Subscription()
)
