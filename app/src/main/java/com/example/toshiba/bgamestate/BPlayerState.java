package com.example.toshiba.bgamestate;

/**
 * Created by Toshiba on 3/5/2018.
 */

public class BPlayerState {
    private String name;
    private int coins;
    // Offer 0: No choice has been made about trading
    // Offer 1: Chose not to trade for this round
    // Offer 2: Trade being offered
    private int makeOffer;
    private boolean hasThirdField;
    private Deck hand;
    private Deck[] fields = new Deck[3];

    public BPlayerState(String playerName) {
        name = playerName;
        hand = new Deck();
        for(int i = 0; i<3; i++){
            fields[i] = new Deck();
        }
        makeOffer = 0;
        hasThirdField = false;
    }

    public BPlayerState(BPlayerState orig) {
        name = orig.name;
        coins = orig.coins;
        makeOffer = orig.makeOffer;
        hasThirdField = orig.hasThirdField;

        hand = new Deck(orig.hand);
        for(int i = 0; i<3; i++){
            fields[i] = new Deck(orig.fields[i]);
        }
    }

    @Override
    public String toString(){
        String playerStr = "\n";
        playerStr = playerStr.concat(name+"\n");
        playerStr = playerStr.concat("Owns third field: "+
                hasThirdField+"\n");
        playerStr = playerStr.concat(hand.toString());
        for (Deck field : fields) {
            playerStr = playerStr.concat(field.toString());
        }
        return playerStr;
    }

    //Getters
    public String getName() { return name; }
    public int getCoins() {return coins;}
    public boolean getHasThirdField() {return hasThirdField;}
    public Deck getHand() { return hand; }
    public Deck[] getFields() { return fields; }

    //Setters
    public void setName (String newName) {name = newName;}
    public void setCoins (int newCoins) {coins = newCoins;}
    public void setHasThirdField (boolean newHasThirdField) {
        hasThirdField = newHasThirdField;
    }
}
