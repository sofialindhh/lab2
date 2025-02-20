import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;



public class DrawPanel extends JPanel {

    // Car class representing a car with an image and position
    private class Car {
        private String modelName;
        private BufferedImage image;
        private Point position;

        public Car(String modelName, BufferedImage image, int x, int y) {
            this.modelName = modelName;
            this.image = image;
            this.position = new Point(x, y);
        }

        public String getId() {
            return modelName;
        }

        public BufferedImage getImage() {
            return image;
        }

        public Point getPosition() {
            return position;
        }

        public void setPosition(int x, int y) {
            this.position = new Point(x, y);
        }
    }

    // Map to store all cars with their unique ID
    private Map<String, Car> cars = new HashMap<>();

    // Images for different car models
    private BufferedImage volvoImage;
    private BufferedImage saabImage;
    private BufferedImage scaniaImage;
    private BufferedImage volvoWorkshopImage;

    // Initializes the panel and loads car images
    public DrawPanel(int x, int y) {
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        try {
            // Load car images from resources
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Add cars with unique IDs
        cars.put("Volvo240", new Car("Volvo240", volvoImage, 0, 0));
        cars.put("Saab95", new Car("Saab95", saabImage, 0, 100));
        cars.put("Scania", new Car("Scania", scaniaImage, 0, 200));
    }

    // This method moves a car to a new position using its unique ID
    public void moveIt(String id, int x, int y) {
        Car car = cars.get(id);
        if (car != null) {
            car.setPosition(x, y);
        }
    }

    // This method is called every time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw each car by ID
        for (Car car : cars.values()) {
            g.drawImage(car.getImage(), car.getPosition().x, car.getPosition().y, null);
        }

        // Optionally, draw the Volvo workshop image at a fixed position
        g.drawImage(volvoWorkshopImage, 300, 300, null);
    }

    public BufferedImage getCarImage(String id) {
        Car car = cars.get(id);
        return (car != null) ? car.getImage() : null;
    }

    // Getter to retrieve all cars (optional)
    public Map<String, Car> getCars() {
        return cars;
    }
}
