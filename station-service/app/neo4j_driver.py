from neo4j import GraphDatabase
from django.conf import settings

class Neo4jConnection:
    def __init__(self):
        self.driver = GraphDatabase.driver(
            settings.NEO4J_HOST,
            auth=(settings.NEO4J_USER, settings.NEO4J_PASSWORD)
        )

    def close(self):
        self.driver.close()

    def query(self, query, parameters=None):
        with self.driver.session() as session:
            result = session.run(query, parameters)
            return [record for record in result]
