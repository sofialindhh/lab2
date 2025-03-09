import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;



public class DrawPanel extends JPanel implements ModelUpdateListener{
    /*
    implementerar ModelUpdateListener, så den uppdateras när ändring sker i model
    lägg till en metod här som kör repaint när det sker uppdateringar i Models
     */
    Map<String, ImageObjects> images;

    private static BufferedImage volvoImage;
    private static BufferedImage saabImage;
    private static BufferedImage scaniaImage;
    private static BufferedImage volvoWorkshopImage;


    // Initializes the panel and loads car images
    public DrawPanel(int x, int y) {
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.images = new HashMap<>();

        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (ImageObjects car : images.values()) {
            if (car.getDrawableState()) {
                g.drawImage(car.getImage(), car.getPosition().x, car.getPosition().y, null);
            }
        }
    }

    @Override
    public void updateModel(){

        repaint();
    }

    @Override
    public void updateNewImage(String id, String carType, int posX, int posY){
        if (Objects.equals(carType, "Volvo")){
            images.put(id, new ImageObjects("Volvo240", volvoImage, posX, posY, CarApplication.ObjectType.vehicle));
        }
        else if (Objects.equals(carType, "Saab")) {
            images.put(id, new ImageObjects("Saab95", saabImage, posX, posY, CarApplication.ObjectType.vehicle));
        } else if (Objects.equals(carType, "Scania")) {
            images.put(id, new ImageObjects("Scania", scaniaImage, posX, posY, CarApplication.ObjectType.vehicle));
        } else if (Objects.equals(carType,"VolvoWorkshop")) {
            images.put(id, new ImageObjects(id, volvoWorkshopImage, posX, posY, CarApplication.ObjectType.garage));
        }
    }

    @Override
    public void updateRemoveImage(String id) {
        images.get(id).updateDrawableState();
        images.remove(id);
    }

    @Override
    public void updateNewPosition(String id, int posX, int posY){
        images.get(id).setPosition(posX, posY);
    }
}
