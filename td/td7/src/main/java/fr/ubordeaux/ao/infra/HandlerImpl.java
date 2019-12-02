package fr.ubordeaux.ao.infra;

import fr.ubordeaux.ao.application.Handler;
import fr.ubordeaux.ao.application.Command;
public class HandlerImpl implements Handler {
    
    public void handle(Command command){
        command.execute();
    }
}