package by.psu.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public abstract class TourService {
    private Integer id;
    private String name;
    private BigDecimal price;
    private LocalDate from;
    private LocalDate to;

    // Конструктор по умолчанию
    public TourService() {
    }

    // Конструктор со всеми параметрами
    public TourService(Integer id, String name, BigDecimal price, LocalDate from, LocalDate to) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.from = from;
        this.to = to;
    }

    public boolean isAvailableON(LocalDate date) {
        return !(from.isAfter(date) || to.isBefore(date));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public abstract BigDecimal calculateTotalPrice(int participants);

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#,##0.00");

        return "TourService{" +
                "id=\"" + id + "\"" +
                ", name=\"" + name + "\"" +
                ", price=\"" + (price != null ? df.format(price) : "null") + "\"" +
                ", from=\"" + (from != null ? from.format(dateFormatter) : "null") + "\"" +
                ", to=\"" + (to != null ? to.format(dateFormatter) : "null") + "\"" +
                "}";
    }


}