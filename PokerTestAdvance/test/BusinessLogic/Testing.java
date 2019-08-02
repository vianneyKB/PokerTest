/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import BusinessLogicLayer.Poker;
import DataLayer.Convert;
import DataLayer.Card;
import java.util.*;
//import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MISHIKO
 */
public class Testing 
{
 @Test
    public void testGetRandomFaceValue() {
        System.out.println("GetRandomFaceValue");
        Poker instance = new Poker();
            
        int result = instance.GetRandomFaceValue();
        
        assertTrue( result > -1 && result < 13 );
    }
    //Test of GetRandomSuit method, of class Poker.
    @Test
    public void testGetRandomSuit() 
    {
        System.out.println("GetRandomSuit");
        Poker instance = new Poker();
        int result = instance.GetRandomSuit();
        assertTrue( result > -1 && result < 5 );
    }
    //Test of Deal method, of class Poker.
    @Test
    public void testDeal() {
        System.out.println("Deal");
        Poker instance = new Poker();        
        ArrayList<Card> result = instance.Deal();      
        assertNotNull(result);
        assertEquals(5,result.size());
    }
    // Test of DisplayCards method, of class Poker.
    @Test
    public void testDisplayCards() 
    {
        System.out.println("DisplayCards");
        Poker instance = new Poker();
        ArrayList<Card> dealtCards = instance.Deal();
        ArrayList<String> result = instance.DisplayCards(dealtCards);
        assertNotNull(result);
        assertEquals(5,result.size());
       
    }
    //Test of EvaluateHand method, of class Poker.
    @Test
    public void testEvaluateHand() 
    {
        System.out.println("EvaluateHand");
        Poker instance = new Poker();
        ArrayList<Card> cardInputs = instance.Deal();
        String result = instance.EvaluateHand(cardInputs);
        assertNotNull(result);
    }
    
    
    /**
     * Test to ensure JSON convert translation is working. 
     */
     @Test
    public void testJSONConvert() {
        System.out.println("Test JSON Converter");
        Poker instance = new Poker();
        
        ArrayList<Card> dealtCards = instance.Deal();
        ArrayList<String> result = instance.DisplayCards(dealtCards);
        
        assertNotNull(new Convert(result));
        
        String st = "Full House";
        
         assertEquals("Full House", st);
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
