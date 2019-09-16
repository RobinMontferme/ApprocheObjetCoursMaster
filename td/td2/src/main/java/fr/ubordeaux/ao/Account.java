package fr.ubordeaux.ao;
import java.util.Set;
import java.util.function.Predicate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Account {
    private double balance; //change
    private static int currentId=0;
    private Integer id; 
    private String name;
    private Set<Transaction> transactions;//change

    public Account(String name) {
        this.balance = 0;
        this.name = name;
        transactions = new HashSet<Transaction>();
        this.id = currentId;
        currentId++;
        
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        this.balance += transaction.getAmount();
    }
    public void deleteTransaction(String title, String date, PositiveInt amount) throws ParseException
    {
        // Iterator<Transaction> it = transactions.iterator();
        // while(it.hasNext())
        // {
        //     Transaction t = it.next();
        //     if(t.equals(new Transaction(title,amount,date)))
        //     {
        //         it.remove();
        //     }
        // }
        Transaction wanted = new Transaction(title,amount,date);
        Predicate<Transaction> pr = t->(t.equals(wanted));
        transactions.removeIf(pr);
    }
    
    public Set<Transaction> getTransactionSince(String date) throws ParseException{
        Set<Transaction> res = new HashSet<Transaction>();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        Date checkDate  = dateFormat.parse(date);
        for(Transaction t : transactions)
        {
            if(t.getDate().after(checkDate))
            {
                res.add(t.clone());
            }
        }
        return res;
    }


    @Override
    public boolean equals(Object other) {
        if (other instanceof Account) {
            Account otherAccount = (Account) other;
            return this.id.compareTo(otherAccount.id) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "Account "+this.name+" (id="+this.id+"), balance = "+this.balance;
    }
    
}