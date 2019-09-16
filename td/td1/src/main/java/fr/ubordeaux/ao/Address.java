package fr.ubordeaux.ao;

import java.util.Objects;

public class Address {
    private int number; // immutable
    private String street; // immutable
    private Town town;// immutable

    public Address(int number, String street, Town town) {
        this.number = number;
        this.street = street;
        this.town = town;
    }

    public int getNumber() { //dépend
        return number;
    }

    public String getStreet() { //dépend
        return street;
    }

    public Town getTown() { //dépend
        return town;
    }

    @Override
    public boolean equals(Object other) { //dépend
        if (! (other instanceof Address)) return false;
        Address otherAddress = (Address) other;
        boolean sameNumber = this.number == otherAddress.getNumber();
        boolean sameStreet = this.street.compareTo(otherAddress.getStreet()) == 0;
        boolean sameTown = this.town.equals(otherAddress.getTown());
        return  sameNumber && sameStreet && sameTown;
    }

    @Override
    public int hashCode() {//dépend
        return Objects.hash(number, street, town);
    }

    @Override
    public String toString() { //dépend
        return number+" "+street+" "+town.toString();
    }

}