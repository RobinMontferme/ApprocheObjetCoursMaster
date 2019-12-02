package fr.ubordeaux.ao.application;


public interface Gateway { //queue

    public void pushCommand(Command c);

    public void addCommandHandler(Handler h);

    
}