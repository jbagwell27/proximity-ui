package code.proximityui;

import code.proximityui.data.Card;

import java.util.ArrayList;

public class ProximityModel {

    private ArrayList<String> cardAvailableNames;
    private ArrayList<Card> cardSelectedArrayList;
    private Card currentCard;
    private int currentIndex;

    public ProximityModel() {
        cardAvailableNames = new ArrayList<>();
        cardSelectedArrayList = new ArrayList<>();
    }

    public ArrayList<String> getCardAvailableNames() {
        return cardAvailableNames;
    }

    public void setCardAvailableNames(ArrayList<String> cardAvailableNames) {
        this.cardAvailableNames = cardAvailableNames;
    }

    public ArrayList<Card> getCardSelectedArrayList() {
        return cardSelectedArrayList;
    }

    public void addCardSelectedItem(String cardName){
        cardSelectedArrayList.add(new Card(cardName));
    }

    public void setCurrentCard(int index) {
        currentIndex = index;
        currentCard = getCardSelectedArrayList().get(index);
    }

    public Card getCurrentCard(){
        return currentCard;
    }

    public void updateSelectedCard() {
        getCardSelectedArrayList().set(currentIndex, currentCard);
    }
}
