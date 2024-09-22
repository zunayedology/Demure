// GraphQL Type Definitions

import { gql } from "apollo-server";

export const typeDefs = gql`
  type Station {
    id: ID!
    name: String!
    latitude: Float!
    longitude: Float!
    address: String!
  }

  type Query {
    getStation(id: ID!): Station
    getAllStations: [Station]
  }

  type Mutation {
    createStation(
      name: String!
      latitude: Float!
      longitude: Float!
      address: String!
    ): Station

    updateStation(
      id: ID!
      name: String
      latitude: Float
      longitude: Float
      address: String
    ): Station

    deleteStation(id: ID!): String
  }
`;
