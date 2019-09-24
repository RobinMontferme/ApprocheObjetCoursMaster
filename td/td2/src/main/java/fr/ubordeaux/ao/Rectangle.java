package main.java.fr.ubordeaux.ao;

public class Rectangle extends Shape{
    private int width,height;
    public Rectangle(Point2D origin,int width, int height){
        super(origin);
        this.width=width;
        this.height=height;
    }
    @Override
    public String toSVGFile(){
        return "<rect x=\""+this.origin.getX()+"\" y=\""+this.origin.getY()+"\" width=\""+this.width+"\" height=\""+this.height+"\"/>";
     }
}