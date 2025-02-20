import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<CarModels> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(Color.gray, 0, 0));

        cc.cars.add(new Saab95(Color.gray,0,100));

        cc.cars.add(new Scania(Color.red,0,200));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (CarModels car : cars) {
                if (touchesWall(car)) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();

                }
                else {
                    car.move();
                }
                int x = (int) Math.round(car.getPositionX());
                int y = (int) Math.round(car.getPositionY());
                frame.drawPanel.moveIt(car.getModelName(), x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }
    private boolean touchesWall(CarModels car) {
        double posX = car.getPositionX();
        double posY = car.getPositionY();

        BufferedImage carImage = frame.drawPanel.getCarImage(car.getModelName());
        int frameWidth = frame.drawPanel.getWidth();
        int frameHeight = frame.drawPanel.getHeight();
        int carSizeX = carImage.getWidth();
        int carSizeY = carImage.getHeight();

        boolean collideTop = posY - car.getCurrentSpeed()<0 && car.getDirection()==2;
        boolean collideBot = posY + carSizeY+ car.getCurrentSpeed()>frameHeight && car.getDirection()==0;
        boolean collideLeft = posX - car.getCurrentSpeed()<0 && car.getDirection()==3;
        boolean collideRight = posX + carSizeX+ car.getCurrentSpeed()>frameWidth && car.getDirection()==1;

        return collideLeft || collideTop || collideBot || collideRight;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (CarModels car : cars
                ) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
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
}
