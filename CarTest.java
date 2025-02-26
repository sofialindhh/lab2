import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testSaab95Turbo() {
        Saab95 saab = new Saab95(Color.red, 0, 0);
        assertEquals(0, saab.getCurrentSpeed());
        saab.setTurboOn();
        assertEquals(1.625, saab.speedFactor());
        saab.setTurboOff();
        assertEquals(1.25, saab.speedFactor());
    }

    @Test
    public void testVolvo240SpeedFactor() {
        Volvo240 volvo = new Volvo240(Color.black, 0, 0);
        assertEquals(1.25, volvo.speedFactor());
    }

    @Test
    public void testGasAndBrake() {
        Volvo240 volvo = new Volvo240(Color.blue, 0, 0);
        volvo.startEngine();
        double initialSpeed = volvo.getCurrentSpeed();
        volvo.gas(0.5);
        assertTrue(volvo.getCurrentSpeed() > initialSpeed);

        double speedBeforeBrake = volvo.getCurrentSpeed();
        volvo.brake(0.5);
        assertTrue(volvo.getCurrentSpeed() < speedBeforeBrake);
    }

    @Test
    public void testGasAndBrakeLimits() {
        Volvo240 volvo = new Volvo240(Color.green, 0, 0);
        volvo.startEngine();

        // Test gas beyond limits
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(-0.1));
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(1.1));

        // Test brake beyond limits
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(-0.1));
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(1.1));
    }

    @Test
    public void testMoveAndTurn() {
        Saab95 saab = new Saab95(Color.red, 0, 0);
        saab.startEngine();
        saab.move();
        assertEquals(0.1, saab.getPositionY());
        assertEquals(0, saab.getPositionX());

        saab.turnRight();
        saab.move();
        assertEquals(0.1, saab.getPositionY());
        assertEquals(0.1, saab.getPositionX());

        saab.turnLeft();
        saab.turnLeft();
        saab.move();
        assertEquals(0.1, saab.getPositionY());
        assertEquals(0.0, saab.getPositionX());
    }

    @Test
    public void testLorryAngle() {
        Scania scania = new Scania(Color.blue, 0, 0);
        assertEquals(0, scania.getLorryAngle());
        double intialAngle = scania.getLorryAngle();
        scania.tiltRamp(50);
        double raisedAngle = scania.getLorryAngle();
        assertTrue(intialAngle < raisedAngle);

        scania.untiltRamp(25);
        double loweredAngle = scania.getLorryAngle();
        assertTrue(loweredAngle < raisedAngle);

        scania.tiltRamp(100);
        assertTrue(scania.getLorryAngle() <= 70);
        scania.untiltRamp(110);
        assertTrue(scania.getLorryAngle() >= 0);
    }

    @Test
    public void changeAngleSpeed() {
        Scania scania = new Scania(Color.magenta, 0, 0);
        scania.startEngine();
        assertThrows(IllegalArgumentException.class, () -> scania.tiltRamp(10));
        assertThrows(IllegalArgumentException.class, () -> scania.untiltRamp(10));

        scania.brake(1);
        scania.tiltRamp(25);
        assertThrows(IllegalArgumentException.class, () -> scania.gas(0.5));
    }

    @Test
    public void lorryAngleCartransport(){
        CarTransport carTransport = new CarTransport(Color.blue, 0, 0);
        assertFalse(carTransport.isTilted());
        carTransport.tiltRamp();
        assertTrue(carTransport.isTilted());

        carTransport.untiltRamp();
        assertFalse(carTransport.isTilted());
    }

    @Test
    public void changeAngleSpeedCartransport() {
        CarTransport carTransport = new CarTransport(Color.cyan, 0, 0);
        carTransport.startEngine();
        assertThrows(IllegalStateException.class, carTransport::tiltRamp);
        assertThrows(IllegalStateException.class, carTransport::untiltRamp);

        carTransport.stopEngine();
        carTransport.tiltRamp();
        assertThrows(IllegalArgumentException.class, () -> carTransport.gas(0.5));
    }

    @Test
    public void testLoadCars(){
        CarTransport carTransport = new CarTransport(Color.black, 0, 0);
        Volvo240 volvo240 = new Volvo240(Color.gray, 0, 0);
        Saab95 saab95 = new Saab95(Color.red, 0, 0);
        CarTransport carTransport2 = new CarTransport(Color.blue, 0, 0);
        assertThrows(IllegalArgumentException.class, ()-> carTransport.loadCar(carTransport2));
        assertThrows(IllegalArgumentException.class, () -> carTransport.loadCar(volvo240));
        carTransport.tiltRamp();
        assertDoesNotThrow(() -> carTransport.loadCar(volvo240));

        saab95.setCurrentPositionX(5);
        saab95.setCurrentPositionY(3);
        assertThrows(IllegalArgumentException.class, () -> carTransport.loadCar(saab95));
    }

    @Test
    public void testUnloadCarsAndPosition() {
        CarTransport carTransport = new CarTransport(Color.black, 0, 0);
        Volvo240 volvo240 = new Volvo240(Color.gray, 0, 0);
        Saab95 saab95 = new Saab95(Color.red, 0, 0);
        carTransport.tiltRamp();
        volvo240.startEngine();
        volvo240.move();
        assertNotEquals(carTransport.getPositionY(), volvo240.getPositionY());
        carTransport.loadCar(volvo240);
        assertEquals(carTransport.getPositionY(), volvo240.getPositionY());
        carTransport.loadCar(saab95);

        carTransport.untiltRamp();
        carTransport.startEngine();
        carTransport.move();
        carTransport.turnRight();
        carTransport.move();
        assertEquals(carTransport.getPositionY(), volvo240.getPositionY());
        assertEquals(carTransport.getPositionX(), saab95.getPositionX());

        carTransport.stopEngine();
        carTransport.tiltRamp();
        assertEquals(saab95, carTransport.unloadCar());
        switch (carTransport.getDirection()) {
            case 0 -> assertEquals(carTransport.getPositionY() -1,saab95.getPositionY());
            case 1 -> assertEquals(carTransport.getPositionX() -1,saab95.getPositionX());
            case 2 -> assertEquals(carTransport.getPositionY() +1,saab95.getPositionY());
            default -> assertEquals(carTransport.getPositionX() +1,saab95.getPositionX());
        }
    }

    @Test
    public void testGarage() {
        Garage<Saab95> garage = new Garage<>(2);
        Saab95 saab = new Saab95(Color.red, 0, 0);
        Saab95 saab2 = new Saab95(Color.blue, 0, 0);
        Saab95 saab3 = new Saab95(Color.gray, 0, 0);
        Volvo240 volvo = new Volvo240(Color.CYAN, 0, 0);
        garage.loadCar(saab);
        garage.loadCar(saab2);
        assertThrows(IllegalStateException.class, () -> garage.loadCar(saab3));
        assertEquals(2, garage.getSize());
        garage.unloadCar(saab2);
        assertEquals(1, garage.getSize());
        assertThrows(IllegalStateException.class, () -> garage.unloadCar(saab3));

        Garage<CarModels> garageAll = new Garage<>(3);
        CarTransport carTransport = new CarTransport(Color.gray, 0, 0);
        Scania scania = new Scania(Color.GREEN, 0, 0);
        garageAll.loadCar(scania);
        garageAll.loadCar(carTransport);
        garageAll.loadCar(volvo);
        assertEquals(3, garageAll.getSize());
    }
}

