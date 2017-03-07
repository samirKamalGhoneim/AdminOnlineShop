/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beshoy
 */
public class Database {

    private Connection con;

    public Database() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshopping", "root", "");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName() + "1").log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName() + "2").log(Level.SEVERE, null, ex);
        }
    }// end constuctor 

    public int addProuduct(String name, String desc, double price, int quantity, String file, double discount, String category) {
        int count = 0;
        try {

            PreparedStatement ps = con.prepareStatement("INSERT INTO products(productname,description,price,imageurl,quantity,categoryname,discount) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setDouble(3, price);
            ps.setString(4, file);
            ps.setInt(5, quantity);
            ps.setString(6, category);
            ps.setDouble(7, discount);

            count = ps.executeUpdate();

        } catch (SQLException ex) {
            count = 0;
        }
        return count;

    }

    public void addCategory(String name, String desc) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO category(cat_name,cat_desc) VALUES (?,?)");
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName() + "3").log(Level.SEVERE, null, ex);
        }

    }

    public void addQunatity(String id, String qunt) {
        try {
            int qunt1 = Integer.parseInt(qunt);
            int Id = Integer.parseInt(id);
            PreparedStatement ps = con.prepareStatement("UPDATE products SET quantity = ? where product_id = ? ");
            ps.setInt(1, qunt1);
            ps.setInt(2, Id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName() + "3").log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet ShowAllUser() {
        try {
            PreparedStatement ps = con.prepareStatement("Select * from user");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet ShowAllProduct() {
        try {
            PreparedStatement ps = con.prepareStatement("Select * from products");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet ShowAllCategory() {
        try {
            PreparedStatement ps = con.prepareStatement("Select * from category");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteProduct(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM  products where product_id = ? ");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM  user where u_id = ? ");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCategory(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM  category where cat_id = ? ");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet GetProductById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("Select * from products where product_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getCategoryById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("Select * from category where cat_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getUserById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("Select * from user where u_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getHistoryByUserId(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT history.p_id,history.u_id, history.unit_price, history.quantity ,product.p_id, product.p_name FROM history,product WHERE history.p_id = product.p_id and history.u_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getcartByUserId(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT cart.u_id, cart.quantity, cart.p_id,product.p_id, product.p_name FROM cart,product WHERE cart.p_id=product.p_id and cart.u_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void updateCategory(int id, String name, String desc) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE `category` SET `cat_name`=?,`cat_desc`=? WHERE cat_id=?");
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setInt(3, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName() + "3").log(Level.SEVERE, null, ex);
        }

    }

    public void updateProduct(int id, String name, String desc, int price, int cat) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE `products` SET`productname`=?,`description`=?,`price`=?,`categoryname`=? WHERE  `product_id`=?");
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setInt(3, price);
            ps.setString(4, "mobile");
            ps.setInt(5, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName() + "3").log(Level.SEVERE, null, ex);
        }

    }

    public void updateUser(int id, String name, String email, String address, String mobile) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE `user` SET `username`=?,`email`=?,`address`=?,`mobile`=?WHERE `u_id`=?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setString(4, mobile);
            ps.setInt(5, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName() + "3").log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet userProfile(int id) {
        return null;
    }

}
