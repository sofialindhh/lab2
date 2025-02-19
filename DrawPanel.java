import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

/*public class DrawPanel extends JPanel{

    private class Car {
        BufferedImage image;
        Point position;

        public Car (BufferedImage image, int x, int y) {
            this.image = image;
            this.position = new Point(x, y);
        }
    }
    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;

    // To keep track of a single car's position
    Point volvoPoint = new Point();
    Point saabPoint = new Point(0,100);
    Point scaniaPoint = new Point(0, 200);

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    // TODO: Make this general for all cars
    void moveit(int x, int y){
        volvoPoint.x = x;
        volvoPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        g.drawImage(saabImage, saabPoint.x, saabPoint.y, null);
        g.drawImage(scaniaImage, scaniaPoint.x, scaniaPoint.y, null);
    }
}*/


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
        cars.put("Scana", new Car("Scania", scaniaImage, 0, 200));
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
