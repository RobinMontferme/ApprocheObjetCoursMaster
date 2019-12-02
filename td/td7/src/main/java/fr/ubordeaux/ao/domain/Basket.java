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
    private static int autoId = 0;
    private Map<Reference, CommandLine> commandLines = new HashMap<Reference, CommandLine>();
    private boolean validedBasket = false;
    private String id;

    public Basket(String id) {
        this.id = id;
    }
    public Basket() {
        this.id = Integer.toString(autoId);
        autoId++;
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
            System.out.println("Basket isn't validated");
            if (commandLines.containsKey(ref)) {
                CommandLine c = commandLines.get(ref);
                if (c.getQuantity() > 1) {
                    c.removeQuantity(1);
                    System.out.println("Removing 1, just "+c.getQuantity()+" left");
                } else {
                    commandLines.remove(ref);
                    System.out.println("No More Left");
                }
            }else{
                System.out.println("Nothing found");
            }
        }else{

        System.out.println("Basket was validated");
        }
    }

    public Map<Reference, CommandLine> getCommandLines() {
        Map<Reference, CommandLine> copiedCmdLines = new HashMap<Reference, CommandLine>();
        for (CommandLine c : commandLines.values()) {
            copiedCmdLines.put(c.getReference(), c.clone());
        }
        return copiedCmdLines;
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