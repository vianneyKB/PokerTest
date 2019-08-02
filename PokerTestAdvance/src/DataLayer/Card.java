/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

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
    //Suit Char values for Display purpose
    static final ArrayList<Character> suitChars = new ArrayList<Character>(Arrays.asList('\u2764', '\u2663', '\u2666','\u2660'));
    private int faceValue, suit;
    public Card(int faceValue, int suit)
    {
        this.faceValue = faceValue;
        this.suit = suit;
    }
    //return face value of card as integer value
    public int getFaceValue() 
    {
        return faceValue;
    }
    //return face value of card as String value
    public String getFaceValueString() 
    {
        return this.faces.get(faceValue);
    }
    //sets the face value of card
    public void setFaceValue(int faceValue) 
    {
        this.faceValue = faceValue;
    }
    /***
     * Method returns the suit of card
     * @return suit of card as integer value 
     */
    public int getSuit() 
    {
        return suit;
    }
     /***
     * Method returns the suit of card
     * @return suit of card as String value 
     */
    public String getSuitString() 
    {
        return this.suits.get(this.suit);
    }
    /***
     * Method sets the suit value
     * @param suit suit value of card
     */
    public void setSuit(int suit) 
    {
        this.suit = suit;
    }
    /***
    * Method overrides the default toString().
    * @return string representation of card object
    */
    public String display() 
    {
        return  this.faces.get(this.faceValue) +  this.suitChars.get(this.suit);
    }
    @Override
    public String toString() {
        return  this.faces.get(this.faceValue) + this.suits.get(this.suit);
    }
}
