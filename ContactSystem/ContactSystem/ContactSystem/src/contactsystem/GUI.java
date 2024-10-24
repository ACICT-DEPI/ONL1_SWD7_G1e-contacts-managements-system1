/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactsystem;

/**
 *
 * @author TASNEEM
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GUI extends JFrame {

    private ContactManager contactManager;
    private JTextField nameField;
    private JTextField phoneField;
    private JPanel table;

    public GUI(ContactManager contactManager) {
        this.contactManager = contactManager;
        initialize();
    }

    public void initialize() {
        setTitle("Contact Management System");
        setSize(900, 700);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JPanel inputPanel = createInputPanel();
        inputPanel.setBackground(Color.pink);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.setBackground(new Color(255, 204, 255));
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        GridLayout gridLayout = new GridLayout(7, 1, 0, 0);
        table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        displayContacts();

        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(new Color(255, 204, 255)); // Light pink background

        JLabel titleLabel = new JLabel("Contact Management System");
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        inputPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setForeground(Color.white); // Deep pink text color

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 2));
        fieldsPanel.setBackground(Color.BLACK);
        JLabel nameLabel = new JLabel("Enter the Name: ");
        nameLabel.setFont(new Font("Garamond", Font.BOLD, 20));

        nameField = new JTextField();
        nameLabel.setForeground(Color.white); // Dark purple text color

        JLabel phoneLabel = new JLabel("Enter the Phone Number: ");
        phoneLabel.setFont(new Font("Garamond", Font.BOLD, 20));

        phoneField = new JTextField();
        phoneLabel.setForeground(Color.white);

        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameField);
        fieldsPanel.add(phoneLabel);
        fieldsPanel.add(phoneField);

        inputPanel.add(fieldsPanel, BorderLayout.CENTER);
        nameField.setFont(new Font("Garamond", Font.BOLD, 20));
        phoneField.setFont(new Font("Garamond", Font.BOLD, 20));

        return inputPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.BLACK);

        JButton addButton = new JButton("Add Contact");
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.setForeground(Color.black);
        addButton.setFont(new Font("Garamond", Font.BOLD, 15));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter a valid Name");
                    //displayArea.setText("Please Enter The Name");
                } else if (phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter a valid phone Number");
                } else if (phone.length() != 11) {
                    JOptionPane.showMessageDialog(null, "phone Number must equal 11 digits");

                } else {
                    contactManager.addContact( name, phone);
                    displayContacts();
                    clearFields();
                }
            }
        }
        );
        buttonPanel.add(addButton);

        JButton searchButton = new JButton("Search Contact");

        searchButton.setBackground(
                new Color(250, 90, 100)); // Orchid button color
        searchButton.setForeground(Color.black);

        searchButton.setFont(
                new Font("Garamond", Font.BOLD, 15));
        searchButton.setBounds(
                10, 30, 89, 23);
        searchButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter The Name");
                } else {
                    String phone = contactManager.searchContact(name);
                    if (phone != "") {
                        JOptionPane.showMessageDialog(null, "Name: " + name + "\nPhone: " + phone);
                    } else {
                        JOptionPane.showMessageDialog(null, "Contact not found");
                    }
                }
                clearFields();
            }
        }
        );
        buttonPanel.add(searchButton);

        JButton sortButton = new JButton("Sort Contacts");

        sortButton.setForeground(Color.black);

        sortButton.setFont(
                new Font("Garamond", Font.BOLD, 15));
        sortButton.setBackground(Color.lightGray);

        sortButton.setForeground(Color.black);

        sortButton.setBounds(10, 30, 89, 23);
        sortButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (contactManager.getContacts().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No Contacts to Sort");
                } else {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem descItem = new JMenuItem("Sort Descending");
                    JMenuItem ascItem = new JMenuItem("Sort Ascending");

                    ascItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            contactManager.sortContacts(true); // Sort in ascending order
                            displayContacts();
                            clearFields();
                        }
                    });
                    descItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            contactManager.sortContacts(false); // Sort in descending order
                            displayContacts();
                            clearFields();
                        }
                    });
                    menu.add(ascItem);
                    menu.add(descItem);
                    menu.show(sortButton, 0, sortButton.getHeight());
                }
            }
        }
        );
        buttonPanel.add(sortButton);

        JButton deleteAllButton = new JButton("Clear");

        deleteAllButton.setBackground(
                new Color(250, 90, 100)); // Orchid button color
        deleteAllButton.setForeground(Color.black);

        deleteAllButton.setFont(
                new Font("Garamond", Font.BOLD, 15));
        deleteAllButton.setBounds(
                10, 30, 89, 23);
        deleteAllButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (!contactManager.getContacts().isEmpty()) {
                    contactManager.deleteAllContacts();
                    displayContacts();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "No Contacts To Delete");
                    clearFields(); // Clear fields even if there are no contacts
                    displayContacts(); // Update the display after clearing the fields
                }
            }
        }
        );
        buttonPanel.add(deleteAllButton);

        return buttonPanel;
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
    }

    private void displayContacts() {
        List<AddContacts> contacts = contactManager.getContacts();
        table.removeAll();

        //JPanel contactPanel = new JPanel(new GridLayout(contacts.size(), 1));
        for (AddContacts contact : contacts) {
            JPanel singleContactPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel nameLabel = new JLabel(contact.getName());
            JLabel phoneLabel = new JLabel(contact.getPhoneNumber());

            JButton editNameButton = new JButton("Edit Name");
            editNameButton.setBackground(Color.lightGray);// Orchid button color
            editNameButton.setForeground(Color.black);
            editNameButton.setFont(new Font("Garamond", Font.BOLD, 15));
            editNameButton.setBounds(10, 30, 89, 23);
            editNameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Boolean f = contact.setName(JOptionPane.showInputDialog(null, "Type The new Name:"));
                    if(f)
                        JOptionPane.showMessageDialog(null,"Name Edit Successfully...");
                    displayContacts();
                }
            });
            
            JButton editPhoneButton = new JButton("Edit Phone Number");
            editPhoneButton.setBackground(Color.lightGray);// Orchid button color
            editPhoneButton.setForeground(Color.black);
            editPhoneButton.setFont(new Font("Garamond", Font.BOLD, 15));
            editPhoneButton.setBounds(10, 30, 89, 23);
            editPhoneButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Boolean f = contact.setPhoneNumber(JOptionPane.showInputDialog(null, "Type The new Phone Number:"));
                    if(f)
                        JOptionPane.showMessageDialog(null,"Phone Number Edit Successfully...");
                    displayContacts();
                }
            });

            JButton deleteButton = new JButton("Delete Contact");
            deleteButton.setBackground(Color.lightGray);// Orchid button color
            deleteButton.setForeground(Color.black);
            deleteButton.setFont(new Font("Garamond", Font.BOLD, 15));
            deleteButton.setBounds(10, 30, 89, 23);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!contactManager.getContacts().isEmpty()) {
                        contactManager.deleteContact(contactManager.getContacts().get(0).getName());
                        displayContacts();
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "No contacts available to delete");
                    }
                }
            });

            singleContactPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Adjust the spacing as needed
            singleContactPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10)); // Adjust the spacing as needed

            singleContactPanel.add(nameLabel);
            singleContactPanel.add(phoneLabel);
            singleContactPanel.add(editNameButton);
            singleContactPanel.add(editPhoneButton);
            singleContactPanel.add(deleteButton);

            nameLabel.setFont(new Font("Garamond", Font.BOLD, 20));
            nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // Adjust the spacing as needed
            phoneLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 170)); // Adjust the spacing as needed

            phoneLabel.setFont(new Font("Garamond", Font.BOLD, 20));
            
            table.add(singleContactPanel);
        }
        table.revalidate();
        table.repaint();
    }

    public void showGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }
}
