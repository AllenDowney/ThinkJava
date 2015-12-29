import java.util.Iterator;
import java.util.List;

/**
 * A player of the card game.
 * 
 * @author Chris Mayfield
 * @version 12/28/2015
 */
public class Player {
    
    private String name;
    
    private Deck hand;
    
    /**
     * Constructs a player with an empty hand.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Deck(0);
    }
    
    /**
     * Replaces the player's hand with the given cards.
     */
    public void deal(List<Card> hand) {
        this.hand = new Deck(hand);
    }
    
    /**
     * Removes the first card from the deck
     * and adds it to this player's hand.
     */
    public void draw(Deck deck) {
        Card card = deck.first();
        deck.remove(card);
        this.hand.add(card);
    }
    
    /**
     * True if the deck is empty, false otherwise.
     */
    public boolean empty() {
        return this.hand.empty();
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
     * 
     * @param top the top of the discard pile
     */
    public Card play(Card top) {
        // simply return the first card that works
        Iterator<Card> iter = this.hand.iterator();
        while (iter.hasNext()) {
            Card card = iter.next();
            if (card.isLike(top) || card.getRank() == 8) {
                this.hand.remove(card);
                return card;
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
        Iterator<Card> iter = this.hand.iterator();
        while (iter.hasNext()) {
            Card card = iter.next();
            int rank = card.getRank();
            if (rank == 8) {
                sum += 50;
            } else if (rank > 10) {
                sum += 10;
            } else {
                sum += rank;
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
    
}
