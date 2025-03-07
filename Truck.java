import java.awt.*;

public class Truck extends CarModels{



    public Truck(Color color, String modelName, double positionX, double positionY, String id) {
        super(2, 100, color, modelName, positionX, positionY, id);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower()*0.01;
    }
}
