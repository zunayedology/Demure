// GraphQL Resolvers

import { session } from "./neo4j";

export const resolvers = {
  Query: {
    getStation: async (_: any, { id }: { id: string }) => {
      const result = await session.run("MATCH (s:Station {id: $id}) RETURN s", {
        id,
      });
      return result.records[0].get("s").properties;
    },
    getAllStations: async () => {
      const result = await session.run("MATCH (s:Station) RETURN s");
      return result.records.map((record) => record.get("s").properties);
    },
  },
  Mutation: {
    createStation: async (
      _: any,
      {
        name,
        latitude,
        longitude,
        address,
      }: { name: string; latitude: number; longitude: number; address: string },
    ) => {
      const result = await session.run(
        "CREATE (s:Station {id: apoc.create.uuid(), name: $name, latitude: $latitude, longitude: $longitude, address: $address}) RETURN s",
        { name, latitude, longitude, address },
      );
      return result.records[0].get("s").properties;
    },
    updateStation: async (
      _: any,
      {
        id,
        name,
        latitude,
        longitude,
        address,
      }: {
        id: string;
        name?: string;
        latitude?: number;
        longitude?: number;
        address?: string;
      },
    ) => {
      const result = await session.run(
        "MATCH (s:Station {id: $id}) SET s += {name: $name, latitude: $latitude, longitude: $longitude, address: $address} RETURN s",
        { id, name, latitude, longitude, address },
      );
      return result.records[0].get("s").properties;
    },
    deleteStation: async (_: any, { id }: { id: string }) => {
      await session.run("MATCH (s:Station {id: $id}) DELETE s", { id });
      return `Station with id ${id} deleted successfully.`;
    },
  },
};
