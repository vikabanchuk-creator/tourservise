package by.psu.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Flight extends TourService {
    private String origin;
    private String destination;
    private String flightNumber;
    private boolean baggageIncluded;

    // Конструктор по умолчанию
    public Flight() {
        super();
    }

    // Конструктор со всеми параметрами
    public Flight(Integer id, String name, BigDecimal price, LocalDate from, LocalDate to,
                  String origin, String destination, String flightNumber, boolean baggageIncluded) {
        super(id, name, price, from, to);
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.baggageIncluded = baggageIncluded;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public boolean isBaggageIncluded() {
        return baggageIncluded;
    }

    public void setBaggageIncluded(boolean baggageIncluded) {
        this.baggageIncluded = baggageIncluded;
    }

    @Override
    public BigDecimal calculateTotalPrice(int participants) {
        var totalPrice = getPrice().multiply(BigDecimal.valueOf(participants));
        return baggageIncluded ? totalPrice.multiply(new BigDecimal("1.3")) : totalPrice;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#,##0.00");

        return "Flight{" +
                "id=\"" + getId() + "\"" +
                ", name=\"" + getName() + "\"" +
                ", price=\"" + (getPrice() != null ? df.format(getPrice()) : "null") + "\"" +
                ", from=\"" + (getFrom() != null ? getFrom().format(dateFormatter) : "null") + "\"" +
                ", to=\"" + (getTo() != null ? getTo().format(dateFormatter) : "null") + "\"" +
                ", origin=\"" + origin + "\"" +
                ", destination=\"" + destination + "\"" +
                ", flightNumber=\"" + flightNumber + "\"" +
                ", baggageIncluded=\"" + baggageIncluded + "\"" +
                "}";
    }
}