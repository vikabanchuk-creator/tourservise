package by.psu.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.regex.Pattern;

public class Client {
    private UUID clientId;
    private String fullName;
    private String email;
    private String phone;
    private String passportNumber;
    private int loyaltyPoints;

    public Client(String fullName, String email, String phone, String passportNumber, int loyaltyPoints) {

        if (fullName == null) {
            throw new TourServiceValidationException("fullName=null (не может быть null)");
        }
        String[] nameParts = fullName.trim().split("\\s+");
        if (nameParts.length < 2) {
            throw new TourServiceValidationException("fullName=" + fullName + " (должно быть минимум 2 слова)");
        }
        for (String part : nameParts) {
            if (part.length() < 2) {
                throw new TourServiceValidationException("fullName=" + fullName + " (каждое слово должно содержать минимум 2 символа)");
            }
        }

        if (email == null) {
            throw new TourServiceValidationException("email=null (не может быть null)");
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (!emailPattern.matcher(email).matches()) {
            throw new TourServiceValidationException("email=" + email + " (не соответствует формату email)");
        }

        if (phone == null) {
            throw new TourServiceValidationException("phone=null (не может быть null)");
        }
        String phoneRegex = "^\\+[0-9]{10,15}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        if (!phonePattern.matcher(phone).matches()) {
            throw new TourServiceValidationException("phone=" + phone + " (должен начинаться с + и содержать от 10 до 15 цифр)");
        }

        if (passportNumber == null) {
            throw new TourServiceValidationException("passportNumber=null (не может быть null)");
        }
        if (passportNumber.length() != 10) {
            throw new TourServiceValidationException("passportNumber=" + passportNumber + " (должен содержать ровно 10 символов)");
        }

        if (loyaltyPoints < 0) {
            throw new TourServiceValidationException("loyaltyPoints=" + loyaltyPoints + " (не может быть отрицательным)");
        }

        this.clientId = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.passportNumber = passportNumber;
        this.loyaltyPoints = loyaltyPoints;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            this.loyaltyPoints += points;
        }
    }

    public BigDecimal getDiscountRate() {
        if (loyaltyPoints >= 0 && loyaltyPoints <= 99) {
            return BigDecimal.ZERO;
        } else if (loyaltyPoints <= 499) {
            return new BigDecimal("0.05");
        } else if (loyaltyPoints <= 999) {
            return new BigDecimal("0.10");
        } else if (loyaltyPoints <= 4999) {
            return new BigDecimal("0.15");
        } else {
            return new BigDecimal("0.20");
        }
    }


    public String getMaskedPassportNumber() {
        if (passportNumber == null || passportNumber.length() < 4) {
            return "****";
        }
        int maskLength = passportNumber.length() - 4;
        return "*".repeat(maskLength) + passportNumber.substring(maskLength);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=\"" + clientId + "\"" +
                ", fullName=\"" + fullName + "\"" +
                ", email=\"" + email + "\"" +
                ", phone=\"" + phone + "\"" +
                ", passportNumber=\"" + getMaskedPassportNumber() + "\"" +
                ", loyaltyPoints=\"" + loyaltyPoints + "\"" +
                ", discountRate=\"" + getDiscountRate().multiply(new BigDecimal("100")) + "%\"" +
                "}";
    }
}