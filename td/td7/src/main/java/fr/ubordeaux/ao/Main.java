package fr.ubordeaux.ao;

import fr.ubordeaux.ao.domain.*;
import fr.ubordeaux.ao.infra.*;


import fr.ubordeaux.ao.application.*;

public class Main {
    
    public static void main(String[] args) {
        
        BasketService bServ = new BasketService();
        Gateway g = new GatewayImpl();
        BasketRepository rep = new BasketInMemoryRepository();
        Query query = new Query(rep);
        g.addCommandHandler(new HandlerImpl());
        g.addCommandHandler(new HandlerImpl());
        bServ.setRepository(rep);
        bServ.setGateway(g);
        bServ.setQuery(query);
        


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

        String basketID = bServ.addReferenceToEmptyBasket(rM);
        System.out.println(bServ.getBasketPrice(basketID));
        
        bServ.addRefAmountToBasket(rZ,4,basketID);
        System.out.println(bServ.getBasketPrice(basketID));
        
        bServ.removeRefFromBasket(rM,basketID);
        System.out.println(bServ.getBasketPrice(basketID));

        bServ.validateBasket(basketID);        
        bServ.addRefAmountToBasket(rP,140,basketID);
        
        System.out.println(bServ.getBasketPrice(basketID));


        // BasketRepository rep = new BasketJsonRepository();

     
        
        
    }
}