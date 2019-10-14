package fr.ubordeaux.ao.domain;
public interface BasketRepository {
    public void save(Basket b);
    public Basket load(String basketId);
}