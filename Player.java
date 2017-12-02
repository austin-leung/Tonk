public class Player {

    //fields
    String[] hand = new String[5];
    int handTotal;
    int turnsToKnock = 1;
    String drawCard;
    String discardCard;

    

    //methods

    public void drawFromDeck() {
	drawCard = DrawDeck.deckList[DrawDeck.topCard];
	DrawDeck.topCard++;
        
    }

    public void sumOfCards() {
	int sum = 0;
	for (String card : hand){
	    String num = card.substring(0,1);
	    if (num.equals("A")) sum += 1;
	    else if (num.equals("T") || num.equals("J") || num.equals("Q") || num.equals("K")) sum += 10;
	    else sum += Integer.parseInt(num);
	}
	handTotal = sum;
    }

    public void drawFromPile() {
	drawCard = DiscardDeck.topCard;
    }

    public void discard(String card) {
	discardCard = card;
    }

    public void updateHand() {
	for (int i = 0; i < hand.length; i++) 
	    if (hand[i].equals( discardCard))
		hand[i] = drawCard;
    }

    public void firstHand() {
	for (int i = 0; i < hand.length; i++) {
	    hand[i] = DrawDeck.deckList[DrawDeck.topCard + i];
	}
	DrawDeck.topCard += 5;
    }

    public String toString() {
	String result = "";
	for (int i = 0; i < hand.length; i++)
	    result += hand[i] + " " ;
	return result;
    }

    
}
