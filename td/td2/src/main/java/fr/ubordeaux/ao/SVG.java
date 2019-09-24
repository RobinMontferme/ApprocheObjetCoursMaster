package main.java.fr.ubordeaux.ao;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import main.java.fr.ubordeaux.ao.Shape;


public class SVG {
    private int width;
    private int heigth;
    private List<Shape> shapeList;

    public SVG(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
        this.shapeList = new ArrayList<Shape>();
    }
    public void addShape(Shape shape)
    {
        shapeList.add(shape);
    }
    public void writeToFile(String fileName,Shape shape) throws IOException
    {
        FileWriter out = new FileWriter(fileName);
        out.write("<?xml version='1.0' encoding='utf-8'?>\n");
        out.write("<svg xmlns='http://www.w3.org/2000/svg' version='1.1' width='100' height='100'>");
        out.write(shape.toSVGFile());
        out.write("</svg>");
        out.close();
    }

    public void writeListToFile(String fileName) throws IOException{
        FileWriter out = new FileWriter(fileName);
        out.write("<?xml version='1.0' encoding='utf-8'?>\n");
        out.write("<svg xmlns='http://www.w3.org/2000/svg' version='1.1' width='100' height='100'>");
        for(Shape shape : shapeList)
        {
            out.write(shape.toSVGFile());
        }
        out.write("</svg>");
        out.close();
    }
    
}