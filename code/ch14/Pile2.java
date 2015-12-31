import java.util.ArrayList;
import java.util.Random;

/**
 * A pile of cards in a game.
 * 
 * @author Chris Mayfield
 * @version 12/29/2015
 */
public class Pile2 extends Agent {
    
    /**
     * Constructs an empty pile of cards.
     */
    public Pile2(String name) {
        super(name);
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
    public void swap(Pile2 pile) {
        ArrayList<Card> temp = this.cards;
        this.cards = pile.cards;
        pile.cards = temp;
    }
    
}
