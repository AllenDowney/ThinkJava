import java.util.ArrayList;
import java.util.Random;

/**
 * A deck of playing cards.
 * 
 */
public class Deck {
    
    protected ArrayList<Card> cards;
    
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
     * Returns the number of cards in the deck.
     */
    public int size() {
        return this.cards.size();
    }
    
    /**
     * True if the deck is empty, false otherwise.
     */
    public boolean empty() {
        return this.cards.size() == 0;
    }

    /**
     * Randomly permute the deck of cards.
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
     * Moves n cards from this deck to the given deck.
     */
    public void deal(Deck deck, int n) {
        for (int i=0; i<n; i++) {
            Card card = this.popCard();
            deck.addCard(card);
        }
    }
    
    /**
     * Moves all remaining cards to the given deck.
     */
    public void deal(Deck deck) {
        int n = this.size();
        this.deal(deck, n);
    }
    
    /**
     * Adds the given card to the deck.
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
     * Returns the last card in the deck.
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
     * Removes and returns the last card in the deck.
     */
    public Card popCard() {
        int i = this.size() - 1;
        return this.popCard(i);
    }

    /**
     * Returns a string representation of the deck.
     */
    public String toString() {
        return "Deck: " + this.cards.toString();
    }    

    /**
     * Gets the internal cards array (should only be used for testing).
     */
    public Card[] getCards() {
        return (Card[])this.cards.toArray();
    }    
}
