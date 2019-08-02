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
public class Hand 
{
    private ArrayList<Card> cards;
    private int[] ranks = new int[14];
    private int[] orderedKickers;
    private boolean isFlush;
    private boolean isStraight;

    private int handCategory;
    private int[] tieBreakers;
    private ArrayList<String> inputCards;

    public Hand(ArrayList<String> inputCards, ArrayList<Card> cardsList){
        this.inputCards = inputCards;
        this.cards = cardsList;
        this.orderedKickers = new int[this.cards.size()];
        this.tieBreakers = new int[this.cards.size()];
    }

    /***
     * Method finds the hand category of hand.
     * Category is one of:
     * 9 - royal flush
     * 8 - straight flush
     * 7 - four of a kind
     * 6 - full house
     * 5 - flush
     * 4 - straight
     * 3 - three of a kind
     * 2 - two pair
     * 1 - one pair
     * 0 - high card
     * Ace has highest rank i.e 14. Cards 2-10,J,Q and K have ranks from 2-13
     */
    public void findCategory(){
        //order the cards based on rank and count number of each cards
        calculateRanks();

        //order the input cards based on their rank/face value used during
        //tie breaking
        calculateOrderForKickers();

        //check if cards are or same suit
        isFlush = checkIfFlush();

        //check is cards are consecutive
        isStraight = checkIfStraight();

        //find the grouping of cards if any i.e find number of pairs in hand
        int highestSameNum = 1, highestSameRank = 0,  deciderSameNum = 1, deciderSameRank = 0;
        if(ranks[1] > highestSameNum){
            highestSameNum = ranks[1];
            highestSameRank = 14;
        }
        for(int i = 13; i >=2; i--){
            if(ranks[i] > highestSameNum){
                deciderSameNum = highestSameNum;
                deciderSameRank = highestSameRank;

                highestSameNum = ranks[i];
                highestSameRank = i;
            }else{
                if(ranks[i] > deciderSameNum){
                    deciderSameNum = ranks[i];
                    deciderSameRank = i;
                }
            }
        }

        //decide category and kickers for hand
        if(checkRoyalFlush()){ //royal flush
            handCategory = 9;
        }else if(isStraight && isFlush){ //straight flush
            handCategory = 8;
            tieBreakers[0] = orderedKickers[0];
            tieBreakers[1] = orderedKickers[1];
            tieBreakers[2] = orderedKickers[2];
            tieBreakers[3] = orderedKickers[3];
            tieBreakers[4] = orderedKickers[4];
        }else if(highestSameNum == 4){ //four of a kind
            handCategory = 7;
            tieBreakers[0] = highestSameRank;
            tieBreakers[1] = orderedKickers[0];
        }else if(highestSameNum == 3 && deciderSameNum == 2){ //full house
            handCategory = 6;
            tieBreakers[0] = highestSameRank;
            tieBreakers[1] = deciderSameRank;
        }else if(isFlush){ //flush
            handCategory = 5;
            tieBreakers[0] = orderedKickers[0];
            tieBreakers[1] = orderedKickers[1];
            tieBreakers[2] = orderedKickers[2];
            tieBreakers[3] = orderedKickers[3];
            tieBreakers[4] = orderedKickers[4];
        }else if(isStraight){ //straight
            handCategory = 4;
            tieBreakers[0] = orderedKickers[0];
            tieBreakers[1] = orderedKickers[1];
            tieBreakers[2] = orderedKickers[2];
            tieBreakers[3] = orderedKickers[3];
            tieBreakers[4] = orderedKickers[4];
        }else if(highestSameNum == 3){ //three of a kind
            handCategory = 3;
            tieBreakers[0] = highestSameRank;
            tieBreakers[1] = orderedKickers[0];
            tieBreakers[2] = orderedKickers[1];
        }else if(highestSameNum == 2 && deciderSameNum == 2){ //two pair
            handCategory = 2;
            tieBreakers[0] = highestSameRank>deciderSameRank ? highestSameRank : deciderSameRank;
            tieBreakers[1] = highestSameRank<deciderSameRank ? highestSameRank : deciderSameRank;
            tieBreakers[2] = orderedKickers[0];
        }else if(highestSameNum == 2){ //one pair
            handCategory = 1;
            tieBreakers[0] = highestSameRank;
            tieBreakers[1] = orderedKickers[0];
            tieBreakers[2] = orderedKickers[1];
            tieBreakers[3] = orderedKickers[2];
        }else{ //high card
            handCategory = 0;
            tieBreakers[0] = orderedKickers[0];
            tieBreakers[1] = orderedKickers[1];
            tieBreakers[2] = orderedKickers[2];
            tieBreakers[3] = orderedKickers[3];
            tieBreakers[4] = orderedKickers[4];
        }
    }

    /***
     * Method return the int value representing the hand category
     * @return integer value of hand category
     */
    public int getHandCategory() {
        return handCategory;
    }

    /***
     * Method returns the tie breakers array
     * @return the tie breakers array
     */
    public int[] getTieBreakers() {
        return tieBreakers;
    }

    /***
     * Method returns the input cards in string representation
     * @return the string representation of cards
     */
    public ArrayList<String> getInputCards() {
        return inputCards;
    }

    /***
     * Method counts the number of same card in input
     */
    private void calculateRanks(){
        for(Card card : cards){
            ranks[card.getFaceValue()+1]++;
        }
    }

    /***
     * Method checks if cards are of same suit or not
     * @return true if cards are of same suit and hence have flush category
     */
    private boolean checkIfFlush(){
        for(int i = 0; i < cards.size()-1; i++){
            if(cards.get(i).getSuit() != cards.get(i+1).getSuit()){
                return false;
            }
        }

        return true;
    }

    /***
     * Method checks if hand is in straight category
     * @return true if cards are consecutive
     */
    private boolean checkIfStraight(){
        for(int i = 1; i <= 9; i++){
            if(ranks[i] == 1
                    && ranks[i+1] == 1
                    && ranks[i+2] == 1
                    && ranks[i+3] == 1
                    && ranks[i+4] == 1){
                return true;
            }
        }

        return false;
    }

    /***
     * Method orders the cards whose count is 1 in hand based on rank (kickers or tie breakers)
     */
    private void calculateOrderForKickers(){
        int index = 0;
        if(ranks[1] == 1){
            orderedKickers[index++] = 14;
        }

        for(int i = 13; i >=2; i--){
            if(ranks[i] == 1){
                orderedKickers[index++] = i;
            }
        }
    }

    /***
     * Method checks if hand is royal flush
     * @return true if hand is royal flush
     */
    private boolean checkRoyalFlush(){
        if(isFlush
                && ranks[10] == 1
                && ranks[11] == 1
                && ranks[12] == 1
                && ranks[13] ==1){
            return true;
        }

        return false;
    }

    /***
     * Method compares this hand to given hand (compares cards based on category
     * and rank
     * @param hand other hand
     * @return int value indicating which hand has higher category
     */
    int compareHand(Hand hand){
        if(this.handCategory > hand.handCategory)return 1;

        if(this.handCategory < hand.handCategory)return -1;

        for(int i = 0; i < this.tieBreakers.length; i++){
            if(this.tieBreakers[i] > hand.tieBreakers[i]){
                return 1;
            }else{
                if(this.tieBreakers[i] < hand.tieBreakers[i]){
                    return -1;
                }
            }
        }

        return 0;
    }
}
