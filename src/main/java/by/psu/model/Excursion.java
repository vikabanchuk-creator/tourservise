package by.psu.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Excursion extends TourService {
    private String guidName;
    private Duration duration;
    private Difficulty difficulty;
    private boolean lunchIncluded;


    // Конструктор по умолчанию
    public Excursion() {
        super();
    }

    // Конструктор со всеми параметрами
    public Excursion(Integer id, String name, BigDecimal price, LocalDate from, LocalDate to,
                     String guideName, String excursionType, boolean lunchIncluded) {
        super(id, name, price, from, to);
        this.guidName = guideName;
        this.duration = Duration.parse(excursionType);
        this.lunchIncluded = lunchIncluded;
    }

    public String getGuidName() {
        return guidName;
    }

    public void setGuidName(String guidName) {
        this.guidName = guidName;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public boolean isLunchIncluded() {
        return lunchIncluded;
    }

    public void setLunchIncluded(boolean lunchIncluded) {
        this.lunchIncluded = lunchIncluded;
    }
    @Override
    public BigDecimal calculateTotalPrice(int participants) {
        BigDecimal totalPrice = getPrice()
                .multiply(BigDecimal.valueOf(participants))
                .multiply(difficulty.getMultiplier());

        if (participants < 10) {
            totalPrice = totalPrice.multiply(new BigDecimal("0.9"));
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#,##0.00");

        String durationStr = (duration != null)
                ? String.format("%d ч. %d мин.", duration.toHours(), duration.toMinutesPart())
                : "null";

        return "Excursion{" +
                "id=\"" + getId() + "\"" +
                ", name=\"" + getName() + "\"" +
                ", price=\"" + (getPrice() != null ? df.format(getPrice()) : "null") + "\"" +
                ", from=\"" + (getFrom() != null ? getFrom().format(dateFormatter) : "null") + "\"" +
                ", to=\"" + (getTo() != null ? getTo().format(dateFormatter) : "null") + "\"" +
                ", guidName=\"" + (guidName != null ? guidName : "null") + "\"" +
                ", duration=\"" + durationStr + "\"" +
                ", difficulty=\"" + (difficulty != null ? difficulty.name() : "null") + "\"" +
                "}";
    }
}