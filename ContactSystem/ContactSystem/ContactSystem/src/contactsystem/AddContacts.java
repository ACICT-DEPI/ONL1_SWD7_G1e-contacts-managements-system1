/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactsystem;

import javax.swing.JOptionPane;

/**
 *
 * @author TASNEEM
 */
public class AddContacts {

    private String name;
    private String phoneNumber;
    private String address;
    private String email;

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean setName(String s) {
        this.name = s;
        return true;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9') {
                    JOptionPane.showMessageDialog(null, "Enter a valid phone number ");
                    return false;
                } else if (!phoneNumber.startsWith("01")) {
                    JOptionPane.showMessageDialog(null, "Phone number must start with '01' ");
                    return false;
                }
            }
            this.phoneNumber = phoneNumber;
            return true;
        }
        JOptionPane.showMessageDialog(null, "Enter a valid phone number ");
        return false;
    }

    public String setAddress() {
        return (this.address = address);
    }

    public String setEmail() {
        return (this.email = email);
    }
    

}
