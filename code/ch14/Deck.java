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
        cards = new ArrayList<Card>();
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(rank, suit));
            }
        }
    }
    
    /**
     * Constructs a deck of n cards (empty).
     */
    public Deck(int n) {
        cards = new ArrayList<Card>(n);
    }
    
    /**
     * Constructs a deck with the given cards.
     */
    public Deck(List<Card> list) {
        cards = new ArrayList<Card>(list);
    }
    
    /**
     * Adds a card to the deck.
     */
    public void add(Card card) {
        cards.add(card);
    }
    
    /**
     * True if the deck is empty, false otherwise.
     */
    public boolean empty() {
        return cards.size() == 0;
    }
    
    /**
     * Gets the first card in the deck.
     */
    public Card first() {
        return cards.get(0);
    }
    
    /**
     * Returns an iterator over the cards.
     */
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
    
    /**
     * Gets the last card in the deck.
     */
    public Card last() {
        return cards.get(cards.size() - 1);
    }
    
    /**
     * Removes a card from the deck.
     */
    public void remove(Card card) {
        cards.remove(card);
    }
    
    /**
     * Randomly permute the deck of cards.
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }
    
    /**
     * Returns a subset of the cards in the deck.
     */
    public Deck subdeck(int low, int high) {
        List<Card> sub = cards.subList(low, high + 1);
        return new Deck(sub);
    }
    
    /**
     * Returns a string representation of the deck.
     */
    public String toString() {
        return cards.toString();
    }
    
}
