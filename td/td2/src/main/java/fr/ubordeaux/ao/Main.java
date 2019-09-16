package fr.ubordeaux.ao;

import java.text.ParseException;

import fr.ubordeaux.ao.Account;
import fr.ubordeaux.ao.Transaction;

public class Main {
    public static void main(String[] args) throws ParseException{
        Account a = new Account("Dupont");
        System.out.println(a);
        Account b = new Account("Mike");
        System.out.println(b);
    
       
        Transaction tCasino = new Transaction("casino", new PositiveInt(450),"16/09/2001");
        //Transaction t2 = new Transaction("entreprise",new PositiveInt(-1),"16/08/1998"); throw exeception
        Transaction tEntreprise = new Transaction("entreprise", new PositiveInt(7800),"16/01/2002");
        Transaction tDividende = new Transaction("dividende", new PositiveInt(78710),"30/11/2004");
        Transaction tBitcoin = new Transaction("bitcoin", new PositiveInt(400050),"17/08/2019");
        System.out.println(a.getTransactionSince("01/01/2002").size());
        
        a.addTransaction(tCasino);
        System.out.println(a);
        a.addTransaction(tEntreprise);
        System.out.println(a);
        a.addTransaction(tDividende);
        System.out.println(a);
        a.addTransaction(tBitcoin);
        System.out.println(a);
        System.out.println(a.getTransactionSince("01/01/2002").size());
        System.out.println(tCasino.getDate());
        for(Transaction t : a.getTransactionSince("01/01/2002"))
        {
            System.out.println(t.getTitle());

        }
        a.deleteTransaction("bitcoin","17/08/2019", new PositiveInt(400050));
        for(Transaction t : a.getTransactionSince("01/01/1998"))
        {
            System.out.println(t.getTitle());

        }

    }
}