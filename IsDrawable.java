public class IsDrawable implements Drawable {
    @Override
    public boolean getDrawableState(){
        return true;
    }

    @Override
    public Drawable updateDrawableState() {
        return new NotDrawable();
    }
}
