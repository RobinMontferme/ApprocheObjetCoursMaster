package fr.ubordeaux.ao.application;

import fr.ubordeaux.ao.application.Command;
import fr.ubordeaux.ao.domain.Basket;
import fr.ubordeaux.ao.domain.BasketRepository;
import fr.ubordeaux.ao.domain.Reference;
public class AddReferenceCommand extends Command {
    private int quantity;
    private Reference reference;
    public AddReferenceCommand(Reference ref, int qty,Basket basket ,BasketRepository rep) {
        super(basket,rep);
        this.quantity = qty;
        this.reference = ref;
    }
    @Override
    public void execute() {
        basket.addRefAndQty(reference, quantity);
        rep.save(basket);
    }
}