import cs1.Keyboard;

public class Tonk {
    public static String x;
    public static Player[] listOfPlayers;
    public static int numOfPlayers;
    //methods

    public static void setup() {
	DrawDeck.shuffle();
	listOfPlayers = new Player[numOfPlayers];
	for (int i = 0; i < numOfPlayers; i++) {
	    listOfPlayers[i] = new Player();
	    listOfPlayers[i].firstHand();
	    listOfPlayers[i].sumOfCards();
	}        
    }

    public static String knock() {
	int[] playerSums = new int[numOfPlayers];
	int winningValue = 0;
	String winningPlayers = "";
	for (int i = 0; i < numOfPlayers; i++) {
	    playerSums[i] = listOfPlayers[i].handTotal;
	}
	if (numOfPlayers == 2) {
	    winningValue = Math.min(playerSums[0], playerSums[1]);
	}
	if (numOfPlayers == 3) {
	    winningValue = Math.min(Math.min(playerSums[0], playerSums[1]), playerSums[2]);
	}
	if (numOfPlayers == 4) {
	    winningValue = Math.min(Math.min(Math.min(playerSums[0], playerSums[1]), playerSums[2]), playerSums[3]);
	}
	for (int i = 0 ; i < playerSums.length ; i++) {
	    if (playerSums[i] == winningValue) {
		if (winningPlayers.equals("")) {
		    winningPlayers += "Player " + (i + 1);
		}
		else {
		    winningPlayers += " and " + "Player " + (i + 1);
		}
	    }
	}
	return winningPlayers + " win(s) with a score of " + winningValue;
    }

	
    public static void playTurn(Player playerx) {
	System.out.println("Your hand: ");
	System.out.println(playerx);
	playerx.sumOfCards();
	System.out.println("Your hand has a sum of " + playerx.handTotal);
	System.out.println("\nThe current card in the discard pile is ");
	if (DiscardDeck.topCard == null) System.out.println("Nothing!");
	else System.out.println(DiscardDeck.topCard);

	if (DiscardDeck.topCard == null) {
	    playerx.drawFromDeck();
	    System.out.println("You drew " + playerx.drawCard);
	}
	else {

	    System.out.println("Would you like to knock? (y/n)");
	    x = Keyboard.readString();

	    if (x.equals("y")) {
		System.out.println(knock());
		x = "quit";
		return; 
	    }



	    
	    System.out.println("Would you like to draw from the deck (d) or pile (p)?");

	    x = Keyboard.readString();
	    if (x.equals("d")) playerx.drawFromDeck();
	    else if (x.equals("p")) playerx.drawFromPile();
  
	    System.out.println("You drew " + playerx.drawCard);
	}

	/*	System.out.println("Do you want to play any combos (y/n)?");
	x = Keyboard.readString();
	if (x.equals("y")) {
	    System.out.println("Which cards would you like to use? (type \"end\" to stop)");
	    while (! x.equals("end")) {
		x = Keyboard.readString();
		playerx.combo = true;
		playerx.discard(x);
		playerx.combo = false;
	    }
		    
	    }*/
		
	    
	
	    

	System.out.println("Choose a card to discard: ");
	System.out.println(playerx);
	x = Keyboard.readString();
	playerx.discard(x);
	DiscardDeck.setTopCard(x);
	System.out.println("You discarded " + playerx.discardCard);

	playerx.updateHand();
	System.out.println("-------------------------");
    }
    public static void main(String[] args) {
	
	System.out.println("Welcome to Tonk!");
	System.out.println("How many players? (2-4)");
	String x = Keyboard.readString();
	numOfPlayers = Integer.parseInt(x);

	setup();
	while(! x.equals("quit")) {
	    for (int i = 0; i < listOfPlayers.length; i++) {
		System.out.println("-------------------------");
		System.out.println("Player" + (i + 1) + "'s " + "Turn\n");
		playTurn(listOfPlayers[i]);
	    }
	}
       
	    
	    
	  

    }
    
}
