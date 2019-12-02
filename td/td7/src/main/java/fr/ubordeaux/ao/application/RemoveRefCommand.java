package fr.ubordeaux.ao.application;

import fr.ubordeaux.ao.application.Command;
import fr.ubordeaux.ao.domain.Basket;
import fr.ubordeaux.ao.domain.BasketRepository;
import fr.ubordeaux.ao.domain.Reference;

public class RemoveRefCommand extends Command {
    private Reference ref;
    public RemoveRefCommand(Reference ref, Basket basket, BasketRepository rep) {
        super(basket,rep);
        this.ref = ref;
    }
    @Override
    public void execute() {
        basket.deleteRefFromBasket(ref);
        System.out.println(ref);
        rep.save(basket);
    }
}