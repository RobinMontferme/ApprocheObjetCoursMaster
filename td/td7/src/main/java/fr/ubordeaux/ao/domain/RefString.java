package fr.ubordeaux.ao.domain;
import java.util.regex.*;

/**
 * RefString is a ValueObject
 */
public class RefString {
    public final String value;

    public RefString(String ref) throws IllegalArgumentException {
        
        String pattern = "[a-zA-Z0-9]{20}";
        if(!Pattern.matches(pattern,ref)) {
            throw new IllegalArgumentException(
                "Exepected a string of size 20, composed with alphanumerical characters, got: "+ref+" of size: "+ref.length());
        }
        this.value=ref;

    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof RefString) {
            RefString otherRS = (RefString) other;
            return value.equals(otherRS.value);
        }
        return false;
    }
    @Override
    public String toString() {
        return value;
    }
}