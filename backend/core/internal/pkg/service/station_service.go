package service

import (
	"context"
	"github.com/neo4j/neo4j-go-driver/v5/neo4j"
	pb "github.com/zunayedology/Demure/backend/core/proto"
	"log"
)

// StationService represents the gRPC service implementation for station-related queries
type StationService struct {
	pb.UnimplementedStationServiceServer
	neo4jDB neo4j.DriverWithContext
}

// NewStationService creates a new instance of StationService
func NewStationService(neo4jDB neo4j.DriverWithContext) *StationService {
	return &StationService{
		neo4jDB: neo4jDB, // Connect to Neo4j
	}
}

// CheckStationAvailability checks if a station has available bicycles
func (s *StationService) CheckStationAvailability(ctx context.Context, req *pb.StationRequest) (*pb.StationResponse, error) {

	session := s.neo4jDB.NewSession(ctx, neo4j.SessionConfig{})
	defer func(session neo4j.SessionWithContext, ctx context.Context) {
		err := session.Close(ctx)
		if err != nil {
			log.Printf("failed to close session: %v", err)
		}
	}(session, ctx)

	// Neo4j query to check availability at the station
	query := `
		MATCH (s:Station {id: $stationId})
		RETURN s.availableBicycles > 0 AS available
	`

	result, err := session.Run(ctx, query, map[string]interface{}{
		"stationId": req.StationId,
	})

	if err != nil || !result.Next(ctx) {
		log.Printf("failed to run query or station not found: %v", err)
		return &pb.StationResponse{
			Available: false,
			Message:   "Station not found or unavailable",
		}, err
	}

	available, _ := result.Record().Get("available")

	// Return the response based on availability
	if available.(bool) {
		return &pb.StationResponse{
			Available: true,
			Message:   "Station has available bicycles",
		}, nil
	} else {
		return &pb.StationResponse{
			Available: false,
			Message:   "No available bicycles at this station",
		}, nil
	}
}
