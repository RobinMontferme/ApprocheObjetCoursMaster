package fr.ubordeaux.ao.application;

import fr.ubordeaux.ao.domain.Basket;
import fr.ubordeaux.ao.domain.BasketRepository;
import fr.ubordeaux.ao.domain.Reference;
import fr.ubordeaux.ao.domain.CommandLine;

import fr.ubordeaux.ao.application.AddReferenceCommand;
import fr.ubordeaux.ao.application.RemoveRefCommand;
import fr.ubordeaux.ao.application.ValidateBasket;
import fr.ubordeaux.ao.application.Gateway;
import fr.ubordeaux.ao.application.Query;

import java.util.Map;
import java.util.HashMap;
public class BasketService {
    private BasketRepository rep;
    private Gateway gateway;
    private Query query;
    
    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }
    public void setRepository(BasketRepository rep) {
        this.rep = rep;
    }
    public void setQuery(Query query) {
        this.query = query;
    }
    
    public String addReferenceToEmptyBasket(Reference ref) {
        Basket b = new Basket();
        String basketID = b.getId();
        // b.addRefAndQty(ref,1);
        // rep.save(b);
        Command c = new AddReferenceCommand(ref,1,b,rep);
        gateway.pushCommand(c);
        return basketID;
    }

    public void addRefAmountToBasket(Reference ref, int qty, String basketID) {
        // Basket b = new Basket(rep.load(basketID)); //
        Basket b = query.getBasket(basketID);
        // b.addRefAndQty(ref, qty);
        // rep.save(b); //write
        Command c = new AddReferenceCommand(ref,qty,b,rep);
        gateway.pushCommand(c);
    }

    public void validateBasket(String basketID) {
        // Basket b = new Basket(rep.load(basketID)); //read
        Basket b = query.getBasket(basketID);
        // rep.save(b); //write
        // b.validateBasket();
        Command c = new ValidateBasket(b,rep);
        gateway.pushCommand(c);
    }
    
    public int getBasketPrice(String basketID) {
        Basket b = new Basket(rep.load(basketID)); //read
        return b.getTotalPrice();
    }
    
    public void removeRefFromBasket(Reference ref, String basketID){
        // Basket b = new Basket(rep.load(basketID)); //read
        Basket b = query.getBasket(basketID);
        Command c = new RemoveRefCommand(ref,b,rep);
        gateway.pushCommand(c);
        // b.deleteRefFromBasket(ref);
        // rep.save(b); //write
    }
    public Map<Reference,Integer> getBasketContent(String basketID) {
        // Basket b = new Basket(rep.load(basketID)); //read
        Basket b = query.getBasket(basketID);
        Map<Reference,Integer> result = new HashMap<Reference,Integer>();
        for(CommandLine c: b.getCommandLines().values()) {
            result.put(c.getReference(),c.getQuantity());
        }
        return result;
    }

}