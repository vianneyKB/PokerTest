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
public class Service 
{
    public String findHandCategory(ArrayList<String> inputCard)
    {
        ArrayList<Card> cardsList = convertArrayToCards(inputCard);
        Hand handObj = new Hand(inputCard, cardsList);
        handObj.findCategory();
        return getHandCategoryString(handObj.getHandCategory(), handObj.getTieBreakers());
    }
    public ArrayList<String> findWinnerFromHands(ArrayList < ArrayList <String>> inputHand)
    {
        ArrayList<Hand> handsList = new ArrayList<Hand>();
        for(ArrayList<String> cardArr : inputHand)
        {
            ArrayList<Card> cardsList = convertArrayToCards(cardArr);
            Hand handObj = new Hand(cardArr, cardsList);
            handObj.findCategory();

            handsList.add(handObj);
        }
        //winner
        Hand winnerHand = handsList.get(0);
        for (int i = 0; i < handsList.size(); i++) 
        {
            if (winnerHand.compareHand(handsList.get(i)) < 1) 
            {
                winnerHand = handsList.get(i);
            }
        }
        //display hand category
        return winnerHand.getInputCards();
    }
    //----------------------------------
    /***
    * Method takes the list of cards (string representation) and returns the best 5
    * cards
    * @param inputCards list of cards (5 or more)
    * @return the best 5 cards
    */ 
    public ArrayList<String> find5BestCards(ArrayList<String> inputCards)
    {
        //generate all 5 card possible combinations
        ArrayList<ArrayList<String>> fiveCardsList = find5CardsCombinations(inputCards);
        //find winner among them
        return findWinnerFromHands(fiveCardsList);
    }
    //---------------------------------
    //Method converts list<string> representation of cards to list<card> for internal use
    private ArrayList<Card> convertArrayToCards(ArrayList<String> inputCards)
    {
        ArrayList<Card> cardsList = new ArrayList<Card>();
        for(String cardStr : inputCards){
            String face = cardStr.substring(0, cardStr.length()-1);
            String suit = cardStr.substring(cardStr.length()-1);
            cardsList.add(new Card(Card.faces.indexOf(face), Card.suits.indexOf(suit)));
        }
        return cardsList;
    }
    //-----------------------------------------
    /***
    * Method return the string representation of hand
    * @param handCategory the integer representing the category of hand
    * @param tieBreakers the integer array of with cards ordered based on their rank
    * @return string representation of the hand category
    */
    private String getHandCategoryString(int handCategory, int[] tieBreakers){
        StringBuilder sb = new StringBuilder();
        switch (handCategory){
            case 9: sb.append("Royal Flush");
                break;
            case 8: sb.append("Straight Flush - Cards: ");
                for(int faceValue : tieBreakers){
                    if(faceValue == 14){
                        faceValue = 1;
                    }
                    sb.append(Card.faces.get(faceValue-1) + " ");
                }
                break;
            case 7: sb.append("Four of a Kind - Cards: ");
                sb.append("Four of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                sb.append(" with kicker " + Card.faces.get((tieBreakers[1] == 14 ? 1 : tieBreakers[1])-1));
                break;

            case 6: sb.append("Full House - Cards: ");
                sb.append("Three of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                sb.append(" and two of " + Card.faces.get((tieBreakers[1] == 14 ? 1 : tieBreakers[1])-1));
                break;
            case 5: sb.append("Flush - Cards: ");
                for(int faceValue : tieBreakers){
                    if(faceValue == 14){
                        faceValue = 1;
                    }
                    sb.append(Card.faces.get(faceValue-1) + " ");
                }
                break;
            case 4: sb.append("Straight - Cards: ");
                for(int faceValue : tieBreakers){
                    if(faceValue == 14){
                        faceValue = 1;
                    }
                    sb.append(Card.faces.get(faceValue-1) + " ");
                }
                break;
            case 3: sb.append("Three of a Kind - Cards: ");
                sb.append("Three of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                sb.append(" with kickers " + Card.faces.get((tieBreakers[1] == 14 ? 1 : tieBreakers[1])-1));
                sb.append(" and " + Card.faces.get((tieBreakers[2] == 14 ? 1 : tieBreakers[2])-1));
                break;
            case 2: sb.append("Two Pair - Cards: ");
                sb.append("Two of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                sb.append(" and two of " + Card.faces.get((tieBreakers[1] == 14 ? 1 : tieBreakers[1])-1));
                sb.append(" with kicker " + Card.faces.get((tieBreakers[2] == 14 ? 1 : tieBreakers[2])-1));
                break;
            case 1: sb.append("One Pair - Cards: ");
                sb.append("Pair of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                sb.append(" with kickers ");
                for(int i = 1; i < tieBreakers.length; i++){
                    if(tieBreakers[i] == 0)break;

                    int faceValue = tieBreakers[i];
                    if(tieBreakers[i] == 14){
                        faceValue = 1;
                    }
                    sb.append(Card.faces.get(faceValue-1) + " ");
                }
                break;
            case 0: sb.append("High Card - Cards: ");
                for(int faceValue : tieBreakers){
                    if(faceValue == 14){
                        faceValue = 1;
                    }
                    sb.append(Card.faces.get(faceValue-1) + " ");
                }
                break;
        }
        return sb.toString();
    }
    //-----------------------------
    /***
    * Method displays the string representation of hand on console
    * @param handCategory the integer representing the category of hand
    * @param tieBreakers the integer array of with cards ordered based on their rank
    */
    private void displayHandCategory(int handCategory, int[] tieBreakers){
        switch (handCategory){
            case 9: System.out.println("Royal Flush");
                break;

            case 8: System.out.print("Straight Flush - Cards: ");
                for(int faceValue : tieBreakers){
                    if(faceValue == 14){
                        faceValue = 1;
                    }
                    System.out.print(Card.faces.get(faceValue-1) + " ");
                }
                break;
            case 7: System.out.print("Four of a Kind - Cards: ");
                System.out.print("Four of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                System.out.print(" with kicker " + Card.faces.get((tieBreakers[1] == 14 ? 1 : tieBreakers[1])-1));
                break;
            case 6: System.out.print("Full House - Cards: ");
                System.out.print("Three of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                System.out.print(" and two of " + Card.faces.get((tieBreakers[1] == 14 ? 1 : tieBreakers[1])-1));
                break;

            case 5: System.out.print("Flush - Cards: ");
                for(int faceValue : tieBreakers){
                    if(faceValue == 14){
                        faceValue = 1;
                    }
                    System.out.print(Card.faces.get(faceValue-1) + " ");
                }
                break;
            case 4: System.out.print("Straight - Cards: ");
                for(int faceValue : tieBreakers){
                    if(faceValue == 14){
                        faceValue = 1;
                    }
                    System.out.print(Card.faces.get(faceValue-1) + " ");
                }
                break;
            case 3: System.out.print("Three of a Kind - Cards: ");
                System.out.print("Three of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                System.out.print(" with kickers " + Card.faces.get((tieBreakers[1] == 14 ? 1 : tieBreakers[1])-1));
                System.out.print(" and " + Card.faces.get((tieBreakers[2] == 14 ? 1 : tieBreakers[2])-1));
                break;
            case 2: System.out.print("Two Pair - Cards: ");
                System.out.print("Two of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                System.out.print(" and two of " + Card.faces.get((tieBreakers[1] == 14 ? 1 : tieBreakers[1])-1));
                System.out.print(" with kicker " + Card.faces.get((tieBreakers[2] == 14 ? 1 : tieBreakers[2])-1));
                break;
            case 1: System.out.print("One Pair - Cards: ");
                System.out.print("Pair of " + Card.faces.get((tieBreakers[0] == 14 ? 1 : tieBreakers[0])-1));
                System.out.print(" with kickers ");
                for(int i = 1; i < tieBreakers.length; i++){
                    if(tieBreakers[i] == 0)break;

                    int faceValue = tieBreakers[i];
                    if(tieBreakers[i] == 14){
                        faceValue = 1;
                    }
                    System.out.print(Card.faces.get(faceValue-1) + " ");
                }
                break;
            case 0: System.out.print("High Card - Cards: ");
                for(int faceValue : tieBreakers)
                {
                    if(faceValue == 14)
                    {
                        faceValue = 1;
                    }
                    System.out.print(Card.faces.get(faceValue-1) + " ");
                }
                break;
        }
    }
    //--------------------------------------------
    /***
     * Method generates all possible 5 card combinations
     * @param inputCards takes in input cards (string representation)
     * @return all the possible 5 card combinations possible
     */
     private ArrayList<ArrayList<String>> find5CardsCombinations(ArrayList<String> inputCards)
     {
        ArrayList<ArrayList<String>> combList = new ArrayList<ArrayList<String>>();
        backtrack(combList, new ArrayList<String>(), inputCards, inputCards.size(), 5,0);
        return combList;
     }
     //-------------------------------------
     /***
     * Helper method for backtracking and generate all possible combinations
     * @param combList the result combinations list
     * @param templist temporary currently being generated list
     * @param inputCards input cards (string representation)
     * @param n number of cards in list
     * @param k 5 (since we need to find 5 cards combination)
     * @param start index/pointer in input
     */
     private void backtrack(ArrayList<ArrayList<String>> combList, ArrayList<String> templist, List<String> inputCards, int n, int k, int start)
     {
        if(templist.size() == k)
            combList.add(new ArrayList<String>(templist));
        else if(templist.size() > k)return;
        for(int i = start; i < n; i++)
        {
            templist.add(inputCards.get(i));
            backtrack(combList, templist, inputCards, n, k,i+1);
            templist.remove(templist.size()-1);
        }
     }
}
