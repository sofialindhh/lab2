import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;

public class GameModel {
    /*
    håller logik för hur spelet ska fungera, händelser vid kollisioner och en loop för själva programmet
    */

    private final int delay = 50;
    private javax.swing.Timer timer = new Timer(delay, new TimerListener());
    private ArrayList<ModelUpdateListener> listeners = new ArrayList<>();
    private ArrayList<CarModels> cars = new ArrayList<>();
    private Garage<Volvo240> volvoGarage = Factory.createVolvoGarage(10);

    private Map<Garage, Point> garages = new HashMap<>();



    public void notifyListeners() {
        for (ModelUpdateListener listener: listeners){
            listener.updateModel();
        }
    }

    public void notifyNewCar(String id, String carType, int posX, int posY){
        for (ModelUpdateListener listener: listeners) {
            listener.updateNewImage(id, carType, posX, posY);
        }
    }

    public void notifyRemoveCar(String id) {
        for (ModelUpdateListener listener:listeners) {
            listener.updateRemoveImage(id);
        }
    }
    public void notifyNewPosition(String id, int posX, int posY) {
        for (ModelUpdateListener listener: listeners){
            listener.updateNewPosition(id, posX, posY);
        }
    }

    public void startGameLoop() {
        timer.start();
    }

    public void addListener(ModelUpdateListener listener){
        listeners.add(listener);
    }

    private class TimerListener implements ActionListener {
        private ArrayList<CarModels> toBeRemoved = new ArrayList<>();

        public void actionPerformed(ActionEvent e) {
            for (CarModels car : cars) {

                //BufferedImage carImage = imageObjects.get(car.getId()).getImage();

                int frameWidth = 800;
                int frameHeight = 560;
                int carSizeX = 100;
                int carSizeY = 60;
                int garageSizeX = 100;
                int garageSizeY = 60;


                if (CollisionModel.touchesWall(car, frameWidth, frameHeight, 100, 60)) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();

                } else if (CollisionModel.touchesGarage(car, garages, 100, 60, 100, 60) && car instanceof Volvo240) {
                    volvoGarage.loadCar((Volvo240) car);
                    toBeRemoved.add(car);

                } else {
                    car.move();

                }
                int x = (int) Math.round(car.getPositionX());
                int y = (int) Math.round(car.getPositionY());


                notifyNewPosition(car.getId(), x, y);
                // repaint() calls the paintComponent method of the panel
                notifyListeners();
            }

            for (CarModels car : toBeRemoved) {
                cars.remove(car);
                notifyRemoveCar(car.getId());
            }
            toBeRemoved.clear();
        }
    }

    public void addCar(String id, String carType, int posX, int posY) {
        if (cars.size() < 10) {
            if (Objects.equals(carType, "Volvo")) {
                cars.add(Factory.createVolvo(Color.gray, posX, posY, id));
            } else if (Objects.equals(carType, "Saab")) {
                cars.add(Factory.createSaab(Color.gray, posX, posY, id));
            } else {
                cars.add(Factory.createScania(Color.red, posX, posY, id));
            }
        }
        notifyNewCar(id, carType, posX, posY);
    }

    public void removeCar(String id) {
        ArrayList<CarModels> toBeRemoved = new ArrayList<>();
        if (!cars.isEmpty()) {

            for (CarModels car: cars){
                if (Objects.equals(car.getId(), id)) {
                    toBeRemoved.add(car);
                }
            }

            for (CarModels car: toBeRemoved){
                cars.remove(car);
            }
            toBeRemoved.clear();
        }
        else {throw new RuntimeException("No cars left");
        }
        notifyRemoveCar(id);
    }


    public void addVolvoGarage(String id, int posX, int posY){
        garages.put(Factory.createVolvoGarage(10), new Point(posX, posY));
        notifyNewCar(id, "VolvoWorkshop", posX, posY);
    }


    public ArrayList<CarModels> getCars() {
        return cars;
    }
}
