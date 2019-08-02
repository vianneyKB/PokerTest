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
public abstract class CardGame
{
    protected ArrayList<Card> dealtCards;
    public ArrayList<Card> getDealtCards() 
    {
        return dealtCards;
    }
    //Abstract Method for getting a random Face Value for a Card 
    public abstract int GetRandomFaceValue();
    //Abstract Method for getting a random Suit for a Card 
    public abstract int GetRandomSuit();
    //Abstract Method that returns an array of Card Objects to function as a player's hand
    public abstract ArrayList<Card> Deal();
    //Abstract Method that displays a player's hand of Cards as Face Values and Suit Symbols (e.g. 4♠ 2❤ 10♦ J♣ 6♦)
    public abstract ArrayList<String> DisplayCards( ArrayList<Card> dealtCards);
    //Abstract Method for evaluating the player's based on the rankings of the Card Game being played.
    public abstract String EvaluateHand(ArrayList<Card> cardInputs);
    //Abstract Method to create shuffling simulation functionality
    public abstract void SimulateShuffle();
    //Abstract Method to create shuffling simulation functionality
    public abstract String ToJSON(String item);
    public abstract String ToJSON(ArrayList<String> items);
}
