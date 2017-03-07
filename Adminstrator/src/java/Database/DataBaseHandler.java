/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import dto.CreditCard;
import dto.ImagesUrl;
import dto.Product;
import dto.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dina Ashraf
 */
// close prepared statements
public class DataBaseHandler implements DataBaseAdminHandlerInterface, DataBaseHandlerInterface {

    // close connection in context destruction
    private static Connection connection = null;
    private static DataBaseHandler instance = null;

    public static DataBaseHandler getinstance() {
        if (instance == null) {
            return new DataBaseHandler();
        }
        return instance;
    }

    private DataBaseHandler() {
        try {
            System.out.println("inside constructor");
            Class.forName("com.mysql.jdbc.Driver");
            DataBaseHandler.connection = createConnection();
            System.out.println("success");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private Connection getConnection() {
        if (DataBaseHandler.connection == null) {
            DataBaseHandler.connection = createConnection();
        }

        return DataBaseHandler.connection;
    }

    private Connection createConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping", "root", "");
            System.out.println("connected");
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addProduct(Product product) {
        try {
            PreparedStatement preparedStatment1 = getConnection().prepareStatement("insert into products "
                    + "(productName,price,quantity,imageUrl,description,discount,categoryName)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatment1.setString(1, product.getProductName());
            preparedStatment1.setDouble(2, product.getPrice());
            preparedStatment1.setInt(3, product.getQuantity());
            preparedStatment1.setString(4, product.getMainImageUrl());
            preparedStatment1.setString(5, product.getDescription());
            preparedStatment1.setDouble(6, product.getDiscount());
            preparedStatment1.setString(7, product.getCategoryName());

            if (preparedStatment1.executeUpdate() > 0) {

                PreparedStatement preparedStatment2 = getConnection().prepareStatement("select product_id "
                        + "from products ORDER BY product_id DESC LIMIT 1");
                ResultSet resultSet = preparedStatment2.executeQuery();

                ///////////////////////////////////////////////////
                if (resultSet.next()) {
                    int productID = resultSet.getInt("product_id");
                    ArrayList<String> imagesArray = new ArrayList<String>();
                    imagesArray = product.getOtherImagesUrls().getImagesUrl();
                    PreparedStatement preparedStatment3 = null;
                    for (String imgUrl : imagesArray) {
                        preparedStatment3 = getConnection().prepareStatement("insert into "
                                + "productImages (imageUrl,products_product_id)"
                                + "values (?,?)");
                        preparedStatment3.setString(1, imgUrl);
                        preparedStatment3.setInt(2, productID);
                        if (preparedStatment3.executeUpdate() == 0) {
                            return false;
                        }
                    }
                    return true;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    //// check column names
    // also delete images url then insert new images using product id
    @Override
    public boolean editProduct(Product product) {
        try {
            PreparedStatement preparedStatment = getConnection().prepareStatement("UPDATE products SET "
                    + "productName=? ,price=? ,quantity=?, imageUrl=?, description=?,"
                    + "discount=? ,categoryName=? WHERE product_id=?");
            preparedStatment.setString(1, product.getProductName());
            preparedStatment.setDouble(2, product.getPrice());
            preparedStatment.setInt(3, product.getQuantity());
            preparedStatment.setString(4, product.getMainImageUrl());
            preparedStatment.setString(5, product.getDescription());
            preparedStatment.setDouble(6, product.getDiscount());
            preparedStatment.setString(7, product.getCategoryName());
            preparedStatment.setInt(8, product.getId());
            if (preparedStatment.executeUpdate() > 0) {
                PreparedStatement preparedStatment2 = getConnection().prepareStatement("delete from productimages "
                        + "where products_product_id=?");
                preparedStatment2.setInt(1, product.getId());
                if (preparedStatment2.executeUpdate() > 0) {
                    ArrayList<String> otherimages = product.getOtherImagesUrls().getImagesUrl();
                    for (String imgUrl : otherimages) {
                        preparedStatment2 = getConnection().prepareStatement("insert into "
                                + "productImages (imageUrl,products_product_id)"
                                + "values (?,?)");
                        preparedStatment2.setString(1, imgUrl);
                        preparedStatment2.setInt(2, product.getId());
                        preparedStatment2.executeUpdate();
                    }
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeProduct(Product product) {
        try {
            PreparedStatement preparedStatment1 = getConnection().prepareStatement("DELETE FROM productImages "
                    + "WHERE products_product_id=?");
            preparedStatment1.setInt(1, product.getId());
            if (preparedStatment1.executeUpdate() > 0) {
                PreparedStatement preparedStatment2 = getConnection().prepareStatement("DELETE FROM products "
                        + "WHERE product_id=?");
                preparedStatment2.setInt(1, product.getId());
                return preparedStatment2.executeUpdate() > 0;
            } else {
                // can't remove product image from productimages table 
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //check column names
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from user");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("email"),
                        resultSet.getString("imageurl"),
                        resultSet.getString("gender"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("birthDate").toLocalDate(),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("type"),
                        resultSet.getString("address"));
                preparedStatement = getConnection().prepareStatement("select * from creditcard where number = ?");
                preparedStatement.setLong(1, resultSet.getLong("creditCard_number"));
                ResultSet resultSet2 = preparedStatement.executeQuery();
                if (resultSet2.next()) {
                    CreditCard creditCard = new CreditCard(resultSet2.getLong("number"),
                            resultSet2.getDate("expireDate").toLocalDate(), resultSet2.getDouble("balance"));
                    user.setCreditCard(creditCard);
                    userList.add(user);
                }
            }
            return userList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUser(String email) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from user where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getString("email"),
                        resultSet.getString("imageurl"),
                        resultSet.getString("gender"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("birthDate").toLocalDate(),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("type"),
                        resultSet.getString("address"));
                preparedStatement = getConnection().prepareStatement("select * from creditcard where number = ?");
                preparedStatement.setLong(1, resultSet.getLong("creditCard_number"));
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    CreditCard creditCard = new CreditCard(resultSet.getLong("number"), resultSet.getDate("expireDate").toLocalDate(), resultSet.getDouble("balance"));
                    user.setCreditCard(creditCard);
                    return user;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkEmailExistance(String email) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from User where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                // email found so its not valid to register with this email
                return true;
            } else {
                // email not found so its valid to register with this email
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //couldnt know if email exists or not so i cant make user register
            return true;
        }
    }

    @Override
    public boolean signup(User user) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO creditcard(number,expireDate,balance) VALUES (?,?,?)");
            preparedStatement.setLong(1, user.getCreditCard().getCreditCardNumber());
            preparedStatement.setDate(2, Date.valueOf(user.getCreditCard().getExpireDate()));
            preparedStatement.setDouble(3, user.getCreditCard().getBalance());
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                preparedStatement = getConnection().prepareStatement("INSERT INTO user(email,gender,firstName,lastName,birthDate,password,phone,imageurl,type,address,creditCard_number) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getGender());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setString(4, user.getLastName());
                preparedStatement.setDate(5, Date.valueOf(user.getbDate()));
                preparedStatement.setString(6, user.getPassword());
                preparedStatement.setString(7, user.getPhone());
                preparedStatement.setString(8, user.getImageUrl());
                preparedStatement.setString(9, user.getType());
                preparedStatement.setString(10, user.getAddress());
                preparedStatement.setLong(11, user.getCreditCard().getCreditCardNumber());
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    //registered
                    // insert into credit card
                    return true;
                } else {
                    //couldnt register
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User login(String email, String password) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from User where email=? and password =?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getString("email"),
                        resultSet.getString("imageurl"),
                        resultSet.getString("gender"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("birthDate").toLocalDate(),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("type"),
                        resultSet.getString("address"));
                preparedStatement = getConnection().prepareStatement("select * from creditcard where number = ?");
                preparedStatement.setLong(1, resultSet.getLong("creditCard_number"));
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    CreditCard creditCard = new CreditCard(resultSet.getLong("number"), resultSet.getDate("expireDate").toLocalDate(), resultSet.getDouble("balance"));
                    user.setCreditCard(creditCard);
                    return user;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product> searchProducts(String category, String productName, double productPrice) {
        ArrayList<Product> productList = new ArrayList<Product>();
        if (productPrice > 0) {
            return SearchProductGeneric(category, productName, productPrice, productList);
        } else {
            return searchWithCategoryAndName(category, productName, productList);
        }
    }

    private ArrayList<Product> searchWithCategoryAndName(String category, String productName, ArrayList<Product> productList) {
        try {
            PreparedStatement preparedStatment2 = getConnection().prepareStatement("select * from products"
                    + " where (categoryName like ?) OR (productName like ?)");
            preparedStatment2.setString(1, "%" + category + "%");
            preparedStatment2.setString(2, "%" + productName + "%");
            ResultSet resultSet2 = preparedStatment2.executeQuery();
            ImagesUrl otherImagesUrl = new ImagesUrl();
            while (resultSet2.next()) {
                otherImagesUrl.addItem(resultSet2.getString("imageUrl"));
                productList.add(new Product(resultSet2.getString("productName"), resultSet2.getString("imageUrl"),
                        otherImagesUrl, resultSet2.getInt("quantity"),
                        resultSet2.getDouble("price"), resultSet2.getString("description"),
                        resultSet2.getDouble("discount"), resultSet2.getString("categoryName")));
            }
            return productList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private ArrayList<Product> SearchProductGeneric(String category, String productName, double productPrice, ArrayList<Product> productList) {
        try {
            PreparedStatement preparedStatment1 = getConnection().prepareStatement("select * from products"
                    + " where (categoryName like ?) OR (productName like ?) OR (price <= ?)");
            preparedStatment1.setString(1, "%" + category + "%");
            preparedStatment1.setString(2, "%" + productName + "%");
            preparedStatment1.setDouble(3, productPrice);
            ResultSet resultSet = preparedStatment1.executeQuery();
            ImagesUrl otherImagesUrl = new ImagesUrl();
            while (resultSet.next()) {

                int productID = resultSet.getInt("product_id");
                PreparedStatement imagesPreparedStatement = getConnection().
                        prepareStatement("select * from productimages where"
                                + " products_product_id =? ");
                imagesPreparedStatement.setInt(1, productID);
                ResultSet ImagesresultSet = imagesPreparedStatement.executeQuery();
                while (ImagesresultSet.next()) {
                    otherImagesUrl.addItem(ImagesresultSet.getString("imageUrl"));
                }
                Product product = new Product(resultSet.getString("productName"),
                        resultSet.getString("imageUrl"),
                        otherImagesUrl, resultSet.getInt("quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getDouble("discount"),
                        resultSet.getString("categoryName"), productID);
                productList.add(product);
            }
            return productList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Product> getAllproducts() {
        ArrayList<Product> productList = new ArrayList<Product>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from products");
            ResultSet resultSet = preparedStatement.executeQuery();
            ImagesUrl otherImagesUrl = new ImagesUrl();
            while (resultSet.next()) {

                int productID = resultSet.getInt("product_id");
                PreparedStatement imagesPreparedStatement = getConnection().prepareStatement("select * from productimages"
                        + " where products_product_id =? ");
                imagesPreparedStatement.setInt(1, productID);
                ResultSet ImagesresultSet = imagesPreparedStatement.executeQuery();
                while (ImagesresultSet.next()) {
                    otherImagesUrl.addItem(ImagesresultSet.getString("imageUrl"));
                }
                Product product = new Product(resultSet.getString("productName"),
                        resultSet.getString("imageUrl"),
                        otherImagesUrl, resultSet.getInt("quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getDouble("discount"),
                        resultSet.getString("categoryName"), productID);
                productList.add(product);
            }
            return productList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean editUserDetials(User user) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("update user "
                    + "set firstName = ?,"
                    + "lastName = ?,"
                    + "birthDate = ?,"
                    + "password = ?,"
                    + "phone = ?,"
                    + "imageurl =?,"
                    + "address = ? "
                    + "where email =?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getbDate()));
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getImageUrl());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getEmail());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public double getUserBalance(String email) {
        try {
            PreparedStatement preparedStatment = getConnection().prepareStatement("select creditCard_number "
                    + "from user where email=?");
            preparedStatment.setString(1, email);
            ResultSet resultset = preparedStatment.executeQuery();
            if (resultset.next()) {
                int creditCardNum = resultset.getInt("creditCard_number");
                preparedStatment = getConnection().prepareStatement("select balance from creditcard where "
                        + "number=?");
                System.out.println(creditCardNum);
                preparedStatment.setInt(1, creditCardNum);
                resultset = preparedStatment.executeQuery();

                if (resultset.next()) {
                    System.out.println("enterd if");
                    return resultset.getDouble("balance");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        return 0;
    }

    @Override
    public boolean updateUserBalance(User user, double addedBalance) {

        try {
            System.out.println("added balnce " + addedBalance);
            double oldBalance = user.getCreditCard().getBalance();
            System.out.println("oldbalance " + oldBalance);
            double newBalance = oldBalance + addedBalance;
            System.out.println("new balace " + newBalance);
            PreparedStatement preparedStatment = getConnection().prepareStatement("update creditcard set balance =? where "
                    + "number=?");
            preparedStatment.setDouble(1, newBalance);
            preparedStatment.setLong(2, user.getCreditCard().getCreditCardNumber());
            if (preparedStatment.executeUpdate() > 0) {
                System.out.println("ddd");
                user.getCreditCard().setBalance(newBalance);
                System.out.println(user.getCreditCard().getBalance());
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean createOrder(String email, ArrayList<Product> products) {
        try {

            PreparedStatement preparedStatment1 = getConnection().prepareStatement("INSERT INTO `orders`(`date`, `status`, `User_email`) VALUES (?, ?, ?)");
            preparedStatment1.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatment1.setInt(2, 0);
            preparedStatment1.setString(3, email);
            if (preparedStatment1.executeUpdate() > 0) {
                PreparedStatement preparedStatment2 = getConnection().prepareStatement("select id from orders ORDER BY id DESC LIMIT 1");
                ResultSet resultSet = preparedStatment2.executeQuery();
                if (resultSet.next()) {
                    int orderID = resultSet.getInt("id");
                    return addProductsToOrder(orderID, products);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean addProductsToOrder(int orderID, ArrayList<Product> products) {
        for (Product product : products) {
            try {
                PreparedStatement preparedStatment1 = getConnection().
                        prepareStatement("insert into orderdetails "
                                + "(products_product_id,order_id,price,quantity)"
                                + "VALUES (?, ?,?,?)");
                preparedStatment1.setInt(1, product.getId());
                preparedStatment1.setInt(2, orderID);
                preparedStatment1.setDouble(3, product.getPrice());
                preparedStatment1.setInt(4, product.getQuantity());
                int rowCount = preparedStatment1.executeUpdate();
                if (rowCount == 0) {
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkProductNameExistance(String productName) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from products"
                    + " where productName=?");
            preparedStatement.setString(1, productName);
            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                // productName found 
                return true;
            } else {
                // productName not found 
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //couldnt know if productName exists or not so i cant add new product
            return true;
        }
    }

    @Override
    public boolean updateOrderStatus(String email, int status) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("update orders "
                    + "set status = ? where User_email = ?");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, email);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteOrder(String email) {
        try {
            PreparedStatement preparedStatement = getConnection().
                    prepareStatement("select id from order where status=0 and User_email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int orderID = resultSet.getInt("id");
                preparedStatement.getConnection().prepareStatement("delete from orderdetails where order_id=?");
                preparedStatement.setInt(1, orderID);
                if (preparedStatement.executeUpdate() > 0) {
                    preparedStatement.getConnection().prepareStatement("delete from order where id=?");
                    preparedStatement.setInt(1, orderID);
                    if (preparedStatement.executeUpdate() > 0) {
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Product getProduct(int productID) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from products "
                    + "where product_id=?");
            preparedStatement.setInt(1, productID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ImagesUrl otherImagesUrl = new ImagesUrl();
            if (resultSet.next()) {
                PreparedStatement imagesPreparedStatement = getConnection().prepareStatement("select * "
                        + "from productimages where products_product_id =? ");
                imagesPreparedStatement.setInt(1, productID);
                ResultSet ImagesresultSet = imagesPreparedStatement.executeQuery();
                while (ImagesresultSet.next()) {
                    otherImagesUrl.addItem(ImagesresultSet.getString("imageUrl"));
                }
                Product product = new Product(resultSet.getString("productName"),
                        resultSet.getString("imageUrl"),
                        otherImagesUrl, resultSet.getInt("quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getDouble("discount"),
                        resultSet.getString("categoryName"), productID);
                return product;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public ArrayList<Product> getDiscountedProducts() {
        ArrayList<Product> discountedProductList = new ArrayList<Product>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from products where discount > 0");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("product_id");
                PreparedStatement imagesPreparedStatement = getConnection().prepareStatement("select * from productimages"
                        + " where products_product_id =? ");
                imagesPreparedStatement.setInt(1, productID);
                ResultSet ImagesresultSet = imagesPreparedStatement.executeQuery();
                ImagesUrl otherImagesUrl = new ImagesUrl();
                while (ImagesresultSet.next()) {
                    otherImagesUrl.addItem(ImagesresultSet.getString("imageUrl"));
                }
                Product product = new Product(resultSet.getString("productName"),
                        resultSet.getString("imageUrl"),
                        otherImagesUrl, resultSet.getInt("quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getDouble("discount"),
                        resultSet.getString("categoryName"), productID);
                discountedProductList.add(product);
            }
            return discountedProductList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    // ajax in register and in edit
    public boolean CheckCreditCardNumberExistance(int number) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from creditcard"
                    + " where number=?");
            preparedStatement.setInt(1, number);
            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                // CreditCardNumber found so i can't add new one 
                return true;
            } else {
                // CreditCardNumber not found 
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //couldnt know if CreditCardNumber exists or not so i cant add new one 
            return true;
        }
    }

    // updates
    //end of updates
    @Override
    public int editQuantity(int id, int quantity) {
        int flag = 0;
        try {
            PreparedStatement st = getConnection().prepareCall("update products set quantity=? where product_id=?");
            st.setInt(1, quantity);
            st.setInt(2, id);
            flag = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
}
