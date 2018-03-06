package com.example.toshiba.bgamestate;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Toshiba on 3/5/2018.
 */

public class Deck {
    private String name;
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
    }

    public Deck(Deck orig) {
        name = orig.name;
        cards = new ArrayList<Card>();
        for (Card c: orig.cards) {
            cards.add(c);
        }
    }

    /**
     * add a card to the top of a deck
     *
     * @param c
     * 		the card to add
     */
    public void add(Card c) {
        // synchronize so that the underlying ArrayList is not accessed
        // inconsistently
        synchronized(this.cards) {
            cards.add(c);
        }
    }

    public int size() {
        return cards.size();
    }

    /**
     * Moves the top card the current deck to the top of another; does nothing if
     * the first deck is empty
     *
     * @param targetDeck
     * 		the deck to which the card should be moved
     */
    public void moveTopCardTo(Deck targetDeck) {

        // will hold the card
        Card c = null;

        // size of the first deck
        int size;
        size = this.size();
        if (size > 0) {
            c = cards.remove(cards.size()-1);
            Log.i("Deck", "Card to move: "+c.toString());
            targetDeck.add(c);
        }
    }

    public void addAllCards() {
        for(int i = 0; i <6; i++) {
            cards.add(new Card("Garden Bean"));
        }
        for(int i = 0; i < 8; i++) {
            cards.add(new Card("Red Bean"));
        }
        for(int i = 0; i < 10; i++) {
            cards.add(new Card("Black-Eyed Bean"));
        }
        for(int i = 0; i < 12; i++) {
            cards.add(new Card("Soy Bean"));
        }
        for(int i = 0; i < 14; i++) {
            cards.add(new Card("Green Bean"));
        }
        for(int i = 0; i < 16; i++) {
            cards.add(new Card("Stink Bean"));
        }
        for(int i = 0; i < 18; i++) {
            cards.add(new Card("Chili Bean"));
        }
        for(int i = 0; i < 20; i++) {
            cards.add(new Card("Blue Bean"));
        }
    }

    public Deck shuffle() {
        // synchronize so that we don't have someone trying to modify the
        // deck as we're modifying it
        synchronized (this.cards) {
            // go through a loop that randomly rearranges the cards
            for (int i = cards.size(); i > 1; i--) {
                int spot = (int)(i*Math.random());
                Card temp = cards.get(spot);
                cards.set(spot, cards.get(i-1));
                cards.set(i-1, temp);
            }
        }

        // return the deck
        return this;
    }

    public void turnHandOver() {
        int oldSize = size();
        cards.clear();
        for(int i = 0; i<oldSize; i++ ){
            cards.add(new Card("Card Back"));
        }
    }

    /**
     * @return
     * 		the top card in the deck, without removing it; null
     * 		if the deck was empty
     */
    public Card peekAtTopCard() {
        synchronized (this.cards) {
            if (cards.isEmpty()) return null;
            return cards.get(cards.size()-1);
        }
    }

    @Override
    public String toString(){
        String deckString = name+" ("+size()+"): ";
        for (Card c: cards ) {
            deckString = deckString.concat(c.toString()+", ");
        }
        deckString = deckString.concat("\n");
        return deckString;
    }

    public void setName (String newName) {name = newName;}
}
