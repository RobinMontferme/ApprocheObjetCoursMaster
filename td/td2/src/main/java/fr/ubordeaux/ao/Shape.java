package main.java.fr.ubordeaux.ao;
public abstract class Shape{

   protected Point2D origin;

    public Shape(Point2D origin){
        this.origin=origin;
    }
    public Point2D getOrigin(){return this.origin;}
    public abstract String toSVGFile();
}