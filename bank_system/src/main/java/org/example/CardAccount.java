package org.example;

import java.util.ArrayList;

public class CardAccount {
    private String cardNumber;
    private String clientSurname;
    private String clientAddress;
    private double accountBalance;
    private boolean isCredit;
    private String issueDate;
    private int validityTerm;

    public CardAccount(String cardNumber, String clientSurname, String clientAddress,
                       double accountBalance, boolean isCredit, String issueDate, int validityTerm) {
        this.cardNumber = cardNumber;
        this.clientSurname = clientSurname;
        this.clientAddress = clientAddress;
        this.accountBalance = accountBalance;
        this.isCredit = isCredit;
        this.issueDate = issueDate;
        this.validityTerm = validityTerm;
    }

    public void printInfo() {
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Client Surname: " + clientSurname);
        System.out.println("Client Address: " + clientAddress);
        System.out.println("Account Balance: " + accountBalance);
        System.out.println("Is Credit: " + (isCredit ? "Yes" : "No"));
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Validity Term: " + validityTerm);
    }

    public boolean isCreditCard() {
        return isCredit;
    }

    public boolean isDepositCard() {
        return !isCredit;
    }

    public static CardAccount findCardByNumber(ArrayList<CardAccount> accounts, String cardNumber) {
        for (CardAccount account : accounts) {
            if (account.cardNumber.equals(cardNumber)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Card Number: " + cardNumber +
                ", Surname: " + clientSurname +
                ", Address: " + clientAddress +
                ", Balance: " + accountBalance +
                ", Is Credit: " + (isCredit ? "Yes" : "No") +
                ", Issue Date: " + issueDate +
                ", Validity Term: " + validityTerm;
    }

}

