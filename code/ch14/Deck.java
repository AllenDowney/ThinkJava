import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A deck of playing cards (of flexible size).
 * 
 * @author Chris Mayfield
 * @version 12/29/2015
 */
public class Deck {
    
    private ArrayList<Card> cards;
    
    /**
     * Constructs a standard deck of 52 cards.
     */
    public Deck() {
        this.cards = new ArrayList<Card>();
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                this.cards.add(new Card(rank, suit));
            }
        }
    }
    
    /**
     * Constructs a deck of n cards (empty).
     */
    public Deck(int n) {
        this.cards = new ArrayList<Card>(n);
    }
    
    /**
     * Constructs a deck with the given cards.
     */
    public Deck(List<Card> cards) {
        this.cards = new ArrayList<Card>(cards);
    }
    
    /**
     * Adds a card to the deck.
     */
    public void add(Card card) {
        this.cards.add(card);
    }
    
    /**
     * True if the deck is empty, false otherwise.
     */
    public boolean empty() {
        return this.cards.size() == 0;
    }
    
    /**
     * Gets the first card in the deck.
     */
    public Card first() {
        return this.cards.get(0);
    }
    
    /**
     * Returns an iterator over the cards.
     */
    public Iterator<Card> iterator() {
        return this.cards.iterator();
    }
    
    /**
     * Gets the last card in the deck.
     */
    public Card last() {
        return this.cards.get(this.cards.size() - 1);
    }
    
    /**
     * Removes a card from the deck.
     */
    public void remove(Card card) {
        this.cards.remove(card);
    }
    
    /**
     * Randomly permute the deck of cards.
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = this.cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i);
            //TODO: refactor swapCards
            Card temp = this.cards.get(i);
            this.cards.set(i, this.cards.get(j));
            this.cards.set(j, temp);
        }
    }
    
    /**
     * Reorders the deck using insertion sort.
     */
    public void sort() {
        for (int i = 1; i < this.cards.size(); i++) {
            Card temp = this.cards.get(i);
            int j = i - 1;
            while (j >= 0 && temp.compareTo(this.cards.get(j)) < 0) {
                //TODO: refactor indexLowestCard
                this.cards.set(j + 1, this.cards.get(j));
                j--;
            }
            this.cards.set(j + 1, temp);
        }
    }
    
    /**
     * Returns a subset of the cards in the deck.
     */
    public List<Card> subdeck(int low, int high) {
        return this.cards.subList(low, high + 1);
    }
    
    /**
     * Returns a string representation of the deck.
     */
    public String toString() {
        return this.cards.toString();
    }
    
}
