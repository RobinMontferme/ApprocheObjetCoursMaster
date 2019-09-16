package fr.ubordeaux.ao;
import java.util.Date;

import java.text.ParseException;

import java.text.SimpleDateFormat;
public class Transaction {
    private Date date;
    private String title;
    private PositiveInt amount;

    public Transaction(String title,  PositiveInt amount, String date) throws ParseException{
        //Affecter la date à aujourd'hui
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        this.title = title;
        this.amount = amount;
        this.date = dateFormat.parse(date);
      
    }

    public Transaction(String title,  PositiveInt amount, Date date){
        //Affecter la date à aujourd'hui
        this.title = title;
        this.amount = amount;
        this.date = date;
      
    }

    public Transaction clone()
    {
        return new Transaction(this.title,this.amount,this.date);
    }
    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount.getValue();
    }
    @Override
    public boolean equals(Object other) {
        if (other instanceof Transaction) {
            Transaction otherTransaction = (Transaction) other;
            return
             (this.amount.getValue() == otherTransaction.amount.getValue())||
            this.date.equals(otherTransaction.date)||
            this.title.equals(otherTransaction.title);
        }
        return false;
    }
}