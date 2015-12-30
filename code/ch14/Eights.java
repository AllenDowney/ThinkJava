import java.util.Scanner;

/**
 * Simulates a game of Crazy Eights. Refer to
 * https://en.wikipedia.org/wiki/Crazy_Eights
 * for basic play and scoring rules.
 * 
 * @author Chris Mayfield
 * @version 12/29/2015
 */
public class Eights {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();
        
        // eight cards are dealt to each player
        Player one = new Player("Allen");
        one.deal(deck.subdeck(0, 7));
        Player two = new Player("Chris");
        two.deal(deck.subdeck(8, 15));
        
        // remaining cards are placed face down
        // and the top card is turned face up
        Pile pack = new Pile("Draw Pile");
        pack.deal(deck.subdeck(16, 50));
        Pile disc = new Pile("Discard Pile");
        disc.deal(deck.subdeck(51, 51));
        
        // keep playing until there's a winner
        Player current = one;
        while (!one.empty() && !two.empty()) {
            
            // output the current state of the game
            System.out.println(one);
            System.out.println();
            System.out.println(two);
            System.out.println();
            System.out.println(pack);
            System.out.println();
            System.out.println(disc);
            in.nextLine();
            
            // play the next card, drawing cards if needed
            Card prev = disc.last();
            Card next = current.play(prev);
            while (next == null) {
                if (pack.empty()) {
                    System.out.println("shuffling discard pile into draw pile");
                    pack.swap(disc);
                    prev = pack.remove();
                    disc.add(prev);
                    pack.shuffle();
                }
                Card draw = pack.remove();
                System.out.println(current.getName() + " draws " + draw);
                current.add(draw);
                next = current.play(prev);
            }
            System.out.println(current.getName() + " plays " + next);
            System.out.println();
            disc.add(next);
            
            // select the next player
            if (current == one) {
                current = two;
            } else {
                current = one;
            }
        }
        
        // display the final score
        System.out.println(one.getName() + " has " + one.score() + " points");
        System.out.println(two.getName() + " has " + two.score() + " points");
    }
    
}
