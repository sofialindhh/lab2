import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;



public class DrawPanel extends JPanel {


    // Car class representing a object with an image and position

    private Map<String, ImageObjects> imgObjects = new HashMap<>();

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
        imgObjects.put("Volvo240", new ImageObjects("Volvo240", volvoImage, 0, 0));
        imgObjects.put("Saab95", new ImageObjects("Saab95", saabImage, 0, 100));
        imgObjects.put("Scania", new ImageObjects("Scania", scaniaImage, 0, 200));

        this.volvoWorkshop = new ImageObjects("VolvoWorkshop",volvoWorkshopImage,0,500);


    }


    public void moveIt(String id, int x, int y) {
        ImageObjects car = imgObjects.get(id);
        if (car != null) {
            car.setPosition(x, y);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (ImageObjects car : imgObjects.values()) {
            if (car.ifDraw()) {
                g.drawImage(car.getImage(), car.getPosition().x, car.getPosition().y, null);
            }
        }

        g.drawImage(volvoWorkshop.getImage(), volvoWorkshop.getPosition().x, volvoWorkshop.getPosition().y, null);
    }

    public ImageObjects getImageObject(String id) {
        return imgObjects.get(id);
    }

}
