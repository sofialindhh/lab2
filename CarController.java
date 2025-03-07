
/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarController{

    CarView cv;
    ArrayList<CarModels> cars;
    GameModel gameModel;

    protected CarController(CarView cv,ArrayList<CarModels> cars, GameModel gameModel){
        this.cv=cv;
        this.cars=cars;
        this.gameModel = gameModel;
        innitButtons();
    }


    private void innitButtons(){
        cv.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double gas = ((double) cv.gasAmount) / 100;
                for (CarModels car : cars
                ) {
                    car.gas(gas);
                }
            }
        });

        cv.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double brake = ((double) cv.gasAmount) / 100;
                for (CarModels car : cars
                ) {
                    car.brake(brake);
                }
            }
        });

        cv.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CarModels car : cars){
                    if (car instanceof Saab95){
                        ((Saab95) car).setTurboOn();
                    }
                }
            }
        });

        cv.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CarModels car : cars){
                    if (car instanceof Saab95){
                        ((Saab95) car).setTurboOff();
                    }
                }
            }
        });

        cv.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CarModels car : cars){
                    car.startEngine();
                }
            }
        });

        cv.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CarModels car : cars){
                    car.stopEngine();
                }
            }
        });

        cv.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CarModels car:cars){
                    if (car instanceof Scania){
                        ((Scania) car).tiltRamp();
                    }
                }
            }
        });

        cv.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CarModels car:cars){
                    if (car instanceof Scania){
                        ((Scania) car).untiltRamp();
                    }
                }
            }
        });

        cv.addCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    gameModel.addCar(cv.idCar.getText(), cv.carType.getSelectedItem().toString(),0, 0);
            }
        });
        cv.removeCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.removeCar(cv.idCar.getText());
            }
        });
    }
}
