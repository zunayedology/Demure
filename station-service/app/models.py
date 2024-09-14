from neomodel import (
    StructuredNode,
    StringProperty,
    IntegerProperty,
    FloatProperty,
    UniqueIdProperty,
    RelationshipTo,
    RelationshipFrom,
    DateTimeProperty,
)

class Bicycle(StructuredNode):
    uid = UniqueIdProperty()
    bicycle_id = StringProperty(unique_index=True)
    type = StringProperty(choices={'mountain', 'cruiser'}, default='mountain')
    status = StringProperty(choices={'available', 'reserved', 'in_use'}, default='available')
    dock = RelationshipFrom('Dock', 'PARKED_AT')

class Dock(StructuredNode):
    uid = UniqueIdProperty()
    dock_number = IntegerProperty()
    status = StringProperty(choices={'available', 'occupied'}, default='available')
    station = RelationshipFrom('Station', 'HAS_DOCK')
    bicycle = RelationshipTo(Bicycle, 'PARKED_AT')

class Station(StructuredNode):
    uid = UniqueIdProperty()
    name = StringProperty(unique_index=True)
    latitude = FloatProperty()
    longitude = FloatProperty()
    address = StringProperty()
    created_at = DateTimeProperty(default_now=True)
    docks = RelationshipTo(Dock, 'HAS_DOCK')
