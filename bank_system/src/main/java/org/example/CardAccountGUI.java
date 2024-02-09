package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CardAccountGUI extends JFrame {
    private ArrayList<CardAccount> cardAccounts;
    private DefaultListModel<String> listModel;
    private JList<String> accountList;
    private JTextField cardNumberField, surnameField, addressField, balanceField, issueDateField, validityTermField;
    private JCheckBox creditCheckBox;
    private JButton addButton, saveButton;

    public CardAccountGUI() {
        cardAccounts = new ArrayList<>();
        listModel = new DefaultListModel<>();
        accountList = new JList<>(listModel);

        cardNumberField = new JTextField(15);
        surnameField = new JTextField(15);
        addressField = new JTextField(15);
        balanceField = new JTextField(15);
        issueDateField = new JTextField(15);
        validityTermField = new JTextField(15);
        creditCheckBox = new JCheckBox("Credit");

        addButton = new JButton("Add");
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.BLACK);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCardAccount();
            }
        });
        saveButton = new JButton("Save");
        saveButton.setBackground(Color.BLUE);
        saveButton.setForeground(Color.BLACK);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Card Number:"));
        inputPanel.add(cardNumberField);
        inputPanel.add(new JLabel("Surname:"));
        inputPanel.add(surnameField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Balance:"));
        inputPanel.add(balanceField);
        inputPanel.add(new JLabel("Issue Date:"));
        inputPanel.add(issueDateField);
        inputPanel.add(new JLabel("Validity Term:"));
        inputPanel.add(validityTermField);
        inputPanel.add(creditCheckBox);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(new JScrollPane(accountList), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Card Account Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(mainPanel);

        setVisible(true);
    }

    private void addCardAccount() {
        String cardNumber = cardNumberField.getText();
        String surname = surnameField.getText();
        String address = addressField.getText();
        double balance = Double.parseDouble(balanceField.getText());
        boolean isCredit = creditCheckBox.isSelected();
        String issueDate = issueDateField.getText();
        int validityTerm = Integer.parseInt(validityTermField.getText());

        CardAccount newAccount = new CardAccount(cardNumber, surname, address, balance, isCredit, issueDate, validityTerm);
        cardAccounts.add(newAccount);
        listModel.addElement(cardNumber + " - " + surname);
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                FileWriter writer = new FileWriter(fileToSave);
                for (CardAccount account : cardAccounts) {
                    writer.write(account.toString() + "\n");
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "Data saved successfully to " + fileToSave.getAbsolutePath(), "Save Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving data to file", "Save Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
