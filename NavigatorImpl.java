import java.util.List;

public class NavigatorImpl implements Navigator {
    private final MyLinkedList<Route> routes = new MyLinkedList<>();

    @Override
    public void addRoute(Route route) {
        if (!contains(route)) {
            routes.add(route);
        }
    }

    @Override
    public void removeRoute(String routeId) {
        routes.removeIf(route -> route.getId().equals(routeId));
    }

    @Override
    public boolean contains(Route route) {
        return routes.contains(route);
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        for (Route route : routes) {
            if (route.getId().equals(routeId)) {
                return route;
            }
        }
        return null;
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = getRoute(routeId);
        if (route != null) {
            route.setPopularity(route.getPopularity() + 1);
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        MyLinkedList<Route> result = new MyLinkedList<>();
        for (Route route : routes) {
            List<String> points = route.getLocationPoints();
            if (points.get(0).equals(startPoint) && points.get(points.size() - 1).equals(endPoint)) {
                result.add(route);
            }
        }
        result.sort((r1, r2) -> {
            if (r1.isFavorite() != r2.isFavorite()) {
                return r1.isFavorite() ? -1 : 1;
            }
            int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
            if (distanceComparison != 0) {
                return distanceComparison;
            }
            return Integer.compare(r2.getPopularity(), r1.getPopularity());
        });
        return result;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        MyLinkedList<Route> result = new MyLinkedList<>();
        for (Route route : routes) {
            List<String> points = route.getLocationPoints();
            if (route.isFavorite() && points.contains(destinationPoint) && !points.get(0).equals(destinationPoint)) {
                result.add(route);
            }
        }
        result.sort((r1, r2) -> {
            int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
            if (distanceComparison != 0) {
                return distanceComparison;
            }
            return Integer.compare(r2.getPopularity(), r1.getPopularity());
        });
        return result;
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        MyLinkedList<Route> result = new MyLinkedList<>();
        for (Route route : routes) {
            result.add(route);
        }
        result.sort((r1, r2) -> {
            int popularityComparison = Integer.compare(r2.getPopularity(), r1.getPopularity());
            if (popularityComparison != 0) {
                return popularityComparison;
            }
            int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
            if (distanceComparison != 0) {
                return distanceComparison;
            }
            return Integer.compare(r1.getLocationPoints().size(), r2.getLocationPoints().size());
        });
        MyLinkedList<Route> topRoutes = new MyLinkedList<>();
        int count = 0;
        for (Route route : result) {
            if (count >= 5) {
                break;
            }
            topRoutes.add(route);
            count++;
        }
        return topRoutes;
    }
}
