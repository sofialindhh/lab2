import java.awt.*;
import java.awt.image.BufferedImage;

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

    public boolean getDraw(){
        return draw;
    }
}