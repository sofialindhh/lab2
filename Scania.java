import java.awt.*;

public class Scania extends Truck{
    private int lorryAngle;
    private int maxAngle;

    public Scania(Color color, double positionX, double positionY){
        super(color, "Scania", positionX, positionY);
        this.lorryAngle = 0;
        this.maxAngle = 70;
    }

    public int getMaxAngle() {
        return maxAngle;
    }

    public int getLorryAngle(){
        return lorryAngle;
    }

    private boolean isAllowedToTilt(int untiltWith) {
        return untiltWith >= 0 && !isDriving();
    }

    public void tiltRamp(int tiltWith){
        if (isAllowedToTilt(tiltWith)) {
            lorryAngle = Math.min((getLorryAngle() + tiltWith), getMaxAngle());
            super.tiltRamp();
        } else {
            throw new IllegalArgumentException("The vehicle is moving or amount is smaller than 0");
        }
    }

    public void untiltRamp(int untiltWith){
        if (isAllowedToTilt(untiltWith)) {
            lorryAngle = Math.max((getLorryAngle() - untiltWith), 0);
            super.untiltRamp();
        } else {
            throw new IllegalArgumentException("The vehicle is moving or amount is smaller than 0");
        }
    }
}
