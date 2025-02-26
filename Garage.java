import java.util.ArrayList;
import java.util.List;

public class Garage <C extends CarModels>{
    private List<C> cars;
    private int maxCars;

    public Garage(int maxCars){
        this.maxCars = maxCars;
        this.cars = new ArrayList<>(maxCars);
    }

    public int loadCar(C car){
        if (cars.size() < maxCars) {
            cars.add(car);
            return cars.indexOf(car);
        }
        else {
            throw new IllegalStateException("Garage is full.");
        }
    }

    public C unloadCar(C car){ //hitta något annat sätt att identifiera specifika bilar, t.ex. ett nyckelelement?
        if (cars.contains(car)) {
            cars.remove(car);
            System.out.println("Unloading " + car.getModelName() + " from garage");
            return car;
        }
        else {throw new IllegalStateException(car + " is not in garage.");}
    }

    public int getSize() {
        return cars.size();
    }

    public boolean carInGarage(CarModels car){
        return cars.contains(car);
    }
}
