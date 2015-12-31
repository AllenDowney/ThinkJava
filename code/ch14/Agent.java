import java.util.ArrayList;

/**
 * A named set of cards in a game.
 * 
 * @author Chris Mayfield
 * @version 12/30/2015
 */
public class Agent {
    
    protected String name;
    
    protected ArrayList<Card> cards;
    
    /**
     * Constructs a player with an empty hand.
     */
    public Agent(String name) {
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
     * Returns a string representation of the player.
     */
    public String toString() {
        return this.name + ": " + this.cards;
    }
    
}
