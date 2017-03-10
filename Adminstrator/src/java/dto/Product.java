/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Dina Ashraf
 */
public class Product {

    private String productName;
    private String mainImageUrl;
    private ImagesUrl otherImagesUrls;
    private int quantity;
    private double price;
    private String description;
    private double discount;
    private String categoryName;
    private int id;

    public Product(String productName, String mainImageUrl, ImagesUrl otherImagesUrls, int quantity, double price, String description, double discount, String categoryName) {
        this.productName = productName;
        this.mainImageUrl = mainImageUrl;
        this.otherImagesUrls = otherImagesUrls;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.categoryName = categoryName;
    }

    public Product(String productName, String mainImageUrl, ImagesUrl otherImagesUrls, int quantity, double price, String description, double discount, String categoryName, int id) {
        this.productName = productName;
        this.mainImageUrl = mainImageUrl;
        this.otherImagesUrls = otherImagesUrls;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.categoryName = categoryName;
        this.id = id;
    }

    public Product(String name, int id, double price, String desc, String cat) {
        this.productName = name;
        this.price = price;
        this.categoryName = cat;
        this.id = id;
        this.description = desc;

    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the mainImageUrl
     */
    public String getMainImageUrl() {
        return mainImageUrl;
    }

    /**
     * @param mainImageUrl the mainImageUrl to set
     */
    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    /**
     * @return the otherImagesUrls
     */
    public ImagesUrl getOtherImagesUrls() {
        return otherImagesUrls;
    }

    /**
     * @param otherImagesUrls the otherImagesUrls to set
     */
    public void setOtherImagesUrls(ImagesUrl otherImagesUrls) {
        this.otherImagesUrls = otherImagesUrls;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" + "productName=" + productName + ", mainImageUrl=" + mainImageUrl + ", otherImagesUrls=" + otherImagesUrls + ", quantity=" + quantity + ", price=" + price + ", description=" + description + ", discount=" + discount + ", categoryName=" + categoryName + ", id=" + id + '}';
    }
    // updates

    //end of updates
}
