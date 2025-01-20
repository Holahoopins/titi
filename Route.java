import java.util.List;
import java.util.Objects;

class Route {
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private List<String> locationPoints;

    public Route(String id, double distance, int popularity, boolean isFavorite, List<String> locationPoints) {
        this.id = id;
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public String getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public List<String> getLocationPoints() {
        return locationPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(route.distance, distance) == 0 &&
                Objects.equals(locationPoints.get(0), route.locationPoints.get(0)) &&
                Objects.equals(locationPoints.get(locationPoints.size() - 1), route.locationPoints.get(route.locationPoints.size() - 1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, locationPoints.get(0), locationPoints.get(locationPoints.size() - 1));
    }
}
