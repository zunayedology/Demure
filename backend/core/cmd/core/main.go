package main

import (
	"context"
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/neo4j/neo4j-go-driver/v5/neo4j"
	"github.com/zunayedology/Demure/backend/core/internal/pkg/config"
	"github.com/zunayedology/Demure/backend/core/internal/pkg/models"
	"github.com/zunayedology/Demure/backend/core/internal/pkg/service"
	"github.com/zunayedology/Demure/backend/core/proto"
	"google.golang.org/grpc"
	"gorm.io/gorm"
	"log"
	"net"
)

func main() {
	// Connect to Postgres
	db := config.ConnectPostgres()

	// Migrate the Booking model
	err := db.AutoMigrate(&models.Booking{})
	if err != nil {
		log.Fatalf("failed to migrate database: %v", err)
	}
	fmt.Println("Database migrated successfully!")

	// Connect to Neo4j
	neo4jDB := config.ConnectNeo4j()

	// Start the gRPC server
	go startGRPCServer(db, neo4jDB)

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

	// REST endpoint to check station availability
	router.GET("/check-station/:stationId", func(c *gin.Context) {
		stationId := c.Param("stationId")

		// Create a gRPC client connection
		conn, err := grpc.Dial("localhost:50051", grpc.WithInsecure())
		if err != nil {
			c.JSON(500, gin.H{"error": "Failed to connect to gRPC server"})
			return
		}
		defer func(conn *grpc.ClientConn) {
			err := conn.Close()
			if err != nil {
				log.Printf("failed to close connection: %v", err)
			}
		}(conn)

		client := proto.NewBookingServiceClient(conn)

		// Make the gRPC call to check station availability
		req := &proto.BookingRequest{StartStationId: stationId}
		resp, err := client.BookBicycle(context.Background(), req)
		if err != nil {
			c.JSON(500, gin.H{"error": "Failed to check station availability"})
			return
		}

		if resp.Error != "" {
			c.JSON(404, gin.H{"error": resp.Error})
			return
		}

		c.JSON(200, gin.H{"message": "Station available"})
	})

	// Start Gin server on port 8000
	fmt.Println("Gin HTTP server listening on port 8000...")
	if err := router.Run(":8000"); err != nil {
		log.Fatalf("failed to start Gin server: %v", err)
	}
}

func startGRPCServer(pgDB *gorm.DB, neo4jDB neo4j.DriverWithContext) {
	// Listen on port 50051 for gRPC
	lis, err := net.Listen("tcp", ":50051")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}

	grpcServer := grpc.NewServer()

	// Initialize the services
	bookingService := service.NewBookingService(pgDB, neo4jDB) // BookingService
	stationService := service.NewStationService(neo4jDB)       // StationService

	// Register the BookingService with the gRPC server
	proto.RegisterBookingServiceServer(grpcServer, bookingService)
	proto.RegisterStationServiceServer(grpcServer, stationService)

	fmt.Println("gRPC server listening on port 50051...")
	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
