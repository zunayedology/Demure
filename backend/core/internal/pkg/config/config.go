package config

import (
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"log"
)

func ConnectPostgres() *gorm.DB {
	dsn := "host=localhost " +
		"user=postgres " +
		"password=280899 " +
		"dbname=demure_core " +
		"port=5432 " +
		"sslmode=disable " +
		"TimeZone=Asia/Dhaka"

	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	if err != nil {
		log.Fatalf("failed to connect to database: %v", err)
	}
	return db
}
