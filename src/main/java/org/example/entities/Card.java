package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table (name="cards")
public class Card {
    @Id
    @Column (name="id")
    private int id;

    @Column (name="name")
    private String name;

    @Column (name="cardnumber")
    private String cardnumber;

    @Column (name="balance")
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardnumber='" + cardnumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
