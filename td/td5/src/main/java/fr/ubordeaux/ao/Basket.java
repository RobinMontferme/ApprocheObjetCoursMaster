package fr.ubordeaux.ao;

import java.util.HashMap;
import java.util.Map;
/**
 * Basket is an Aggregate
 */
public class Basket {
    private Map<Reference,CommandLine> commandLines;
    private boolean validedBasket = false;

    public Basket() {
        this.commandLines = new HashMap<Reference,CommandLine>();
    };

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

    public void validateBasket() {
        validedBasket = true;
    }
}