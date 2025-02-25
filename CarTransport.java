import org.junit.Test;

import java.awt.*;
import java.util.Stack;

public class CarTransport extends Truck implements RampI{
    private Stack<CarModels> loadedCars;
    private RampC<Boolean> transportRamp;

    public CarTransport(Color color, double currentPositionX, double currentPositionY){
        super(color, "CarTransport", currentPositionX, currentPositionY);
        this.loadedCars = new Stack<>();
        transportRamp = new RampC<>(false);
    }

    // tilted - true or false

    private boolean isInRange(CarModels car) {
        double xDistance = getPositionX() - car.getPositionX();
        double yDistance = getPositionY() - car.getPositionY();

        boolean inRange = Math.abs(xDistance) <= 1 && Math.abs(yDistance) <= 1;
        return inRange;
    }

    private boolean maxSizeReached() {
        return loadedCars.size() >= 8;
    }


    public boolean isLoadable(CarModels car) {
        return isTilted() && isInRange(car) && !maxSizeReached()
                && !(car instanceof CarTransport);
    }

    public void loadCar(CarModels car) {

        if (isLoadable(car)) {
            loadedCars.push(car);
            car.setCurrentPositionX(getPositionX());
            car.setCurrentPositionY(getPositionY());
        }
        else {throw new IllegalArgumentException("Not loadable!");}
    }


    public CarModels unloadCar() {
        if (isTilted() && !loadedCars.isEmpty()){
            CarModels car = loadedCars.pop();
            switch (getDirection()) {
                case 0 -> car.setCurrentPositionY((getPositionY() - 1));
                case 1 -> car.setCurrentPositionX((getPositionX() - 1));
                case 2 -> car.setCurrentPositionY((getPositionY() + 1));
                default -> car.setCurrentPositionX((getPositionX() + 1));
            }
            return car;
        }
        else {throw new IllegalArgumentException("Angle not ok or no cars loaded!");}
    }

    @Override
    public void move(){
        super.move();
        for (CarModels car: loadedCars) {
           car.setCurrentPositionX(getPositionX());
           car.setCurrentPositionY(getPositionY());
        }
    }

    @Override
    public boolean isTilted(){
        return transportRamp.getRampstatus();
    }

    @Override
    public void tiltRamp() {
        if (!isDriving()) {
            transportRamp.setRampStatus(true);
        }
        else {throw new IllegalStateException("Truck is moving");}
    }

    public void untiltRamp() {
        if (!isDriving()) {
            transportRamp.setRampStatus(false);
        }
        else {throw new IllegalStateException("Truck is moving");}
    }



    @Override
    public void gas(double amount) {
        if (!isTilted()){
            super.gas(amount);
        }
        else {throw new IllegalArgumentException("The lorry is tilted");}
    }

}


