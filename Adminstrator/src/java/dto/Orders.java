/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author Ahmed labib
 */
public class Orders {
    private String userEmail;
    private String date;
    private ArrayList<Product> orderedProducts;

    public Orders(String userEmail, String date, ArrayList<Product> orderedProducts) {
        this.userEmail = userEmail;
        this.date = date;
        this.orderedProducts = orderedProducts;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the orderedProducts
     */
    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }

    /**
     * @param orderedProducts the orderedProducts to set
     */
    public void setOrderedProducts(ArrayList<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
    
}
