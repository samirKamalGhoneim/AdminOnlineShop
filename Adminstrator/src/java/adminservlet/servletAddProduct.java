package adminservlet;

import Database.DataBaseHandler;
import dto.ImagesUrl;
import dto.Product;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class servletAddProduct extends HttpServlet {

    String name, description, image, fullName;
    double price;
    private int quantity;
    private double discount;
    private String category;
    DataBaseHandler instance;
    ImagesUrl images = new ImagesUrl();
    int i = 0;
    private String mainImage;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    String temp = item.getFieldName();
                    String tempValue = item.getString();
                    if (temp.equals("name")) {
                        name = tempValue;
                    }
                    if (temp.equals("description")) {
                        description = tempValue;
                    }
                    if (temp.equals("quantity")) {
                        quantity = Integer.parseInt(tempValue);
                    }
                    if (temp.equals("price")) {
                        price = Double.parseDouble(tempValue);
                    }
                    if (temp.equals("discount")) {
                        discount = Double.parseDouble(tempValue);
                    }
                    if (temp.equals("category")) {
                        category = tempValue;
                    }
                } else {
                    try {
                        String itemName = item.getName();
                        System.out.println(itemName);
                        String path = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "product" + File.separator;

                        File savedFile = new File(path + itemName);
                        item.write(savedFile);
                        images.addItem(path + itemName);
                        if (i < 1) {
                            mainImage = path + itemName;
                        }
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        instance = DataBaseHandler.getinstance();
        if (instance.checkProductNameExistance(name)) {
            request.setAttribute("invalid", "yes");
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);

        } else {
            Product product = new Product(name, mainImage, images, quantity, price, description, discount, category);
            boolean flag = instance.addProduct(product);
            if (flag) {
                request.setAttribute("valid", "yes");
                request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
            } else {
                request.removeAttribute("valid");
                request.getRequestDispatcher("/addProduct.jsp").forward(request, response);

            }
        }
    }

}
