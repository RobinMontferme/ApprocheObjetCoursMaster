package fr.ubordeaux.ao;

import java.util.Objects;

public class PhoneNumber {
    private int number;//immutable

    public PhoneNumber(int number) {
        this.number = number;
    }

    public int getPhoneNumber() {//dépend
        return number;
    }

    @Override
    public boolean equals(Object other) {//dépend
        if (! (other instanceof PhoneNumber)) return false;
        PhoneNumber otherPhoneNumber = (PhoneNumber) other;
        return  number == otherPhoneNumber.getPhoneNumber();
    }

    @Override
    public int hashCode() {//dépend
        return Objects.hash(number);
    }

    @Override
    public String toString() {//dépend
        return ""+number;
    }
}