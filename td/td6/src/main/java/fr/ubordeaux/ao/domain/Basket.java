package fr.ubordeaux.ao.domain;

import java.util.HashMap;
import java.util.Map;

import fr.ubordeaux.ao.domain.CommandLine;
/**
 * Basket is an Aggregate
 */
public class Basket {
    private Map<Reference,CommandLine> commandLines;
    private boolean validedBasket = false;
    private String id;
    public Basket(String id) {
        this.id=id;
        this.commandLines = new HashMap<Reference,CommandLine>();
    };

    public String getId() {
        return this.id;
    }
    public int getTotalPrice() {
        int totalPrice = 0;
        for (CommandLine c : commandLines.values()) {
            totalPrice += c.getPriceCommandLine();
        }
        return totalPrice;
    }

    private void addCommandLine(CommandLine newCommandLine) {
        if (!validedBasket) {

            if (commandLines.containsKey(newCommandLine.getReference())) {
                CommandLine presentCmdline = commandLines.get(newCommandLine);
                presentCmdline.addQuantity(newCommandLine.getQuantity());
            } else {
                commandLines.put(newCommandLine.getReference(),newCommandLine);
            }
        }
    }

    public void addRefAndQty(Reference ref, int quantity) {
        if(!validedBasket){
            addCommandLine(new CommandLine(ref,quantity));
        }
    }

    public void deleteRefFromBasket(Reference ref) {
        if(!validedBasket) {
            if(commandLines.containsKey(ref)) {
                CommandLine c = commandLines.get(ref);
                if(c.getQuantity()>1){
                   c.removeQuantity(1);
                }
                else {
                    commandLines.remove(ref); 
                }
            }
        }
    }

    public Map<Reference,CommandLine> getCommandLines() {
        Map<Reference,CommandLine> copiedCmdLines = new HashMap<Reference,CommandLine>();
        for(CommandLine c: commandLines.values()) {
            copiedCmdLines.put(c.getReference(),c.clone());
        }
        return commandLines;
    }

    public void validateBasket() {
        validedBasket = true;
    }
}