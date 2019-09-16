package fr.ubordeaux.ao;

public class PositiveInt{
    private int value;
    public PositiveInt(int value) throws IllegalArgumentException{
        if(value <=0)
        {
            throw new IllegalArgumentException("Exepected integer value got "+value);
        }
        this.value =value;
    }
    public int getValue(){return value;}
}