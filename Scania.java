import java.awt.*;

public class Scania extends Truck implements RampI{
    private int maxAngle;
    private RampC<Integer> scaniaRamp;

    public Scania(Color color, double positionX, double positionY){
        super(color, "Scania", positionX, positionY);
        this.maxAngle = 70;
        scaniaRamp = new RampC<>(0);

    }

    @Override
    public boolean isTilted(){
        return scaniaRamp.getRampstatus() >= 0;
    }

    public int getMaxAngle() {
        return maxAngle;
    }

    public int getLorryAngle(){
        return scaniaRamp.getRampstatus();
    }



    /*private boolean isAllowedToTilt(int untiltWith) {
        return untiltWith >= 0 && !isDriving();
    }
     */

    @Override
    public void tiltRamp(){
        if (!isDriving()) {
            scaniaRamp.setRampStatus(Math.min((scaniaRamp.getRampstatus() + 10), getMaxAngle()));
            //super.tiltRamp();
        } else {
            throw new IllegalArgumentException("The vehicle is moving or amount is smaller than 0");
        }
    }

    @Override
    public void untiltRamp(){
        if (!isDriving()) {
            scaniaRamp.setRampStatus(Math.max((scaniaRamp.getRampstatus() - 10), 0));
            //super.untiltRamp();
        } else {
            throw new IllegalArgumentException("The vehicle is moving or amount is smaller than 0");
        }
    }

    @Override
    public void gas(double amount) {
        if (! isTilted()){
            super.gas(amount);
        }
        else {throw new IllegalArgumentException("The lorry is tilted");}
    }
}
