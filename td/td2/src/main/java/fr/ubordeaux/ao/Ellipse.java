package main.java.fr.ubordeaux.ao;

public class Ellipse extends Circle {
    protected int radiusY;
     public Ellipse(Point2D origin, int radiusX,int radiusY){
         super(origin,radiusX);
         this.radiusY=radiusY;
     }
     public int getRadiusY(){return this.radiusY;}
    @Override
    public String  toSVGFile(){
        return "<ellipse cx=\""+this.origin.getX()+"\" cy=\""+this.origin.getY()+"\" rx=\""+this.getRadius()+"\" ry=\""+this.getRadiusY()+"\"/>";
    }
    
}