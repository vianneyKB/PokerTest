/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLogicLayer.Poker;

import java.util.*;
/**
 *
 * @author MISHIKO
 */
public class PokerTestAdvance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Create an instance of the poker game
        Poker pG = new Poker();
        //array of values
        ArrayList<String> handValues;
        //Shuffling
        pG.SimulateShuffle();
        // deal the 5 card values
        handValues = pG.DisplayCards(pG.getDealtCards());
        System.out.print("Your Hand: ");
        for (String handValue : handValues) 
        {
            System.out.printf(" %s", handValue);
        }
        
        System.out.print("\nYour Have: ");
        System.out.printf(" %s",pG.EvaluateHand(pG.getDealtCards()));
        //Runs Evaluate hand method which returns highest rank value of current hand as String value
        System.out.println();
       
    }
    
}
