package main.java.fr.ubordeaux.ao;

public class ColoredPoint2D extends Point2D{

    private RGBColor color;
    public ColoredPoint2D(PositiveInt x, PositiveInt y)
    {
        super(x,y);
    }
    public void setRGBColor(RGBColor color)
    {
        this.color = color;
    }
}