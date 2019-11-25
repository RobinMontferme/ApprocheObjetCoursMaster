package fr.ubordeaux.ao.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import fr.ubordeaux.ao.domain.CommandLine;

/**
 * Basket is an Aggregate
 */
public class Basket {
    private Map<Reference, CommandLine> commandLines = new HashMap<Reference, CommandLine>();
    private boolean validedBasket = false;
    private String id;

    public Basket(String id) {
        this.id = id;
    }

      public Basket(BasketDAO bDAO) {

        this.id = bDAO.id;
        for (CommandLineDAO imc : bDAO.cmd) {
            RefString ref = new RefString(imc.ref);
            NameString name = new NameString(imc.name);
            DescriptionString desc = new DescriptionString(imc.desc);
            int price = imc.price;
            Reference reference = new Reference(ref, name, desc, price);
            
            addRefAndQty(reference, imc.qty);
        }
        this.validedBasket = bDAO.validated;
    }

    public String getId() {
        return this.id;
    }

    public boolean isValidated() {
        return validedBasket;
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
                commandLines.put(newCommandLine.getReference(), newCommandLine);
            }
        }
    }

  

    public void addRefAndQty(Reference ref, int quantity) {
        if (!validedBasket) {
            addCommandLine(new CommandLine(ref, quantity));
        }
    }

    public void deleteRefFromBasket(Reference ref) {
        if (!validedBasket) {
            if (commandLines.containsKey(ref)) {
                CommandLine c = commandLines.get(ref);
                if (c.getQuantity() > 1) {
                    c.removeQuantity(1);
                } else {
                    commandLines.remove(ref);
                }
            }
        }
    }

    public Map<Reference, CommandLine> getCommandLines() {
        Map<Reference, CommandLine> copiedCmdLines = new HashMap<Reference, CommandLine>();
        for (CommandLine c : commandLines.values()) {
            copiedCmdLines.put(c.getReference(), c.clone());
        }
        return commandLines;
    }

    public void validateBasket() {

        validedBasket = true;
    }

    public BasketDAO serialize() {
        List<CommandLineDAO> serialCommandLines = new ArrayList<CommandLineDAO>();
        for (CommandLine c : commandLines.values()) {

            int qty = c.getQuantity();
            Reference cmdRef = c.getReference();
            int price = cmdRef.price;
            String ref = cmdRef.ref.value;
            String name = cmdRef.name.value;
            String desc = cmdRef.desc.value;
            CommandLineDAO singleCmdLine = new CommandLineDAO(qty, price, ref, name, desc);
            serialCommandLines.add(singleCmdLine);
        }
        BasketDAO serialBasket = new BasketDAO(id, validedBasket, serialCommandLines);
        return serialBasket;
    }

}