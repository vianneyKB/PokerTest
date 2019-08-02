/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataLayer.CardGame;
import DataLayer.Convert;
import DataLayer.Card;
import Contracter.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MISHIKO
 */
public class Poker extends CardGame
{
    public Poker() 
    {
       this.dealtCards = Deal();
    }
    // getting a random Face Value for a Card 
    @Override
    public int GetRandomFaceValue() 
    {
        return new Random().nextInt(13);
    }
   //Abstract Method for getting a random Suit for a Card
    @Override
    public int GetRandomSuit() 
    {
        return new Random().nextInt(4);
    }

    
    //return an array of 5 Card Objects to function as a player's hand
    @Override
    public ArrayList<Card> Deal() 
    {
        int faceValue,suit;
        ArrayList<Card> cardsDealt = new ArrayList<>();
        for (int i = 0; i < 5; i++) 
        {
            boolean containsCard = true;
            while(containsCard)
            {
                faceValue = GetRandomFaceValue();
                suit =  GetRandomSuit();
                Card card = new Card(faceValue,suit);
                if (cardsDealt.contains(card)==false) 
                {
                    containsCard = false;
                    cardsDealt.add(card);
                }
            }
        }
        return cardsDealt;
    }
    //displays a player's hand of Cards as Face Values and Suit Symbols
    @Override
    public ArrayList<String> DisplayCards(ArrayList<Card> dealtCards)
    {
        ArrayList<String> cardValues = new ArrayList<>();
        for (Card dealtCard : dealtCards) 
        {
            cardValues.add(dealtCard.display());
        }
        return cardValues;
    }
    //evaluating the player's based on the rankings of Poker.
    @Override
    public String EvaluateHand(ArrayList<Card> cardInputs) 
    {
        ArrayList<String> hand = new ArrayList<>();
        for (Card card : cardInputs) 
        {
            hand.add(card.toString());
        }
        Service evaluater = new Service();
        String handRank;
        try 
        {
            handRank = evaluater.findHandCategory(hand).split("-")[0];
        } 
        catch (Exception e) 
        {
            System.out.printf(e.getMessage());
            handRank = " [Error while evaluating hand, please try Deal Again!!]";
        }
        return handRank;
    }

    //Method to create shuffling simulation functionality
    @Override
    public void SimulateShuffle() 
    {
        for (int i = 0; i < 3; i++) 
        {
            System.out.print("Shuffling....");
            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Poker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println();
    }
    //Method to translate String value to a JSON format to be able to easily send to a frontend. 
    @Override
    public String ToJSON(String item) 
    {
        return new Convert(item).ConvertToJSON();
    }
    //Method to translate String ArrayList to a JSON format to be able to easily send to a frontend
    @Override
    public String ToJSON(ArrayList<String> items) 
    {
        return new Convert(items).ConvertToJSON();
    }
}