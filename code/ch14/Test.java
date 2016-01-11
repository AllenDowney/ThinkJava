/**
 * Test sorting algorithms for decks of cards.
 * 
 */
public class Test {
    
    /**
     * Test code.
     */
    public static void main(String[] args) {
        Deck deck = new Deck("Deck");
        deck.shuffle();
        System.out.println(deck);

        Hand hand1 = new Hand("Hand 1");
        deck.deal(hand1, 5);
        hand1.display();

        Hand drawPile = new Hand("Draw Pile");
        deck.dealAll(drawPile);
        System.out.println(drawPile.size());
    }
}
