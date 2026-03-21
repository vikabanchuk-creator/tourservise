package model;

public enum BookingStatus {
    PENDING,
    CONFIRMED,
    COMPLETED,
    CANCELLED;

    @Override
    public String toString() {
        return this.name();
    }
}