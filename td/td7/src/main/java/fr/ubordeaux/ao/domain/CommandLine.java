package fr.ubordeaux.ao.domain;

/**
 * CommandLine is an Entity
 */
public class CommandLine {
    private final Reference reference; // id
    private int quantity;

    public CommandLine(Reference reference, int quantity) {
        this.reference = reference;
        if (quantity <= 0) {
            throw new IllegalArgumentException("Exepected a positive integer value, got: " + quantity);
        }
        this.quantity = quantity;
    }

    public Reference getReference() {
        return reference;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int qty) throws IllegalArgumentException {
        if (qty < 0) {
            throw new IllegalArgumentException("Expected postive integer, got: " + qty);
        }
        this.quantity += qty;
    }

    public void removeQuantity(int qty) throws IllegalArgumentException {
        if (qty < 0) {
            throw new IllegalArgumentException("Expected positive integer, got: " + qty);
        }
        this.quantity -= qty;
    }

    public int getPriceCommandLine() {
        return quantity* reference.price;
    }

    @Override
    public CommandLine clone()
    {
        return new CommandLine(this.reference, this.quantity);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof CommandLine) {
            CommandLine otherCommandLine = (CommandLine) other;
            return reference.equals(otherCommandLine.getReference());
        }
        return false;
    }
    @Override
    public String toString() {
        return reference.toString() + " QTY: " + quantity;
    }
}