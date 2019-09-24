package main.java.fr.ubordeaux.ao;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        SVG canvas = new SVG(500, 500);
        Line l = new Line(new Point2D(1, 1), new Point2D(15, 5));
        Circle c = new Circle (new Point2D(50,50),5);
        Ellipse e = new Ellipse(new Point2D(45,45),8,7);
        Rectangle r = new Rectangle(new Point2D(25,25),20,10);
        canvas.writeToFile("test.svg", l);
        canvas.writeToFile("testcircle.svg", c);
        canvas.writeToFile("testellipse.svg", e);
        canvas.writeToFile("testRectangle.svg",r);
        canvas.addShape(l);
        canvas.addShape(c);
        canvas.addShape(e);
        canvas.addShape(r);
        canvas.writeListToFile("testAll.svg");
    }}

       
