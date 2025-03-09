import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

public class CollisionModel {

    //

    public static boolean touchesWall(CarModels car, int sizePanelW, int sizePanelH, int carSizeW, int carSizeH ) {
        double posX = car.getPositionX();
        double posY = car.getPositionY();

        //int carSizeX = sizeImageW;
        //int carSizeY = sizeImageH;

        boolean collideTop = posY - car.getCurrentSpeed() < 0 && car.getDirection() == 2;
        boolean collideBot = posY + car.getCurrentSpeed() > sizePanelH && car.getDirection() == 0;
        boolean collideLeft = posX - car.getCurrentSpeed() < 0 && car.getDirection() == 3;
        boolean collideRight = posX + car.getCurrentSpeed() > sizePanelW && car.getDirection() == 1;

        return collideLeft || collideTop || collideBot || collideRight;
    }

    public static boolean touchesGarage(CarModels car, Map<Garage, Point> garages, int garageSizeW, int garageSizeH, int carSizeX, int carSizeY) {
        for (Garage garage : garages.keySet()) {

            //if (image.getObjectType() == CarApplication.ObjectType.garage) {
                Point workshopPos = garages.get(garage);
                //BufferedImage workshopImage = image.getImage();

            double posX = car.getPositionX();
            double posY = car.getPositionY();
            double posGarageY = workshopPos.y;
            double posGarageX = workshopPos.x;


            boolean overlapX = (posX < posGarageX + garageSizeW) && (posX + carSizeX > posGarageX);
            boolean overlapY = (posY < posGarageY + garageSizeH) && (posY + carSizeY > posGarageY);


            if (overlapX && overlapY){
                return true;
                }
            }
        return false;
    }
}
