import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CarApplication {


    // The delay (ms) corresponds to 20 updates a sec (hz)
    private GameModel gameModel;

    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.


    // The frame that represents this instance View of the MVC pattern
    CarView frame;


    private static final int X = 800;
    private static final int Y = 800;

    CarController controller;
    // A list of cars, modify if needed
    static ArrayList<CarModels> cars = new ArrayList<>();


    private static Map<String, BufferedImage> images = new HashMap<>();


    private static BufferedImage volvoImage;
    private static BufferedImage saabImage;
    private static BufferedImage scaniaImage;
    private static BufferedImage volvoWorkshopImage;

    public enum ObjectType {vehicle, garage}

    private ObjectType objectType;
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarApplication ca = new CarApplication();
        ca.start();
    }

    public void start() {
        GameModel gameModel = new GameModel(images);

        try {
            // Load car images from resources
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        images.put("Volvo", volvoImage);
        images.put("Saab", saabImage);
        images.put("Scania", scaniaImage);
        images.put("VolvoWorkshop", volvoWorkshopImage);


        Map<String, ImageObjects> images = gameModel.getImageObjects();
        DrawPanel drawPanel = new DrawPanel(X, Y - 240, images);
        frame = new CarView("CarSim 1.0", drawPanel);


        gameModel.addListener(drawPanel);
        CarController controller = new CarController(frame, gameModel.getCars(), gameModel);

        gameModel.addCar("ABC123", "Volvo", 0, 0);
        gameModel.addCar("CBA321", "Saab", 0, 100);
        gameModel.addCar("CBA123", "Scania", 0, 200);

        gameModel.addVolvoGarage("VolvoWorkshop", 0, 500);

        // Start the timer
        gameModel.startGameLoop();
    }
}