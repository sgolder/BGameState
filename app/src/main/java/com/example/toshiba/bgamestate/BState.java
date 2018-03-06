package com.example.toshiba.bgamestate;

import android.widget.EditText;

/**
 * Created by Toshiba on 3/5/2018.
 */

public class BState {

    private BPlayerState[] playerList = new BPlayerState[4];
    private int turn = 0;
    // Phase 0: Begin turn and plant initial beans
    // Phase 1: Turn over two cards and decide to trade or plant
    // Phase 2: Trading
    private int phase;
    //Deck class will include current iteration
    private Deck mainDeck = new Deck();
    private Deck discardDeck = new Deck();
    private Deck tradeDeck = new Deck();

    private EditText testEditText;

    public BState( int playerId ) {
        //Fill player array with new players
        playerList[0] = new BPlayerState("Reeca");
        playerList[1] = new BPlayerState("Alyssa");
        playerList[2] = new BPlayerState("Adam");
        playerList[3] = new BPlayerState("Sarah");

        //Initialize main deck and deal out 5 cards to each player
        mainDeck.addAllCards();
        mainDeck = mainDeck.shuffle();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 5; i++) {
                mainDeck.moveTopCardTo(playerList[j].getHand());
            }
        }

        //Initialize player hands and fields
        for(int i = 0; i<4; i++){
            playerList[i].getHand().setName("Hand");
            Deck[] curFields = playerList[i].getFields();
            for( int j = 0; j<3; j++) {
                curFields[j].setName("Field " + j);
            }
        }
        /*
        //Remove visibility of other player hands
        for(int i = 0; i<playerList.length; i++){
            if(i != playerId){
                playerList[i].getHand().turnHandOver();
            }
        }
        */
        //Setup game to enable use of action methods
        for(int i = 0; i<4; i++){
            //Plant first bean in player's hand
            Deck[] curFields = playerList[i].getFields();
            playerList[i].getHand().moveTopCardTo( curFields[0] );
        }
        //Give playerId 10 coins
        playerList[playerId].setCoins(10);
    }

    public BState(BState orig, int playerId) {
        turn = orig.turn;
        phase = orig.phase;
        mainDeck = new Deck(orig.mainDeck);
        discardDeck = new Deck(orig.discardDeck);
        tradeDeck = new Deck(orig.tradeDeck);
        for(int i = 0; i<4; i++) {
            playerList[i] = new BPlayerState(orig.playerList[i]);
        }

        //Remove visibility of other player hands
        for(int i = 0; i<playerList.length; i++){
            if(i != playerId){
                playerList[i].getHand().turnHandOver();
            }
        }
    }

    //Buy new field
    public boolean buyThirdField(int playerId){
        if( playerList[playerId].getHasThirdField() ){
            return false;
        }
        else {
            if( playerList[playerId].getCoins() >= 3 ){
                playerList[playerId].setHasThirdField(true);
                playerList[playerId].setCoins(
                        playerList[playerId].getCoins() - 3 );
                testEditText.append("Player "+playerId+
                        " has bought a third field\n");
                return true;
            }
            else{
                return false;
            }
        }
    }

    //PlantBean
    public boolean plantBean(int playerId, int fieldId, Card toPlant,
                             Deck origin){
        //Check if player's turn
        if( turn != playerId ){
            return false;
        }
        Deck targetField = new Deck(playerList[playerId].getFields()[fieldId]);
        if ( targetField.size() == 0 ) {
            origin.moveTopCardTo(playerList[playerId].getFields()[fieldId]);
            return true;
        }
        else if (targetField.peekAtTopCard().equals(toPlant)){
            origin.moveTopCardTo(playerList[playerId].getFields()[fieldId]);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String stateString = "";
        for(int i = 0; i<4; i++) {
            stateString = stateString.concat(
                    playerList[i].toString());
        }
        return stateString;
    }

    public BPlayerState[] getPlayerList() { return playerList; }

    public void setEditText( EditText editText) { testEditText = editText; }
}
