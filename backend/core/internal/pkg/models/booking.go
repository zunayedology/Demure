package models

import (
	"gorm.io/gorm"
	"time"
)

type Booking struct {
	gorm.Model
	UserID         string    `gorm:"not null"`
	BiCycleID      string    `gorm:"not null"`
	StartStationID string    `gorm:"not null"`
	EndStationID   string    `gorm:"not null"`
	StartTime      time.Time `gorm:"not null"`
	EndTime        time.Time `gorm:"not null"`
	BookingID      string    `gorm:"not null"`
}
