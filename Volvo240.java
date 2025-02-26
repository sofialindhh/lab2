import java.awt.*;

public class Volvo240 extends CarModels{

    private final static double trimFactor = 1.25;
    
    public Volvo240(Color color, double positionX, double positionY){
        super(4,100,color,"Volvo240", positionX, positionY);
    }

    @Override
    protected double speedFactor(){
        return getEnginePower()*0.01*trimFactor;
    }
}
