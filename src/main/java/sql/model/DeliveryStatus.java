package sql.model;

public class DeliveryStatus {
    private int statusId;
    private int orderId;
    private int routeId;
    private String status;

    public DeliveryStatus(int statusId, int orderId, int routeId, String status) {
        this.statusId = statusId;
        this.orderId = orderId;
        this.routeId = routeId;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getRouteId() {
        return routeId;
    }

    public String getStatus() {
        return status;
    }
}

