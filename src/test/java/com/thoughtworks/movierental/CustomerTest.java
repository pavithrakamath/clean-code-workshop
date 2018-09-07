package com.thoughtworks.movierental;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
    private Customer customer;
    private Movie regularMovie;
    private Movie newMovie;
    private Movie childrenMovie;

    @Before
    public void setUp() {
        customer = new Customer("Sam");
        regularMovie = new Movie("DDLJ", Movie.REGULAR);
        newMovie = new Movie("MI4", Movie.NEW_RELEASE);
        childrenMovie = new Movie("Babys Day out", Movie.CHILDRENS);
    }

    @Test
    public void whenNoRentalStatementShouldHaveZeroAmoutZeroPointsAndCorrectName() {

        Assert.assertEquals("Rental Record for Sam\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points", customer.statement());
    }

    @Test
    public void whenRentedRegularForOneDayShouldGetStatement() {
        Rental regularRental = new Rental(regularMovie, 1);
        customer.addRental(regularRental);

        Assert.assertEquals("Rental Record for Sam\n" +
                "\tDDLJ\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void whenRentedRegularForMoreThanTwoDaysShouldGetStatement() {
        Rental regularRental = new Rental(regularMovie, 2);
        customer.addRental(regularRental);

        Assert.assertEquals("Rental Record for Sam\n" +
                "\tDDLJ\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void whenRentedAllCategoryMoreThanTwoDaysShouldGetStatement() {
        Rental regularRental = new Rental(regularMovie, 3);
        Rental childrenRental = new Rental(childrenMovie, 4);
        Rental newMovieRental = new Rental(newMovie, 3);

        customer.addRental(regularRental);
        customer.addRental(childrenRental);
        customer.addRental(newMovieRental);

        Assert.assertEquals("Rental Record for Sam\n" +
                "\tDDLJ\t3.5\n" +
                "\tBabys Day out\t3.0\n" +
                "\tMI4\t9.0\n" +
                "Amount owed is 15.5\n" +
                "You earned 4 frequent renter points", customer.statement());
    }

    @Test
    public void whenNoRentalHtmlStatementShouldHaveZeroAmoutZeroPointsAndCorrectName() {
        Assert.assertEquals("<h3>Rental Record for Sam</h3><BR/><p></p><p>Amount owed is <b>0.0</b></p><BR/><p>You earned <b>0</b> frequent renter points</p>",
                customer.statement());
    }

    @Test
    public void whenRentedAllCategoryMoreThanTwoDaysShouldGetHtmlStatement() {
        Rental regularRental = new Rental(regularMovie, 3);
        Rental childrenRental = new Rental(childrenMovie, 4);
        Rental newMovieRental = new Rental(newMovie, 3);

        customer.addRental(regularRental);
        customer.addRental(childrenRental);
        customer.addRental(newMovieRental);
        Assert.assertEquals("<h3>Rental Record for Sam</h3><br/><p>DDLJ:<b>3.5</b><br/>Babys Day out:<b>3.0</b><br/>MI4:<b>9.0</b><br/></p><p>Amount owed is <b>15.5</b></p><br/><p>You earned <b>4</b> frequent renter points</p>",
                customer.htmlStatement());
    }


}