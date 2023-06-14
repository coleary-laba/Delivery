
import sql.jdbc.*;
import sql.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]){
        System.out.println("--category--");
        CategoryDAO catDao = new CategoryDAO();
        Category cat = new Category(1, "Phones");
        Category cat2 = new Category(2, "Cars");
        catDao.insert(cat);
        catDao.insert(cat2);
        System.out.println(catDao.getById(1).getCategoryName());
        cat = new Category(2, "Food");
        catDao.update(cat);
        System.out.println(catDao.getById(2).getCategoryName());

        System.out.println("--product--");
        ProductDAO proDAO = new ProductDAO();
        Product prod1 = new Product(1, "IPhone 11", 299.99, 1);
        Product prod2 = new Product(2, "Apple", 1.99, 2);
        proDAO.insert(prod1);
        proDAO.insert(prod2);
        System.out.println(proDAO.getById(1).getProductName());
        prod1 = new Product(1, "IPhone 12",399.99, 1);
        proDAO.update(prod1);
        proDAO.delete(2);
        prod2 = new Product(2, "Carrots", 2.99, 2);
        proDAO.insert(prod2);

        System.out.println("--customer--");
        CustomerDAO custDAO = new CustomerDAO();
        Customer cust1 = new Customer(1, "Bob Johnson", "123-456-7890");
        Customer cust2 = new Customer(2, "Billy Bobby", "456-789-0123");
        custDAO.insert(cust1);
        custDAO.insert(cust2);
        System.out.println(custDAO.getById(1).getCustomerName());
        cust1 = new Customer(1, "Bobby John", "123-456-7890");
        custDAO.update(cust1);
        System.out.println(custDAO.getById(1).getCustomerName());

        System.out.println("--personnel--");
        PersonnelDAO perDAO = new PersonnelDAO();
        Personnel per1 = new Personnel(1, "JohnBoy Smith", "123 Pike Drive", "234-567-8901");
        perDAO.insert(per1);
        per1 = new Personnel(1, "John Smith", "123 Pine Drive", "234-567-8901");
        perDAO.update(per1);
        System.out.println(perDAO.getById(1).getPersonnelName());
        Personnel per2 = new Personnel(2, "Johhny Smythe", "124 32nd Ave", "345-567-8901");
        perDAO.insert(per2);
        List<Personnel> perList = perDAO.getAll();
        for(int i = 0; i < perList.size(); i++){
            System.out.println(perList.get(1).getPersonnelName());
        }

        System.out.println("--order--");
        OrderDAO orderDAO = new OrderDAO();
        Date date = new Date(2023,20, 21);
        Order ord1 = new Order(1, 1, 1, date);
        Order ord2 = new Order(2, 2, 2, date);
        Order ord3 = new Order(3, 1, 2, date);
        orderDAO.insert(ord1);
        orderDAO.insert(ord2);
        orderDAO.insert(ord3);
        int size = orderDAO.getAll().size();
        System.out.println("Size of orders: "+size);
        orderDAO.delete(3);
        size = orderDAO.getAll().size();
        System.out.println("Size of orders: "+size);

        System.out.println("--order detail--");
        OrderDetailDAO ordDetDAO = new OrderDetailDAO();
        OrderDetail ordeDet1 = new OrderDetail(1, 1, 3);
        OrderDetail ordeDet2 = new OrderDetail(2, 2, 7);
        ordDetDAO.insert(ordeDet1);
        ordDetDAO.insert(ordeDet2);
        ordeDet1 = new OrderDetail(1, 1, 8);
        ordDetDAO.update(ordeDet1);
        System.out.println("--delivery address--");
        DeliveryAddressDAO delivAddDAO = new DeliveryAddressDAO();
        DeliveryAddress delivAdd1 = new DeliveryAddress(1, 1, "1221 Alabama Lane");
        DeliveryAddress delivAdd2 = new DeliveryAddress(2, 2, "8686 Allen Ave");
        delivAddDAO.insert(delivAdd1);
        delivAddDAO.insert(delivAdd2);
        delivAdd1 = new DeliveryAddress(1, 1, "1231 Alabama Lane");
        delivAddDAO.update(delivAdd1);
        System.out.println(delivAddDAO.getById(1).getAddress());

        System.out.println("--delivery status--");
        DeliveryStatusDAO delivStatDAO = new DeliveryStatusDAO();
        DeliveryStatus delivStat1 = new DeliveryStatus(1, 1, 1, "Unfulfilled");
        DeliveryStatus delivStat2 = new DeliveryStatus(2, 2, 2, "Unfulfilled");
        delivStatDAO.insert(delivStat1);
        delivStatDAO.insert(delivStat2);
        System.out.println(delivStatDAO.getById(1).getStatus());
        delivStat1 = new DeliveryStatus(1, 1, 1, "Fulfilled");
        delivStatDAO.update(delivStat1);
        System.out.println(delivStatDAO.getById(1).getStatus());

        System.out.println("--payment--");
        PaymentDAO payDAO = new PaymentDAO();
        Payment pay1 = new Payment(1, 1, date, 100.00);
        Payment pay2 = new Payment(2, 2, date, 200.00);
        payDAO.insert(pay1);
        payDAO.insert(pay2);
        pay1 = new Payment(1, 1, date, 1200);
        payDAO.update(pay1);
        System.out.println(payDAO.getById(1).getAmount());

        System.out.println("--customer reviews--");
        CustomerReviewDAO custRevDAO = new CustomerReviewDAO();
        CustomerReview custRev = new CustomerReview(1, 1, 1, 8, "Customer was cool, no problems");
        custRevDAO.insert(custRev);
        System.out.println(custRevDAO.getById(1).getReviewText());
        custRev = new CustomerReview(1, 1, 1, 2, "Customer was cool, their dog... not so much");
        custRevDAO.update(custRev);
        System.out.println(custRevDAO.getById(1).getReviewText());

        System.out.println("--personnel review--");
        PersonnelReviewDAO perRevDAO = new PersonnelReviewDAO();
        PersonnelReview perRev = new PersonnelReview(1, 1, 1, 8, "Everything was delivered intact, delivery person was cool");
        perRevDAO.insert(perRev);
        System.out.println(perRevDAO.getById(1).getReviewText());
        perRev = new PersonnelReview(1, 1, 1, 2, "Everything was broken, but I guess teh delivery person was cool");
        perRevDAO.update(perRev);
        System.out.println(perRevDAO.getById(1).getReviewText());

    }
}
