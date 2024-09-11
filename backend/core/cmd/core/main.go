package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/zunayedology/Demure/backend/core/internal/pkg/service"
	"github.com/zunayedology/Demure/backend/core/proto"
	"google.golang.org/grpc"
	"log"
	"net"
)

func main() {
	// Start the gRPC server
	go startGRPCServer()

	// Set up Gin for REST API exposure
	router := gin.Default()

	// REST endpoint that triggers the BookBicycle RPC method
	router.POST("/book-bicycle", func(c *gin.Context) {
		// Here we would call the gRPC service via client
		// For now, return a dummy success message
		c.JSON(200, gin.H{
			"message": "Booking request received!",
		})
	})

	// Start Gin server on port 8000
	fmt.Println("Gin HTTP server listening on port 8000...")
	if err := router.Run(":8000"); err != nil {
		log.Fatalf("failed to start Gin server: %v", err)
	}
}

func startGRPCServer() {
	// Listen on port 50051 for gRPC
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
