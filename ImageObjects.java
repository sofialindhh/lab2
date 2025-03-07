import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageObjects{
    private String modelName;
    private BufferedImage image;
    private Point position;
    private Drawable drawState;
    private final CarApplication.ObjectType objectType;

    public ImageObjects(String modelName, BufferedImage image, int x, int y, CarApplication.ObjectType objectType) {
        this.modelName = modelName;
        this.image = image;
        this.position = new Point(x, y);
        this.objectType = objectType;
        this.drawState = new IsDrawable();
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

    public CarApplication.ObjectType getObjectType(){
        return objectType;
    }


    public void updateDrawableState(){
        drawState = drawState.updateDrawableState();
    }

    public boolean getDrawableState(){
        return drawState.getDrawableState();
    }
}