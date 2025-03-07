import java.awt.*;

public class Saab95 extends CarModels{

    private boolean turboOn;
    
    public Saab95(Color color, double positionX, double positionY, String id){
        super(2,125,color,"Saab95", positionX, positionY, id);
        turboOn=false;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
