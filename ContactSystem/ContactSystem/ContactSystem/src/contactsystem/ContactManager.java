/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactsystem;

/**
 *
 * @author TASNEEM
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;

public class ContactManager {

    private List<AddContacts> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String phoneNumber) {
        for (AddContacts contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                JOptionPane.showMessageDialog(null, "This Phone Number is entered Before");
                return;
            } else if (contact.getName().equals(name)) {

                JOptionPane.showMessageDialog(null, "This Name is entered Before");
                return;
            }
        }
        AddContacts contact = new AddContacts();
        if (contact.setName(name) && contact.setPhoneNumber(phoneNumber)) {
            contacts.add(contact);
            JOptionPane.showMessageDialog(null, "Contact add succefully...");
        }
    }
    /*public AddContacts* getContactWithName(){
    
    }*/
    public void modifyContact(String name, String phoneNumber) {
        for (AddContacts contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setPhoneNumber(phoneNumber);
                break;
            }
        }
    }

    public String searchContact(String name) {
        for (AddContacts contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact.getPhoneNumber();
 
            }
        }
        return "";
    }

    public void deleteContact(String name) {
        AddContacts contactToRemove = null;
        for (AddContacts contact : contacts) {
            if (contact.getName().equals(name)) {
                contactToRemove = contact;
                break;
            }
        }
        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
        }
    }

    public void sortContacts(boolean ascending) {
        Collections.sort(contacts, new SortContacts(ascending));
    }
    
    public List<AddContacts> getContacts() {
        return contacts;
    }
    
    public void deleteAllContacts() {
        contacts.clear();
    }

    
}
