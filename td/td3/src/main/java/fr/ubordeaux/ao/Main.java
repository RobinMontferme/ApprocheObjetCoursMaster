package main.java.fr.ubordeaux.ao;

public class Main{
    public static void main(String[] args) {
        Point2D p = new Point2D(new PositiveInt(0), new PositiveInt(0));
        System.out.println(p.getX()+" "+p.getY());
        RGBColor color = new RGBColor(new PositiveInt(255), new PositiveInt(0), new PositiveInt(45));
        ColoredPoint2D colored = new ColoredPoint2D(new PositiveInt(4), new PositiveInt(45));
        colored.setRGBColor(color);
        System.out.println(colored.getX()+" "+colored.getY() + colored.);
        
        SortBenchmark s = new SortBenchmark();
        InsertSort ins = new InsertSort();
        s.addAlgorithm(ins);
        s.bench();
        
    }
}