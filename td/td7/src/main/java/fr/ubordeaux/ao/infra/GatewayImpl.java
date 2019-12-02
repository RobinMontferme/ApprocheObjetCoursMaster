package fr.ubordeaux.ao.infra;

import fr.ubordeaux.ao.application.Gateway;
import fr.ubordeaux.ao.application.Handler;
import fr.ubordeaux.ao.application.Command;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
public class GatewayImpl implements Gateway {

    private List<Handler> handlerList ;
    private Queue<Command> commandQueue ;
    public GatewayImpl() {
        handlerList = new ArrayList<Handler>();
        commandQueue = new LinkedList<Command>();
    }
    public void pushCommand(Command c){
        commandQueue.add(c);
        if(handlerList.size()!=0)
        {
            int rand = random(0,handlerList.size()-1);
            Handler h = handlerList.get(rand);        
            h.handle(commandQueue.remove());
        }
    }

    private int random(int min, int max) {
        return (int) (min + (Math.random()*(max -min )));
    }

    public void addCommandHandler(Handler h){// call in main 
        handlerList.add(h);
    }
}