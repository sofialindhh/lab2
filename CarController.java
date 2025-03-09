
/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
*/


import java.util.ArrayList;

public class CarController{

    ArrayList<CarModels> cars;
    GameModel gameModel;

    protected CarController(ArrayList<CarModels> cars, GameModel gameModel){
        this.cars=cars;
        this.gameModel = gameModel;
    }

    void gas(double gas){
        for (CarModels car : cars
        ) {
            car.gas(gas);
        }
    }

    void brake(double brake){
        for (CarModels car : cars
        ) {
            car.brake(brake);
        }
    }

    void turboOn(){
        for (CarModels car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff(){
        for (CarModels car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void startEngine(){
        for (CarModels car : cars){
            car.startEngine();
        }
    }

    void stopEngine(){
        for (CarModels car : cars){
            car.stopEngine();
        }
    }

    void tiltRamp(){
        for (CarModels car:cars){
            if (car instanceof Scania){
                ((Scania) car).tiltRamp();
            }
        }
    }

    void untiltRamp(){
        for (CarModels car:cars){
            if (car instanceof Scania){
                ((Scania) car).tiltRamp();
            }
        }
    }

    void addCar(String id, String type){
        gameModel.addCar(id,type,0,0);
    }

    void removeCar(String id){
        gameModel.removeCar(id);
    }

}
