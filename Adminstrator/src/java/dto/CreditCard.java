/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author Dina Ashraf
 */
public class CreditCard {

    private long creditCardNumber;
    private LocalDate expireDate;
    private double Balance;

    public CreditCard(long creditCardNumber, LocalDate expireDate, double Balance) {
        this.creditCardNumber = creditCardNumber;
        this.expireDate = expireDate;
        this.Balance = Balance;
    }

    /**
     * @return the creditCardNumber
     */
    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * @return the expireDate
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the Balance
     */
    public double getBalance() {
        return Balance;
    }

    /**
     * @param Balance the Balance to set
     */
    public void setBalance(double Balance) {
        this.Balance = Balance;
    }
    // updates

    //end of updates
}
