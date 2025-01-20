import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Navigator navigator = new NavigatorImpl();

        Route route1 = new Route("1", 100.0, 0, true, Arrays.asList("Moscow", "Kazan", "Ufa"));
        Route route2 = new Route("2", 200.0, 0, false, Arrays.asList("Moscow", "Saint Petersburg"));
        Route route3 = new Route("3", 150.0, 0, true, Arrays.asList("Moscow", "Kazan", "Chelyabinsk"));
        Route route4 = new Route("4", 100.0, 0, false, Arrays.asList("Moscow", "Kazan", "Ufa")); // Дубликат route1
        Route route5 = new Route("5", 300.0, 0, false, Arrays.asList("Moscow", "Novosibirsk"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4); // Не добавится
        navigator.addRoute(route5);

        System.out.println("Количество маршрутов: " + navigator.size()); // Ожидаемо: 4

        navigator.chooseRoute("1");
        navigator.chooseRoute("1");

        Route foundRoute = navigator.getRoute("1");
        System.out.println("Найденный маршрут: " + foundRoute.getId() + ", популярность: " + foundRoute.getPopularity()); // Ожидаемо: популярность 2

        System.out.println("\nМаршруты из Moscow в Ufa:");
        for (Route route : navigator.searchRoutes("Moscow", "Ufa")) {
            System.out.println("Маршрут ID: " + route.getId() + ", расстояние: " + route.getDistance() + ", популярность: " + route.getPopularity());
        }

        System.out.println("\nИзбранные маршруты через Kazan:");
        for (Route route : navigator.getFavoriteRoutes("Kazan")) {
            System.out.println("Маршрут ID: " + route.getId() + ", расстояние: " + route.getDistance() + ", популярность: " + route.getPopularity());
        }

        System.out.println("\nТоп-3 маршрута:");
        for (Route route : navigator.getTop3Routes()) {
            System.out.println("Маршрут ID: " + route.getId() + ", расстояние: " + route.getDistance() + ", популярность: " + route.getPopularity());
        }

        navigator.removeRoute("2");
        System.out.println("\nКоличество маршрутов после удаления: " + navigator.size()); // Ожидаемо: 3
    }
}
