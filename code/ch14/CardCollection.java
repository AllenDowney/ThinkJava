import java.util.ArrayList;
import java.util.Random;

/**
 * A collection of playing cards.
 * 
 */
public class CardCollection {
    
    protected String label;
    protected ArrayList<Card> cards;
    
    /**
     * Constructs an empty collection.
     */
    public CardCollection(String label) {
        this.label = label;
        this.cards = new ArrayList<Card>();
    }
    
    /**
     * Returns the number of cards
     */
    public int size() {
        return this.cards.size();
    }
    
    /**
     * True if the collection is empty, false otherwise.
     */
    public boolean empty() {
        return this.cards.size() == 0;
    }

    /**
     * Randomly permute the cards.
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = this.size() - 1; i > 0; i--) {
            int j = random.nextInt(i);
            this.swapCards(i, j);
        }
    }

    /**
     * Swaps the cards at indexes i and j.
     */
    public void swapCards(int i, int j) {
        Card temp = this.cards.get(i);
        this.cards.set(i, this.cards.get(j));
        this.cards.set(j, temp);
    }
    
    /**
     * Moves n cards from this collect to the given collection.
     */
    public void deal(CardCollection cc, int n) {
        for (int i=0; i<n; i++) {
            Card card = this.popCard();
            cc.addCard(card);
        }
    }
    
    /**
     * Moves all remaining cards to the given cc.
     */
    public void deal(CardCollection cc) {
        int n = this.size();
        this.deal(cc, n);
    }
    
    /**
     * Adds the given card to the collection.
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }
 
    /**
     * Returns the card with the given index.
     */
    public Card getCard(int i) {
        return this.cards.get(i);
    }

    /**
     * Returns the last card.
     */
    public Card last() {
        int i = this.size() - 1;        
        return this.cards.get(i);
    }

    /**
     * Removes and returns the card with the given index.
     */
    public Card popCard(int i) {
        return this.cards.remove(i);
    }

    /**
     * Removes and returns the last card.
     */
    public Card popCard() {
        int i = this.size() - 1;
        return this.popCard(i);
    }

    /**
     * Returns a string representation of the card collection.
     */
    public String toString() {
        return this.label + ": " + this.cards.toString();
    }    

    /**
     * Gets the internal cards array (should only be used for testing).
     */
    public Card[] getCards() {
        return (Card[])this.cards.toArray();
    }    
}
