import java.awt.*;
import java.util.Map;
import javax.swing.*;



public class DrawPanel extends JPanel implements ModelUpdateListener{
    /*
    implementerar ModelUpdateListener, så den uppdateras när ändring sker i model
    lägg till en metod här som kör repaint när det sker uppdateringar i Models
     */
    Map<String, ImageObjects> images;


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

    @Override
    public void updateModel(){
        repaint();
    }
}
