package fr.ubordeaux.ao;

/**
 * NameString is a ValueObject
 */
public class NameString {
    public final String value;

    public NameString(String name) throws IllegalArgumentException {
        
        if(name.length() > 20) {
            throw new IllegalArgumentException(
                "Exepected a string of max size 20, composed with any characters, got: "+name+" of size: "+name.length());
        }
        this.value=name;

    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof NameString) {
            NameString otherNS = (NameString) other;
            return value.equals(otherNS);
        }
        return false;
    }
    @Override
    public String toString() {
        return value;
    }
}