/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author MISHIKO
 */
public class CardTest 
{
    //testing the class Test
    @Test
    public void testCard1(){
        System.out.println("Card Test 1");
        Card cardObj = new Card(0,2);
        Assert.assertEquals(0, cardObj.getFaceValue());
        Assert.assertEquals(2, cardObj.getSuit());

        cardObj.setFaceValue(4);
        Assert.assertEquals(4, cardObj.getFaceValue());

        cardObj.setSuit(0);
        Assert.assertEquals(0, cardObj.getSuit());

        Assert.assertEquals("5H", cardObj.toString());
    }
    @Test
    public void testCard2(){
        System.out.println("Card Test 2");
        Card cardObj = new Card(12,3);
        Assert.assertEquals(12, cardObj.getFaceValue());
        Assert.assertEquals(3, cardObj.getSuit());

        cardObj.setFaceValue(10);
        Assert.assertEquals(10, cardObj.getFaceValue());
        Assert.assertEquals(3, cardObj.getSuit());
        
        cardObj.setSuit(1);
        Assert.assertEquals(1, cardObj.getSuit());
        Assert.assertEquals(10, cardObj.getFaceValue());
        
        Assert.assertEquals( "J"+Character.toString('\u2663'), cardObj.display());
    }

}
