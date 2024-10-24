/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactsystem;

import java.util.Comparator;

/**
 *
 * @author TASNEEM
 */
public class SortContacts implements Comparator<AddContacts> {

    private boolean ascending;

    public SortContacts(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(AddContacts contact1, AddContacts contact2) {
        if (ascending) {
            return contact1.getName().compareTo(contact2.getName());
        } else {
            return contact2.getName().compareTo(contact1.getName());
        }
    }
}
