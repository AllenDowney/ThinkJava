/**
 * A player in a game of crazy eights.
 * 
 */
public class Player {
    
    private String name;
    private Hand hand;
    
    /**
     * Constructs a player with an empty hand.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Hand(name);
    }
    
    /**
     * Gets the player's name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the player's hand.
     */
    public Hand getHand() {
        return this.hand;
    }
    
    /**
     * Adds a card to the player's hand.
     */
    public void addCard(Card card) {
        this.hand.addCard(card);
    }
    
    /**
     * Removes and returns a legal card from the player's
     * hand, or returns null if the player cannot play.
     */
    public Card play(Card prev) {
        // return the first card that works
        for (int i = 0; i < this.hand.size(); i++) {
            Card card = this.hand.getCard(i);
            if (cardMatches(card, prev)) {
                return this.hand.popCard(i);
            }
        }
        // no legal moves are possible
        return null;
    }

    /**
     * Checks whether two cards match.
     */
    public static boolean cardMatches(Card card1, Card card2) {
        if (card1.getSuit() == card2.getSuit()) {
            return true;
        }
        if (card1.getRank() == card2.getRank()) {
            return true;
        }
        if (card1.getRank() == 8) {
            return true;
        }
        return false;
    }
    
    /**
     * Calculates the player's score (penalty points).
     */
    public int score() {
        int sum = 0;
        for (int i = 0; i < this.hand.size(); i++) {
            Card card = this.hand.getCard(i);
            int rank = card.getRank();
            if (rank == 8) {
                sum -= 20;
            } else if (rank > 10) {
                sum -= 10;
            } else {
                sum -= rank;
            }
        }
        return sum;
    }
    
    /**
     * Returns a string representation of the player.
     */
    public String toString() {
        return this.name + ": " + this.hand;
    }

    /**
     * Displays the player's name and hand.
     */
    public void display() {
        System.out.println(this.name);
        this.hand.display();
    }
    
    /**
     * Displays the player's name and score.
     */
    public void displayScore() {
        System.out.println(this.name + " has " + this.score() + " points");
    }
}
