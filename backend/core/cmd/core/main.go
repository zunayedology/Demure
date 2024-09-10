package main

import (
	"fmt"
	"github.com/zunayedology/Demure/backend/core/internal/pkg/service"
	"github.com/zunayedology/Demure/backend/core/proto"
	"google.golang.org/grpc"
	"log"
	"net"
)

func main() {
	// Listen on port 50051 for incoming gRPC requests
	lis, err := net.Listen("tcp", ":50051")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}

	grpcServer := grpc.NewServer()
	bookingService := &service.BookingService{}

	// Register the BookingService with the gRPC server
	proto.RegisterBookingServiceServer(grpcServer, bookingService)

	fmt.Println("gRPC server listening on port 50051...")
	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
