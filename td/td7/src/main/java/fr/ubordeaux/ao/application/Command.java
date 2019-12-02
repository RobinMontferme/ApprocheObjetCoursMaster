package fr.ubordeaux.ao.application;

import fr.ubordeaux.ao.domain.Basket;
import fr.ubordeaux.ao.domain.BasketRepository;

public abstract class Command {
        protected Basket basket;
        protected BasketRepository rep;
        public Command(Basket basket, BasketRepository rep){
                this.basket = basket;
                this.rep = rep;
        }
        public abstract void execute();
}