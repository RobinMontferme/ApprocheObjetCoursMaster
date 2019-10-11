package fr.ubordeaux.ao;

/**
 * DescriptionString is a ValueObject
 */
public class DescriptionString {
    public final String value;

    public DescriptionString(String desc) throws IllegalArgumentException {
        
        if(desc.length()>200) {
            throw new IllegalArgumentException(
                "Exepected a string of max size 200, composed with any characters, got: "+desc+" of size: "+desc.length());
        }
        this.value=desc;

    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof DescriptionString) {
            DescriptionString otherDS = (DescriptionString) other;
            return value.equals(otherDS);
        }
        return false;
    }
    @Override
    public String toString() {
        return value;
    }
}