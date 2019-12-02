package fr.ubordeaux.ao.domain;

public class CommandLineDAO {
    public final int qty;
    public final int price;
    public final String ref;
    public final String name;
    public final String desc;
    
    public CommandLineDAO(int qty,int price, String ref, String name, String desc) {
        this.qty = qty;
        this.price = price;
        this.ref = ref;
        this.name = name;
        this.desc = desc;

    }
}