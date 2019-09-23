package main.java.fr.ubordeaux.ao;
public class Point2D{
    private PositiveInt x,y;
    public Point2D(PositiveInt x, PositiveInt y)
    {
        this.x=x;
        this.y=y;
    }
    public int getX(){return x.getValue();}
    public int getY(){return y.getValue();}
}