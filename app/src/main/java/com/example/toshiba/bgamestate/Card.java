package com.example.toshiba.bgamestate;

/**
 * Created by Toshiba on 3/6/2018.
 */

public class Card {
    private String name;

    public Card(String cardName) {
        name = cardName;
    }

    public Card(Card orig) {
        name = orig.name;
    }

    @Override
    public String toString(){
        return name;
    }

    public void setName (String newName) {name = newName;}
}
