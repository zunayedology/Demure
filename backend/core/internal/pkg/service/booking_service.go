package service

import (
	"context"
	"fmt"
	"github.com/zunayedology/Demure/backend/core/proto"
)

type BookingService struct {
	proto.UnimplementedBookingServiceServer
}

// BookBicycle handles the booking of a bicycle by a user.
func (s *BookingService) BookBicycle(ctx context.Context, req *proto.BookingRequest) (*proto.BookingResponse, error) {
	// For now, we'll just mock a booking response
	bookingID := fmt.Sprintf("BKG-%s-%s", req.UserId, req.StationId)
	return &proto.BookingResponse{
		BookingId: bookingID,
		Message:   "Bicycle booked successfully",
	}, nil
}
