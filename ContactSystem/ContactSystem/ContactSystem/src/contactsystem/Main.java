/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package contactsystem;

/**
 *
 * @author TASNEEM
 */
public class Main {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        GUI gui = new GUI(contactManager);
        gui.showGUI();
    }
}