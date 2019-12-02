package fr.ubordeaux.ao.domain;
import java.util.List;
import java.util.ArrayList;

public class BasketDAO {
    public String id;
    public final boolean validated;
    public final List<CommandLineDAO> cmd;
    
    public BasketDAO(String id,boolean validated, List<CommandLineDAO> cmd) {
        this.id = id;
        this.validated = validated;
        this.cmd = cmd;
    }

}