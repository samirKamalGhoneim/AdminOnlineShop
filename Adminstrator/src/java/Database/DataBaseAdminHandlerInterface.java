/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import dto.Product;
import dto.User;
import java.util.ArrayList;

/**
 *
 * @author Dina Ashraf
 */
public interface DataBaseAdminHandlerInterface {

    public boolean addProduct(Product product);

    public boolean editProduct(Product product);

    public boolean removeProduct(Product product);

    public ArrayList<User> getAllUsers();

    public User getUser(String email);

    public int editQuantity(int id, int quantity);

    // updates
    public boolean addRechargeCards(int number, int amount);

    //end of updates
}
