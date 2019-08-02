/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contracter;

import java.util.*;

/**
 *
 * @author MISHIKO
 */
public class Card 
{
    //single deck face values and suit values
    static final ArrayList<String> suits = new ArrayList<String>(Arrays.asList("H", "C", "D", "S"));
    static final ArrayList<String> faces = new ArrayList<String>(Arrays.asList("A", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "J", "Q", "K"));

    private int faceValue, suit;

    public Card(int faceValue, int suit){
        this.faceValue = faceValue;
        this.suit = suit;
    }

    /***
     * Method returns the face value
     * @return face value of card
     */
    public int getFaceValue() {
        return faceValue;
    }

    /***
     * Method sets the face value of card
     * @param faceValue face value of card
     */
    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    /***
     * Method returns the suit of card
     * @return suit of card
     */
    public int getSuit() {
        return suit;
    }

    /***
     * Method sets the suit value
     * @param suit suit value of card
     */
    public void setSuit(int suit) {
        this.suit = suit;
    }

    /***
     * Method overrides the default toString().
     * @return string representation of card object
     */
    @Override
    public String toString() {
        return "Card: " + this.faces.get(faceValue) + " of " + this.suits.get(this.suit);
    }
}
