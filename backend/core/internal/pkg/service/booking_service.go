package service

import (
	"context"
	"github.com/neo4j/neo4j-go-driver/v5/neo4j"
	"github.com/zunayedology/Demure/backend/core/internal/pkg/models"
	pb "github.com/zunayedology/Demure/backend/core/proto"
	"gorm.io/gorm"
	"log"
	"time"
)

// BookingService represents the gRPC service implementation
type BookingService struct {
	pb.UnimplementedBookingServiceServer
	pgDB    *gorm.DB
	neo4jDB neo4j.DriverWithContext
}

// NewBookingService creates a new instance of BookingService
func NewBookingService(pgDB *gorm.DB, neo4jDB neo4j.DriverWithContext) *BookingService {
	return &BookingService{
		pgDB:    pgDB,    // Use the already connected Postgres
		neo4jDB: neo4jDB, // Use the already connected Neo4j
	}
}

// BookBicycle is the gRPC method that handles bicycle booking requests
func (s *BookingService) BookBicycle(ctx context.Context, req *pb.BookingRequest) (*pb.BookingResponse, error) {

	// Start Neo4j session
	session := s.neo4jDB.NewSession(ctx, neo4j.SessionConfig{AccessMode: neo4j.AccessModeWrite})
	defer func(session neo4j.SessionWithContext, ctx context.Context) {
		err := session.Close(ctx)
		if err != nil {
			log.Printf("failed to close session: %v", err)
		}
	}(session, ctx)

	// Check station availability in Neo4j
	stationCheckQuery := "MATCH (s:Station {id: $stationId}) RETURN s"
	result, err := session.Run(ctx, stationCheckQuery, map[string]interface{}{"stationId": req.StartStationId})
	if err != nil {
		log.Printf("failed to run query: %v", err)
		return &pb.BookingResponse{
			BookingId: req.BookingId,
			Message:   "",
			Error:     "Failed to check station availability",
		}, err
	}

	// Check if the station exists
	if !result.Next(ctx) {
		log.Printf("station not found: %v", req.StartStationId)
		return &pb.BookingResponse{
			BookingId: req.BookingId,
			Message:   "",
			Error:     "Station not found",
		}, nil
	}

	// Create booking model
	booking := models.Booking{
		UserID:         req.UserId,
		BiCycleID:      req.BicycleId,
		StartStationID: req.StartStationId,
		EndStationID:   req.EndStationId,
		StartTime:      time.Now(),
		BookingID:      req.BookingId,
	}

	// Handle transaction: Save to Postgres
	err = s.pgDB.Transaction(func(tx *gorm.DB) error {
		if err := tx.Create(&booking).Error; err != nil {
			log.Printf("failed to save booking: %v", err)
			return err
		}
		return nil
	})
	if err != nil {
		return &pb.BookingResponse{
			BookingId: req.BookingId,
			Message:   "",
			Error:     "Failed to save booking",
		}, err
	}

	// Return a success message after booking
	log.Printf("Successfully booked bicycle for user: %s", req.UserId)
	return &pb.BookingResponse{
		BookingId: req.BookingId,
		Message:   "Booking successful!",
		Error:     "",
	}, nil
}
