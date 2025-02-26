public class RampC<T> {
    private T rampStatus;

    public RampC(T rampStatus) {
        this.rampStatus = rampStatus;}

    protected T getRampstatus() {
        return rampStatus;
    }

    protected void setRampStatus(T change) {
        rampStatus = change;
    }
}
