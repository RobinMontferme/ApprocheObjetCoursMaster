package main.java.fr.ubordeaux.ao;

public class RGBColor{
    private PositiveInt red;
    private PositiveInt green;
    private PositiveInt blue;

    public RGBColor(PositiveInt red, PositiveInt green, PositiveInt blue){
        this.red = red;
        this.green = blue;
        this.blue = blue;
    }
    public int getRed(){
        return red.getValue();
    }
    public int getGreen(){
        return green.getValue();
    }
    public int getBlue(){
        return blue.getValue();
    }
}