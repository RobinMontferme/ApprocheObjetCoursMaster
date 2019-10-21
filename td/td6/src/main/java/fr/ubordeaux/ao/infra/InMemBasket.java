package fr.ubordeaux.ao.infra;
import java.util.List;
import java.util.ArrayList;

public class InMemBasket {
    public final boolean validated;
    public final List<InMemCmdLine> cmd;
    public InMemBasket(boolean validated, List<InMemCmdLine> cmd) {
        this.validated = validated;
        this.cmd = cmd;
    }

}