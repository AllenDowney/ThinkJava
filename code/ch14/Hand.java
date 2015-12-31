import java.util.ArrayList;

/**
 * A hand of playing cards.
 * 
  */
public class Hand extends Deck {
    
    /**
     * Constructs a Hand with a capacity of n Cards (but no cards).
     */
    public Hand() {
        this.cards = new ArrayList<Card>();
    }
    
    /**
     * Returns a string representation of the deck.
     */
    public String toString() {
        return "Hand: " + this.cards.toString();
    }

    public void display() {
        for (int i = 0; i < this.cards.size(); i++) {
            System.out.println(this.cards.get(i));
        }
    }
}
