package fr.ubordeaux.ao.application;

import fr.ubordeaux.ao.domain.*;

public class Query {
    private Basket cachedBasket;
    private BasketRepository rep;

    public Query(BasketRepository rep) {
        this.rep = rep;
    }

    public Basket getBasket(String basketId) {
        if (cachedBasket == null) {
            cachedBasket = new Basket(rep.load(basketId));
        }
        if (!basketId.equals(cachedBasket.getId())) {
            cachedBasket = new Basket(rep.load(basketId));
        }
        return cachedBasket;
    }
}
