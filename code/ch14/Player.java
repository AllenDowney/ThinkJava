import java.util.ArrayList;

/**
 * A player in a card game.
 * 
 * @author Chris Mayfield
 * @version 12/29/2015
 */
public class Player {
    
    private String name;
    
    private ArrayList<Card> cards;
    
    /**
     * Constructs a player with an empty hand.
     */
    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<Card>();
    }
    
    /**
     * Adds a card to the player's hand.
     */
    public void add(Card card) {
        this.cards.add(card);
    }
    
    /**
     * Adds the given cards to the player's hand.
     */
    public void deal(Deck deck) {
        for (Card card : deck.getCards()) {
            this.cards.add(card);
        }
    }
    
    /**
     * True if the player has no cards, false otherwise.
     */
    public boolean empty() {
        return this.cards.size() == 0;
    }
    
    /**
     * Gets the player's name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Removes and returns a legal card from the player's
     * hand, or returns null if the player cannot play.
     */
    public Card play(Card prev) {
        // simply return the first card that works
        for (int i = 0; i < this.cards.size(); i++) {
            Card card = this.cards.get(i);
            if (card.isLike(prev) || card.getRank() == 8) {
                return this.cards.remove(i);
            }
        }
        // no legal moves are possible
        return null;
    }
    
    /**
     * Calculates the player's score (penalty points).
     */
    public int score() {
        int sum = 0;
        for (Card card : this.cards) {
            int rank = card.getRank();
            if (rank == 8) {
                sum += 20;
            } else if (rank > 10) {
                sum += 10;
            } else {
                sum += rank;
            }
        }
        return sum * -1;
    }
    
    /**
     * Returns a string representation of the player.
     */
    public String toString() {
        return this.name + ": " + this.cards;
    }
    
}
