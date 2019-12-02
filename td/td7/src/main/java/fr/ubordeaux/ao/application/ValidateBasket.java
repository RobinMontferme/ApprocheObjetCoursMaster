package fr.ubordeaux.ao.application;

import fr.ubordeaux.ao.application.Command;
import fr.ubordeaux.ao.domain.Basket;
import fr.ubordeaux.ao.domain.BasketRepository;

public class ValidateBasket extends Command{
    public ValidateBasket(Basket basket, BasketRepository rep)
    {
        super(basket,rep);
    }
    @Override
    public void execute() {
        basket.validateBasket();
        rep.save(basket);
    }
}
// map.push(VO,queryRES)