package fr.ubordeaux.ao;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Contact {
    private static int nextId=1; //non immutable
    private int id; //imutabme
    private String firstName; // immutable
    private String secondName; //immutable
    private Address address; //non immutable
    private Set<Mail> mails; //non immutable
    private Set<PhoneNumber> phones;// non immutable


    public Contact(String firstName, String secondName, Address address) { //modifie
        this.id = Contact.nextId++;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        mails = new HashSet<Mail>();
        phones = new HashSet<PhoneNumber>();
    }

    public int id() { // dépendent
        return id;
    }

    public String getFirstName() {// dépend
        return firstName;
    }

    public String getSecondName() { //dépend
        return secondName;
    }

    public void changeAddress(Address newAddress) { //modifie
        this.address = newAddress;
    }

    public Address getAddress() { //dépend
        return address;
    }

    public void addMail(Mail mail) { //modifie
        mails.add(mail);
    }

    public void removeMail(Mail mail) {//modifie
        mails.remove(mail);
    }

    public Set<Mail> getMails() {//dépend
        Set<Mail> mailz = new HashSet<Mail>();
        mailz.addAll(mails);
        return mailz;
    }

    public void addPhoneNumber(PhoneNumber phone) {//modifie
        phones.add(phone);
    }

    public void removePhoneNumber(PhoneNumber phone) {//modifie
        phones.remove(phone);
    }

    public Set<PhoneNumber> getPhoneNumbers() {//dépend
        Set<PhoneNumber> phonez = new HashSet<PhoneNumber>();
        phonez.addAll(phones);
        return phonez;
    }

    @Override
    public boolean equals(Object other) {//modifie
        if (! (other instanceof Contact)) return false;
        Contact otherContact = (Contact) other;
        return  this.id == otherContact.id();
    }

    @Override
    public int hashCode() {//dépend
        return Objects.hash(id);
    }

    @Override
    public String toString() {//dépend
        return "contact "+id;
    }
}