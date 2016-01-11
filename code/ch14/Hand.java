/**
 * A hand of playing cards.
 * 
  */
public class Hand extends CardCollection {

    public Hand(String label) {
        super(label);
    }

    public void display() {
        for (int i = 0; i < this.cards.size(); i++) {
            System.out.println(this.cards.get(i));
        }
    }
}
