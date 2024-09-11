package config

import (
	"github.com/neo4j/neo4j-go-driver/v5/neo4j"
	"log"
)

func ConnectNeo4j() neo4j.DriverWithContext {
	uri := "bolt://localhost:7687"
	username := "neo4j"
	password := "11020418"

	db, err := neo4j.NewDriverWithContext(uri, neo4j.BasicAuth(username, password, ""))

	if err != nil {
		log.Fatalf("failed to connect to database: %v", err)
	}
	return db
}
