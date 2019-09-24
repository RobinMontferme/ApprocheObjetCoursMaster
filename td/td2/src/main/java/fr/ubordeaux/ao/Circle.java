package main.java.fr.ubordeaux.ao;

public class Circle extends Shape {
    private int radius;
    public Circle(Point2D origin, int radius) {
        super(origin);
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }
    @Override
    public String  toSVGFile(){
        return "<circle cx='"+this.origin.getX()+"' cy='"+this.origin.getY()+"' r='"+this.radius+"' />";
    }
}