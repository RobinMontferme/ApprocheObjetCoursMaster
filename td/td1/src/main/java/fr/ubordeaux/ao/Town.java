package fr.ubordeaux.ao;

import java.util.Objects;

public class Town {
    private String name; //immutable
    private int zipcode; //immutable

    public Town(String name, int zipcode) {
        this.name = name;
        this.zipcode = zipcode;
    }

    public String getName() {//dépend
        return name;
    }

    public int getZipCode() {//dépend
        return zipcode;
    }

    @Override
    public boolean equals(Object other) {//dépend
        if (! (other instanceof Town)) return false;
        Town otherTown = (Town) other;
        boolean sameTown = this.name.compareTo(otherTown.name) == 0;
        boolean sameZipcode = this.zipcode == otherTown.zipcode;
        return  sameTown && sameZipcode;
    }

    @Override
    public int hashCode() {//dépend
        return Objects.hash(name, zipcode);
    }

    @Override
    public String toString() {//dépend
        return zipcode+" "+name;
    }
}