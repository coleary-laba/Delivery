package sql.model;

import java.util.Date;

public class Order {
    private int orderId;
    private int customerId;
    private int personnelId;
    private Date orderDate;

    public Order(int orderId, int customerId, int personnelId, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.personnelId = personnelId;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
