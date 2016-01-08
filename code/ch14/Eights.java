import java.util.Scanner;

/**
 * Simulates a game of Crazy Eights. Refer to
 * https://en.wikipedia.org/wiki/Crazy_Eights
 * for basic play and scoring rules.
 * 
 */
public class Eights {

    private Scanner in;
    private Deck deck;
    private Player one;
    private Player two;
    private Hand drawPile;
    private Hand discardPile;

    /**
     * Initializes the state of the game.
     */
    public Eights() {
        in = new Scanner(System.in);
        deck = new Deck();
        deck.shuffle();
        
        // deal cards to each player
        int handSize = 5;
        one = new Player("Allen");
        deck.deal(one.getHand(), handSize);

        two = new Player("Chris");
        deck.deal(two.getHand(), handSize);
        
        // turn one card face up
        discardPile = new Hand();
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand();
        deck.deal(drawPile);
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        this.one.display();
        System.out.println();
        this.two.display();
        System.out.println("\nDiscard pile");
        this.discardPile.display();
        System.out.println("\nDraw pile");
        System.out.println(this.drawPile.size() + " cards");
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        return this.one.getHand().empty() || this.two.getHand().empty();
    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public Card reshuffle() {
        System.out.println("shuffling discard pile into draw pile");
        discardPile.deal(drawPile);
        drawPile.shuffle();

        Card prev = drawPile.popCard();
        discardPile.addCard(prev);
        return prev;
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        // play the next card, drawing cards if needed
        Card prev = this.discardPile.last();
        Card next = player.play(prev);
        
        while (next == null) {
            if (drawPile.empty()) {
                prev = this.reshuffle();
            }
            Card card = drawPile.popCard();
            System.out.println(player.getName() + " draws " + card);
            player.getHand().addCard(card);
            next = player.play(prev);
        }
        System.out.println(player.getName() + " plays " + next);
        System.out.println();

        this.discardPile.addCard(next);    
    }

    /**
     * Waits for the user to press enter.
     */
    public void waitForUser() {
        this.in.nextLine();
    }

    /**
     * Switches players.
     */
    public Player otherPlayer(Player current) {
        if (current == this.one) {
            return this.two;
        } else {
            return this.one;
        }
    }

    /**
     * Plays the game.
     */
    public void playGame() {
        Player player = this.one;

        // keep playing until there's a winner
        while (!this.isDone()) {
            this.displayState();
            this.waitForUser();
            this.takeTurn(player);
            player = otherPlayer(player);
        }
        
        // display the final score
        this.one.displayScore();
        this.two.displayScore();
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {        
        Eights game = new Eights();
        game.playGame();
    }    
}
