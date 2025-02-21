import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;



public class DrawPanel extends JPanel {


    // Car class representing a object with an image and position
    public class ImageObjects {
        private String modelName;
        private BufferedImage image;
        private Point position;
        private Boolean draw = true;

        public ImageObjects(String modelName, BufferedImage image, int x, int y) {
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

        public void swithcDraw(){
            if (draw){
                draw =false;
            }
            else{
                draw =true;
            }
        }


    }

    private Map<String, ImageObjects> cars = new HashMap<>();

    private BufferedImage volvoImage;
    private BufferedImage saabImage;
    private BufferedImage scaniaImage;
    private BufferedImage volvoWorkshopImage;
    ImageObjects volvoWorkshop;



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
        cars.put("Volvo240", new ImageObjects("Volvo240", volvoImage, 0, 0));
        cars.put("Saab95", new ImageObjects("Saab95", saabImage, 0, 100));
        cars.put("Scania", new ImageObjects("Scania", scaniaImage, 0, 200));

        this.volvoWorkshop = new ImageObjects("VolvoWorkshop",volvoWorkshopImage,0,500);


    }


    public void moveIt(String id, int x, int y) {
        ImageObjects car = cars.get(id);
        if (car != null) {
            car.setPosition(x, y);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (ImageObjects car : cars.values()) {
            if (car.draw) {
                g.drawImage(car.getImage(), car.getPosition().x, car.getPosition().y, null);
            }
        }

        g.drawImage(volvoWorkshop.getImage(), volvoWorkshop.getPosition().x, volvoWorkshop.getPosition().y, null);
    }

    public ImageObjects getImageObject(String id) {
        return cars.get(id);
    }

}
