/**
 * Sorting algorithms for arrays of cards.
 * 
 * @author Chris Mayfield
 * @version 12/29/2015
 */
public class Sort {
    
    /*
     * Helper method that finds the index of the lowest card
     * between low and high inclusive.
     */
    public static int indexLowest(Card[] cards, int low, int high) {
        int min = low;
        for (int i = low + 1; i <= high; i++) {
            if (cards[i].compareTo(cards[min]) < 0) {
                min = i;
            }
        }
        return min;
    }
    
    /**
     * Helper method that swaps the cards at indexes i and j.
     */
    public static void swapCards(Card[] cards, int i, int j) {
        Card temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }
    
    /**
     * Reorders the cards (in place) using selection sort.
     */
    public static void selection(Card[] cards) {
        int high = cards.length - 1;
        for (int i = 0; i < cards.length; i++) {
            int j = indexLowest(cards, i, high);
            swapCards(cards, i, j);
        }
    }
    
    /**
     * Helper method that returns a subset of the cards.
     */
    public static Card[] subdeck(Card cards[], int low, int high) {
        Card[] sub = new Card[high - low + 1];
        for (int i = 0; i < sub.length; i++) {
            sub[i] = cards[low + i];
        }
        return sub;
    }
    
    /**
     * Helper method that merges two subdecks in order.
     */
    public static Card[] merge(Card[] d1, Card[] d2) {
        Card[] result = new Card[d1.length + d2.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < result.length; k++) {
            int choice;
            
            // determine which card to merge next
            if (i >= d1.length) {
                choice = 2;  // d1 is empty
            } else if (j >= d2.length) {
                choice = 1;  // d2 is empty
            } else if (d1[i].compareTo(d2[j]) < 0) {
                choice = 1;  // d1 is lower
            } else {
                choice = 2;  // d2 is lower
            }
            
            // store the chosen card in the result
            if (choice == 1) {
                result[k] = d1[i];
                i++;
            } else {
                result[k] = d2[j];
                j++;
            }
        }
        return result;
    }
    
    /**
     * Returns a reordered array using merge sort.
     */
    public static Card[] mergesort(Card[] cards) {
        
        // 0 or 1 cards already sorted
        if (cards.length < 2) {
            return cards;
        }
        
        // cut the deck about in half
        int mid = cards.length / 2;
        Card[] d1 = subdeck(cards, 0, mid - 1);
        Card[] d2 = subdeck(cards, mid, cards.length - 1);
        
        // sort each half and merge
        d1 = mergesort(d1);
        d2 = mergesort(d2);
        return merge(d1, d2);
    }
    
    /**
     * Reorders the cards (in place) using insertion sort.
     */
    public static void insertion(Card[] cards) {
        for (int i = 1; i < cards.length; i++) {
            Card temp = cards[i];
            
            // find insertion point and shift to the right
            int j = i - 1;
            while (j >= 0 && temp.compareTo(cards[j]) < 0) {
                cards[j + 1] = cards[j];
                j--;
            }
            
            // loop ends to the left of the insertion point
            cards[j + 1] = temp;
        }
    }
    
    /*
     * Checks that the deck is sorted.
     */
    public static void checkSorted(Card[] cards) {
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].compareTo(cards[i + 1]) >= 0) {
                System.out.println("Card #" + i + " not sorted!");
            }
        }
    }
    
    /**
     * Demonstrates how to call the sorting methods.
     */
    public static void main(String[] args) {
        Deck deck;
        Card[] cards;
        
        System.out.println("Testing selection...");
        deck = new Deck();
        deck.shuffle();
        cards = deck.getCards();
        selection(cards);
        checkSorted(cards);
        
        System.out.println("Testing mergesort...");
        deck = new Deck();
        deck.shuffle();
        cards = deck.getCards();
        cards = mergesort(cards);
        checkSorted(cards);
        
        System.out.println("Testing insertion...");
        deck = new Deck();
        deck.shuffle();
        cards = deck.getCards();
        insertion(cards);
        checkSorted(cards);
    }
    
}
