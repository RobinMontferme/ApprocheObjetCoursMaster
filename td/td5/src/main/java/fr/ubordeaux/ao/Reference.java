package fr.ubordeaux.ao;

/**
 * Reference is a ValueObject
 */
public class Reference {
    public final RefString ref;
    public final NameString name;
    public final DescriptionString desc;
    public final int price;
    
    public Reference(RefString ref, NameString name, DescriptionString desc, int price) throws IllegalArgumentException
    {
        this.ref=ref;
        this.name=name;
        this.desc=desc;
        if(price <=0) {
            throw new IllegalArgumentException("Exepected a positive integer value, got: "+price) ;
        }
        this.price=price;
    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof Reference) {
            Reference otherRef = (Reference)other;
            return (ref.equals(otherRef.ref)) && (name.equals(otherRef.name))
            && (desc.equals(otherRef.desc)) && (price==otherRef.price);
        }
        return false;
    }
    @Override
    public String toString()
    {
        return "ref: "+ ref + " name: " + name +" desc: " +desc+ " price: " +price;
    }
}