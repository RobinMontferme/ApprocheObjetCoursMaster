package fr.ubordeaux.ao.infra;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import fr.ubordeaux.ao.domain.*;

public class BasketInMemoryRepository implements BasketRepository {
    private Map<String,BasketDAO> repData = new HashMap<String,BasketDAO>();
    public void save(Basket b) {
        
        BasketDAO basketToSave = b.serialize();
        repData.put(basketToSave.id,basketToSave);
    }

    public void update(Basket b) {

    }
    public BasketDAO load(String id) {

        BasketDAO basketToLoad = repData.get(id);

       return basketToLoad; 
    }
}