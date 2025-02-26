import java.awt.*;

public class Truck extends CarModels{
    private boolean tilted;

    public Truck(Color color, String modelName, double positionX, double positionY) {
        super(2, 100, color, modelName, positionX, positionY);
        this.tilted = false;
    }

    public boolean isTilted(){
        return tilted;
    }

    public void tiltRamp() {
        if (!isDriving()) {
            tilted = true;
        }
        else {throw new IllegalStateException("Truck is moving");}
    }

    public void untiltRamp() {
        if (!isDriving()) {
            tilted = false;
        }
        else {throw new IllegalStateException("Truck is moving");}
    }


    @Override
    public void gas(double amount) {
        if (! isTilted()){
            super.gas(amount);
        }
        else {throw new IllegalArgumentException("The lorry is tilted");}
    }

    @Override
    protected double speedFactor() {
        return getEnginePower()*0.01;
    }
}
