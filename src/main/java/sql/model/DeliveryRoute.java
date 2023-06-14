package sql.model;

public class DeliveryRoute {
    private int routeId;
    private int personnelId;
    private String startLocation;
    private String endLocation;

    public DeliveryRoute(int routeId, int personnelId, String startLocation, String endLocation) {
        this.routeId = routeId;
        this.personnelId = personnelId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public int getRouteId() {
        return routeId;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }
}
