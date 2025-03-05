import java.awt.*;

public class Scania extends Truck implements RampI<Integer>{
    private int maxAngle;
    public Ramp<Integer> scaniaRamp = new Ramp<>(0);

    public Scania(Color color, double positionX, double positionY){
        super(color, "Scania", positionX, positionY);
        this.maxAngle = 70;
    }

    public int getMaxAngle() {
        return maxAngle;
    }


    private boolean isAllowedToTilt(int untiltWith) {
        return untiltWith >= 0 && !isDriving();
    }

    @Override
    public void tiltRamp(){
        if (isAllowedToTilt(10)) {
            int newAngle = Math.min((scaniaRamp.angle() + 10), getMaxAngle());
            scaniaRamp.setRampStatus(newAngle);
        } else {
            throw new IllegalArgumentException("The vehicle is moving or amount is smaller than 0");
        }
    }

    @Override
    public void untiltRamp(){
        if (isAllowedToTilt(10)) {
            int newAngle = Math.max((scaniaRamp.angle() - 10), 0);
            scaniaRamp.setRampStatus(newAngle);
        } else {
            throw new IllegalArgumentException("The vehicle is moving or amount is smaller than 0");
        }
    }

    @Override
    public boolean isTilted(){
        if (scaniaRamp.angle()>0){
            return true;
        }
        else{
            return false;
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
