import java.util.ArrayList;
import java.util.Random;

/**
 * A pile of cards in a game.
 * 
 * @author Chris Mayfield
 * @version 12/29/2015
 */
public class Pile {
    
    private String name;
    
    private ArrayList<Card> cards;
    
    /**
     * Constructs an empty pile of cards.
     */
    public Pile(String name) {
        this.name = name;
        this.cards = new ArrayList<Card>();
    }
    
    /**
     * Adds a card at the end of the pile.
     */
    public void add(Card card) {
        this.cards.add(card);
    }
    
    /**
     * Adds the given cards to the pile.
     */
    public void deal(Deck deck) {
        for (Card card : deck.getCards()) {
            this.cards.add(card);
        }
    }
    
    /**
     * True if the pile has no cards, false otherwise.
     */
    public boolean empty() {
        return this.cards.size() == 0;
    }
    
    /**
     * Gets the pile's name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the last card in the pile.
     */
    public Card last() {
        int index = this.cards.size() - 1;
        return this.cards.get(index);
    }
    
    /**
     * Removes the last card in the pile.
     */
    public Card remove() {
        int index = this.cards.size() - 1;
        return this.cards.remove(index);
    }
    
    /**
     * Randomly permute the pile of cards.
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = this.cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i);
            Card temp = this.cards.get(i);
            this.cards.set(i, this.cards.get(j));
            this.cards.set(j, temp);
        }
    }
    
    /**
     * Swap cards with another pile.
     */
    public void swap(Pile pile) {
        ArrayList<Card> temp = this.cards;
        this.cards = pile.cards;
        pile.cards = temp;
    }
    
    /**
     * Returns a string representation of the pile.
     */
    public String toString() {
        return this.name + ": " + this.cards;
    }
    
}
