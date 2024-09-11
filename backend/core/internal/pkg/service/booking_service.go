package service

import (
	"context"
	"github.com/zunayedology/Demure/backend/core/internal/pkg/config"
	"github.com/zunayedology/Demure/backend/core/internal/pkg/models"
	pb "github.com/zunayedology/Demure/backend/core/proto"
	"gorm.io/gorm"
	"log"
	"time"
)

// BookingService represents the gRPC service implementation
type BookingService struct {
	pb.UnimplementedBookingServiceServer
	db *gorm.DB
}

// NewBookingService creates a new instance of BookingService
func NewBookingService() *BookingService {
	return &BookingService{
		db: config.ConnectPostgres(), // Connect to Postgres
	}
}

// BookBicycle is the gRPC method that handles bicycle booking requests
func (s *BookingService) BookBicycle(ctx context.Context, req *pb.BookingRequest) (*pb.BookingResponse, error) {
	log.Printf("Received booking request for user: %s, bicycle: %s, from station %s to station %s",
		req.UserId, req.BicycleId, req.StartStationId, req.EndStationId)

	// Convert the BookingRequest to a Booking model
	booking := models.Booking{
		UserID:         req.UserId,
		BiCycleID:      req.BicycleId,
		StartStationID: req.StartStationId,
		EndStationID:   req.EndStationId,
		StartTime:      time.Now(),
		EndTime:        time.Now(),
		BookingID:      req.BookingId,
	}

	// Save the booking to Postgres
	if err := s.db.Create(&booking).Error; err != nil {
		log.Printf("failed to save booking: %v", err)
		return &pb.BookingResponse{
			BookingId: req.BookingId,
			Message:   "",
			Error:     "Failed to save booking",
		}, err
	}

	// Return a success message
	return &pb.BookingResponse{
		BookingId: req.BookingId,
		Message:   "Booking successful!",
		Error:     "",
	}, nil
}
