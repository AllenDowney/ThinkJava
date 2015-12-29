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
        Deck pack = deck.subdeck(16, 50);
        Deck disc = deck.subdeck(51, 51);
        
        // keep playing until there's a winner
        Player player = one;
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
            
            // play the next card
            Card prev = disc.last();
            Card next = player.play(prev);
            while (next == null) {
                if (pack.empty()) {
                    // replace the pack with discard pile
                    Deck temp = pack;
                    pack = disc;
                    disc = temp;
                    // move the top card back into discard
                    pack.remove(prev);
                    disc.add(prev);
                    pack.shuffle();
                }
                System.out.println(player.getName() + " draws " + pack.first());
                player.draw(pack);
                next = player.play(prev);
            }
            System.out.println(player.getName() + " plays " + next);
            System.out.println();
            disc.add(next);
            
            // pick the next player
            if (player == one) {
                player = two;
            } else {
                player = one;
            }
        }
        
        // display the final score
        System.out.println(one.getName() + " has " + one.score() + " points");
        System.out.println(two.getName() + " has " + two.score() + " points");
    }
    
}
