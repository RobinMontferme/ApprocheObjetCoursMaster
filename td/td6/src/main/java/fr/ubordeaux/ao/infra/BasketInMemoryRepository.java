package fr.ubordeaux.ao.infra;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import fr.ubordeaux.ao.domain.*;

public class BasketInMemoryRepository implements BasketRepository {
    private Map<String,InMemBasket> repData = new HashMap<String,InMemBasket>();
    public void save(Basket b) {
        List<InMemCmdLine> savedCmdLines = new ArrayList<InMemCmdLine>();
        for(CommandLine c : b.getCommandLines().values()) {

            int qty = c.getQuantity();
            Reference cmdRef = c.getReference();
            int price = cmdRef.price;
            String ref = cmdRef.ref.value;
            String name = cmdRef.name.value;
            String desc = cmdRef.desc.value; 
            InMemCmdLine singleCmdLine = new InMemCmdLine(qty,price,ref,name,desc);
            savedCmdLines.add(singleCmdLine);
        }
        InMemBasket basketToSave = new InMemBasket(b.isValidated(),savedCmdLines);

        repData.put(b.getId(),basketToSave);
    }
    public Basket load(String id) {
        InMemBasket basketToLoad = repData.get(id);

        Basket loadedBasket = new Basket(id);

        for(InMemCmdLine imc : basketToLoad.cmd)
        {
            RefString ref = new RefString(imc.ref);
            NameString name = new NameString(imc.name);
            DescriptionString desc = new DescriptionString(imc.desc);
            int price = imc.price;

            Reference reference = new Reference(ref,name,desc,price);
            loadedBasket.addRefAndQty(reference, imc.qty);

            boolean basketValidation = basketToLoad.validated;
            if(basketValidation) {
                loadedBasket.validateBasket();
            }
        }
       return loadedBasket; 
    }
}