package com.thoughtworks.movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    public static final String RENTAL_RECORD_FOR = "Rental Record for ";
    public static final String AMOUNT_OWED_IS = "Amount owed is ";
    public static final String YOU_EARNED = "You earned ";
    public static final String FREQUENT_RENTER_POINTS = " frequent renter points";
    public static final String NEW_LINE = "\n";
    private static final String LINE_BREAK = "<br/>";
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {

        String result = RENTAL_RECORD_FOR + getName() + NEW_LINE;

        for (Rental rental : rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t" +
                    String.valueOf(rental.amount()) + NEW_LINE;

        }
        result += AMOUNT_OWED_IS + String.valueOf(totalAmount()) + NEW_LINE;
        result += YOU_EARNED + String.valueOf(totalFrequentRenterPoints())
                + FREQUENT_RENTER_POINTS;
        return result;
    }

    public String htmlStatement() {

        String result = "<h3>" + RENTAL_RECORD_FOR  + getName() + "</h3>" + LINE_BREAK+"<p>";

        for (Rental rental : rentals) {
            result +=  rental.getMovie().getTitle() + ":<b>" +
                    String.valueOf(rental.amount()) +"</b>"+ LINE_BREAK;
        }

        result +="</p><p>" +AMOUNT_OWED_IS + "<b>" + String.valueOf(totalAmount()) + "</b></p>" + LINE_BREAK
                +"<p>" +YOU_EARNED + "<b>" + String.valueOf(totalFrequentRenterPoints())
                + "</b>" + FREQUENT_RENTER_POINTS+"</p>";
        return result;

    }

    private double totalAmount() {

        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.amount();
        }
        return totalAmount;
    }

    private int totalFrequentRenterPoints() {
        int frequentRenterPoints = 0;

        for (Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

}

