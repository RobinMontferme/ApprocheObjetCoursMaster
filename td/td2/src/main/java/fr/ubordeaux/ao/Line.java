package main.java.fr.ubordeaux.ao;

public class Line extends Shape{
   private Point2D destination;
   public Line(Point2D origin, Point2D destination){
       super(origin);
       this.destination=destination;

   }
   public Point2D getDestination(){return this.destination;}

   @Override
   public String toSVGFile(){
       return "<line x1=\""+this.origin.getX()+"\" y1=\""+this.origin.getY()+"\" x2=\""+this.destination.getX()+"\" y2=\""+this.destination.getY()+"\" style=\"stroke:rgb(0,0,0);stroke-width:1\"/>";
    }
}