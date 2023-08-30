/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventorymanagementsystem;

/**
 *
 * @author mitta
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class InventoryManagementSystem {
    private static JFrame frame;
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Item> inventory = new HashMap<>();
    private static User currentUser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        mainPanel.add(registerButton);
        mainPanel.add(loginButton);
        mainPanel.add(exitButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegistrationForm();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginForm();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private static void showRegistrationForm() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        registrationPanel.add(usernameLabel);
        registrationPanel.add(usernameField);
        registrationPanel.add(passwordLabel);
        registrationPanel.add(passwordField);
        registrationPanel.add(new JLabel()); // Empty label for spacing
        registrationPanel.add(registerButton);

        frame.add(registrationPanel, BorderLayout.CENTER);
        frame.revalidate();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (!users.containsKey(username)) {
                    User newUser = new User(username, password);
                    users.put(username, newUser);
                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                    showMainMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Username already exists. Please choose a different username.");
                }
            }
        });
    }

    private static void showLoginForm() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(loginButton);

        frame.add(loginPanel, BorderLayout.CENTER);
        frame.revalidate();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
                    currentUser = users.get(username);
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    showMainMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                }
            }
        });
    }

    private static void showMainMenu() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(4, 1));

        JButton addItemButton = new JButton("Add Item to Inventory");
        JButton deleteItemButton = new JButton("Delete Item from Inventory");
        JButton purchaseItemButton = new JButton("Purchase Item");
        JButton logoutButton = new JButton("Logout");

        mainMenuPanel.add(addItemButton);
        mainMenuPanel.add(deleteItemButton);
        mainMenuPanel.add(purchaseItemButton);
        mainMenuPanel.add(logoutButton);

        frame.add(mainMenuPanel, BorderLayout.CENTER);
        frame.revalidate();

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddItemForm();
            }
        });

        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteItemForm();
            }
        });

        purchaseItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPurchaseItemForm();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                createAndShowGUI();
            }
        });
    }

    private static void showAddItemForm() {
        frame.getContentPane().removeAll();
    frame.repaint();

    JPanel addItemPanel = new JPanel();
    addItemPanel.setLayout(new GridLayout(4, 2));

    JLabel nameLabel = new JLabel("Item Name:");
    JTextField nameField = new JTextField();
    JLabel priceLabel = new JLabel("Item Price:");
    JTextField priceField = new JTextField();
    JLabel quantityLabel = new JLabel("Item Quantity:");
    JTextField quantityField = new JTextField();
    JButton addButton = new JButton("Add Item");

    addItemPanel.add(nameLabel);
    addItemPanel.add(nameField);
    addItemPanel.add(priceLabel);
    addItemPanel.add(priceField);
    addItemPanel.add(quantityLabel);
    addItemPanel.add(quantityField);
    addItemPanel.add(new JLabel()); // Empty label for spacing
    addItemPanel.add(addButton);

    frame.add(addItemPanel, BorderLayout.CENTER);
    frame.revalidate();

    addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            Item newItem = new Item(name, price, quantity);
            inventory.put(name, newItem);
            JOptionPane.showMessageDialog(frame, "Item added to inventory.");
            showMainMenu();
        }
    });
    }

    private static void showDeleteItemForm() {
         frame.getContentPane().removeAll();
    frame.repaint();

    JPanel deleteItemPanel = new JPanel();
    deleteItemPanel.setLayout(new GridLayout(2, 2));

    JLabel nameLabel = new JLabel("Item Name:");
    JTextField nameField = new JTextField();
    JButton deleteButton = new JButton("Delete Item");

    deleteItemPanel.add(nameLabel);
    deleteItemPanel.add(nameField);
    deleteItemPanel.add(new JLabel()); // Empty label for spacing
    deleteItemPanel.add(deleteButton);

    frame.add(deleteItemPanel, BorderLayout.CENTER);
    frame.revalidate();

    deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();

            if (inventory.containsKey(name)) {
                inventory.remove(name);
                JOptionPane.showMessageDialog(frame, "Item deleted from inventory.");
            } else {
                JOptionPane.showMessageDialog(frame, "Item not found in inventory.");
            }
            showMainMenu();
        }
    });
    }

    private static void showPurchaseItemForm() {
         frame.getContentPane().removeAll();
    frame.repaint();

    JPanel purchaseItemPanel = new JPanel();
    purchaseItemPanel.setLayout(new GridLayout(2, 2));

    JLabel nameLabel = new JLabel("Item Name:");
    JTextField nameField = new JTextField();
    JButton purchaseButton = new JButton("Purchase Item");

    purchaseItemPanel.add(nameLabel);
    purchaseItemPanel.add(nameField);
    purchaseItemPanel.add(new JLabel()); // Empty label for spacing
    purchaseItemPanel.add(purchaseButton);

    frame.add(purchaseItemPanel, BorderLayout.CENTER);
    frame.revalidate();

    purchaseButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();

            if (inventory.containsKey(name)) {
                Item item = inventory.get(name);
                if (item.getQuantity() > 0) {
                    int quantityToPurchase = Integer.parseInt(
                            JOptionPane.showInputDialog(frame, "Enter the quantity to purchase:"));

                    if (quantityToPurchase > 0 && quantityToPurchase <= item.getQuantity()) {
                        item.setQuantity(item.getQuantity() - quantityToPurchase);
                        double totalCost = item.getPrice() * quantityToPurchase;
                        JOptionPane.showMessageDialog(frame, "Purchase successful. Total cost: $" + totalCost);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid quantity.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Item out of stock.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Item not found in inventory.");
            }
            showMainMenu();
        }
    });
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

