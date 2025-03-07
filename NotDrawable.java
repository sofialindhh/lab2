public class NotDrawable implements Drawable{
    @Override
    public boolean getDrawableState() {
        return false;
    }

    @Override
    public Drawable updateDrawableState() {
        return new IsDrawable();
    }
}
