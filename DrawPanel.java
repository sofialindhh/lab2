import java.awt.*;
import java.util.Map;
import javax.swing.*;



public class DrawPanel extends JPanel {

    Map<String, ImageObjects> images;

    // Car class representing a object with an image and position

    // Initializes the panel and loads car images
    public DrawPanel(int x, int y, Map<String, ImageObjects> images) {
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.images = images;
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

    public ImageObjects getImageObject(String id) {
        return images.get(id);
    }

}
