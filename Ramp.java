public class Ramp<T> {
    private T tilted;

    public Ramp(T tilted){
        this.tilted=tilted;
    }

    public T angle(){
        return tilted;
    }

    public void setRampStatus(T tilt){
        tilted=tilt;
    }
}
