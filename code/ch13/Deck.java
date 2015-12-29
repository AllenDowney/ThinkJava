import java.util.Arrays;
import java.util.Random;

/**
 * A deck of playing cards (of fixed size).
 * 
 * @author Chris Mayfield
 * @version 12/28/2015
 */
public class Deck {
    
    private Card[] cards;
    
    /**
     * Constructs a standard deck of 52 cards.
     */
    public Deck() {
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                this.cards[index] = new Card(suit, rank);
                index++;
            }
        }
    }
    
    /**
     * Constructs a deck of n cards (null).
     */
    public Deck(int n) {
        this.cards = new Card[n];
    }
    
    /**
     * Randomly permute the deck of cards.
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = this.cards.length - 1; i > 0; i--) {
            int j = random.nextInt(i);
            Card temp = this.cards[i];
            this.cards[i] = this.cards[j];
            this.cards[j] = temp;
        }
    }
    
    /**
     * Reorders the deck using insertion sort.
     */
    public void sort() {
        for (int i = 1; i < this.cards.length; i++) {
            Card temp = this.cards[i];
            int j = i - 1;
            while (j >= 0 && temp.compareTo(this.cards[j]) < 0) {
                this.cards[j + 1] = this.cards[j];
                j--;
            }
            this.cards[j + 1] = temp;
        }
    }
    
    /**
     * Returns a subset of the cards in the deck.
     */
    public Deck subdeck(int low, int high) {
        Deck sub = new Deck(high - low + 1);
        for (int i = 0; i < sub.cards.length; i++) {
            sub.cards[i] = this.cards[low + i];
        }
        return sub;
    }
    
    /**
     * Returns a string representation of the deck.
     */
    public String toString() {
        return Arrays.toString(this.cards);
    }
    
}
