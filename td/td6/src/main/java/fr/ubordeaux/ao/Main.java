package fr.ubordeaux.ao;

import fr.ubordeaux.ao.domain.*;
import fr.ubordeaux.ao.infra.*;

public class Main {

    public static void main(String[] args) {
        Basket b = new Basket("Super Panier");

        RefString rsZ = new RefString("WozfT2eGr981d7QZY124");
        NameString nsZ = new NameString("Zoldo");
        DescriptionString dsZ = new DescriptionString("this is gud game");
        int priceZ = 39;
        Reference rZ = new Reference(rsZ, nsZ, dsZ, priceZ);

        RefString rsM = new RefString("WdzfT2eGr981d7QZY124");
        NameString nsM = new NameString("Mariol");
        DescriptionString dsM = new DescriptionString("this is also gud game");
        int priceM = 39;
        Reference rM = new Reference(rsM, nsM, dsM, priceM);

        RefString rsP = new RefString("WpdfT2eGr981d7QZY124");
        NameString nsP = new NameString("Pekamon");
        DescriptionString dsP = new DescriptionString("also this is also gud game");
        int priceP = 39;
        Reference rP = new Reference(rsP, nsP, dsP, priceP);

        b.addRefAndQty(rZ, 4);
        b.addRefAndQty(rM, 1);
        System.out.println(b.getTotalPrice());
        // b.deleteRefFromBasket(rM);
        System.out.println(b.getTotalPrice());
        b.validateBasket();
        b.addRefAndQty(rP, 140);
        System.out.println(b.getTotalPrice());

        // BasketRepository rep = new BasketJsonRepository();
        BasketRepository rep = new BasketInMemoryRepository();

        rep.save(b);
        
        BasketDAO dup = rep.load("Super Panier");
        Basket newB = new Basket(dup);
        
        System.out.println(newB.getTotalPrice());
        System.out.println("basket is validated: "+newB.isValidated());
    }
}