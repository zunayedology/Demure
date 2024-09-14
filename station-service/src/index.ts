import express from 'express';
import { ApolloServer } from 'apollo-server-express';
import { typeDefs } from './typeDefs';
import { resolvers } from './resolvers';

const app = express();

const server = new ApolloServer({ typeDefs, resolvers });

(async () => {
  await server.start();
  server.applyMiddleware({ app });

  app.listen(4000, () => {
    console.log('Server running on http://localhost:4000' + server.graphqlPath);
  });
})();
