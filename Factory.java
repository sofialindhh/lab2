import java.awt.*;

public class Factory {


    public static Volvo240 createVolvo(Color color, int x, int y, String id) {
        return new Volvo240(color, x, y, id);
    }

    public static Saab95 createSaab(Color color, int x, int y, String id) {
        return new Saab95(color, x, y, id);
    }

    public static Scania createScania(Color color, int x, int y, String id) {
        return new Scania(color, x, y, id);
    }

    public static CarTransport createCarTransport(Color color, int x, int y, String id) {
        return new CarTransport(color, x, y, id);
    }

    public static Garage<Volvo240> createVolvoGarage(int maxCars) {
        return new Garage<>(maxCars);
    }

    public static Garage<Saab95> createSaabGarage(int maxCars) {
        return new Garage<>(maxCars);
    }

    public static Garage<Scania> createScaniaGarage(int maxCars) {
        return new Garage<>(maxCars);
    }

    public static Garage<CarTransport> createCarTransportGarage(int maxCars) {
        return new Garage<>(maxCars);
    }

    public static Garage <CarModels> createGenericGarage(int maxCars) {
        return new Garage<>(maxCars);
    }
}
