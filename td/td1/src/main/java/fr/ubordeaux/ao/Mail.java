package fr.ubordeaux.ao;

import java.util.Objects;

public class Mail {
    private String mail; //immutable

    public Mail(String mail) {
        this.mail = mail;
    }

    public String getMail() {//dépend
        return mail;
    }

    @Override
    public boolean equals(Object other) {//dépend
        if (! (other instanceof Mail)) return false;
        Mail otherMail = (Mail) other;
        return  this.mail.compareTo(otherMail.getMail())==0;
    }

    @Override
    public int hashCode() {//dépend
        return Objects.hash(mail);
    }

    @Override
    public String toString() {//dépend
        return "mails: "+mail;
    }

}