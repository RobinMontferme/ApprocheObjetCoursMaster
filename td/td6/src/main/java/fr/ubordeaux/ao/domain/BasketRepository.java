package fr.ubordeaux.ao.domain;
public interface BasketRepository {
    public void save(Basket b);
    public void update(Basket b);

    public BasketDAO load(String basketId);

}