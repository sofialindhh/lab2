import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class CollisionModel {

    //

    public static boolean touchesWall(CarModels car, int sizePanelW, int sizePanelH, int sizeImageW, int sizeImageH) {
        double posX = car.getPositionX();
        double posY = car.getPositionY();

        int frameWidth = sizePanelW;
        int frameHeight = sizePanelH;
        int carSizeX = sizeImageW;
        int carSizeY = sizeImageH;

        boolean collideTop = posY - car.getCurrentSpeed() < 0 && car.getDirection() == 2;
        boolean collideBot = posY + carSizeY + car.getCurrentSpeed() > frameHeight && car.getDirection() == 0;
        boolean collideLeft = posX - car.getCurrentSpeed() < 0 && car.getDirection() == 3;
        boolean collideRight = posX + carSizeX + car.getCurrentSpeed() > frameWidth && car.getDirection() == 1;

        return collideLeft || collideTop || collideBot || collideRight;
    }

    public static boolean touchesGarage(CarModels car, Map<String, ImageObjects> images) {
        for (ImageObjects image : images.values()) {
            if (image.getObjectType() == CarApplication.ObjectType.garage) {
                Point workshopPos = image.getPosition();
                BufferedImage workshopImage = image.getImage();

                double posX = car.getPositionX();
                double posY = car.getPositionY();
                double posGarageY = workshopPos.y;
                double posGarageX = workshopPos.x;

                BufferedImage carImage = images.get(car.getId()).getImage();

                int carSizeX = carImage.getWidth();
                int carSizeY = carImage.getHeight();
                int garageSizeW = workshopImage.getWidth();
                int garageSizeH = workshopImage.getHeight();

                boolean overlapX = (posX < posGarageX + garageSizeW) && (posX + carSizeX > posGarageX);
                boolean overlapY = (posY < posGarageY + garageSizeH) && (posY + carSizeY > posGarageY);


                return overlapX && overlapY;
            }
        }
        return false;
    }
}
