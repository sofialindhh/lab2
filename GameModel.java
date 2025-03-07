import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class GameModel {
    /*
    skapa dessa


    Håll metod som uppdaterar ModelUpdateListener när något uppdateras
    Håll TimerListener
    Håll metoder för att addCar, removeCar
    istället för repaint i TimerListener, kör notifyListeners som då kallar på repaint via drawPanel
     */

    private final int delay = 50;
    private javax.swing.Timer timer = new Timer(delay, new TimerListener());
    private ArrayList<ModelUpdateListener> listeners = new ArrayList<>();
    private ArrayList<CarModels> cars = new ArrayList<>();
    private Map<String, ImageObjects> imageObjects = new HashMap<>();
    private Map<String, BufferedImage> images = new HashMap<>();
    private Garage<Volvo240> volvoGarage = Factory.createVolvoGarage(10);

    private static BufferedImage volvoImage;
    private static BufferedImage saabImage;
    private static BufferedImage scaniaImage;
    private static BufferedImage volvoWorkshopImage;

    public GameModel(Map<String, BufferedImage> images){
        this.images = images;
    }


    public void notifyListeners() {
        for (ModelUpdateListener listener: listeners){
            listener.updateModel();
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

                BufferedImage carImage = imageObjects.get(car.getId()).getImage();

                int frameWidth = 800;
                int frameHeight = 560;
                int carSizeX = carImage.getWidth();
                int carSizeY = carImage.getHeight();

                if (CollisionModel.touchesWall(car, frameWidth, frameHeight, carSizeX, carSizeY)) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();

                } else if (CollisionModel.touchesGarage(car, imageObjects) && car instanceof Volvo240) {
                    volvoGarage.loadCar((Volvo240) car);
                    toBeRemoved.add(car);
                } else {
                    car.move();
                }
                int x = (int) Math.round(car.getPositionX());
                int y = (int) Math.round(car.getPositionY());

                imageObjects.get(car.getId()).setPosition(x, y);
                // repaint() calls the paintComponent method of the panel
                notifyListeners();
            }
            for (CarModels car : toBeRemoved) {
                cars.remove(car);
                imageObjects.get(car.getId()).updateDrawableState();
            }
            toBeRemoved.clear();
        }
    }

    public void addCar(String id, String carType, int posX, int posY) {
        BufferedImage carImage = images.get(carType);

        if (cars.size() < 10) {
            if (Objects.equals(carType, "Volvo")) {
                cars.add(Factory.createVolvo(Color.gray, posX, posY, id));
                imageObjects.put(id, new ImageObjects("Volvo240", carImage, posX, posY, CarApplication.ObjectType.vehicle));
            } else if (Objects.equals(carType, "Saab")) {
                cars.add(Factory.createSaab(Color.gray, posX, posY, id));
                imageObjects.put(id, new ImageObjects("Saab95", carImage, posX, posY, CarApplication.ObjectType.vehicle));
            } else {
                cars.add(Factory.createScania(Color.red, posX, posY, id));
                imageObjects.put(id, new ImageObjects("Scania", carImage, posX, posY, CarApplication.ObjectType.vehicle));
            }
        }
        notifyListeners();
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
                imageObjects.remove(car.getId());
            }
            toBeRemoved.clear();
        }
        else {throw new RuntimeException("No cars left");
        }
        notifyListeners();
    }
    public void addVolvoGarage(String id, int posX, int posY){
        BufferedImage carImage = images.get(id);

        Factory.createVolvoGarage(10);
        imageObjects.put(id, new ImageObjects(id, carImage, posX, posY, CarApplication.ObjectType.garage));
        notifyListeners();
    }

    public Map<String, ImageObjects> getImageObjects(){
        return imageObjects;
    }

    public ArrayList<CarModels> getCars() {
        return cars;
    }
}
