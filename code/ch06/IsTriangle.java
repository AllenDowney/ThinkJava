/**
 * Write a method named isTriangle that takes three integers as arguments and
 * returns either true or false
 * @author Rajarshi Mandal
 * @version 6.3.1
 */
 
public class IsTriangle {

    public static void main(String[] args){
    
        // Putting the value of x, y, z
        if (isTriangle(1, 1, 2)) {
            System.out.println("This can form a Triangle.");
        } else {
            System.out.println("This \"can not\" form a Triangle.");
        }

    }

    public static boolean isTriangle(int x, int y, int z) {
    
        // Verify that if any one of the sides is not 0
        // *true || anything is always true, false && anything is always false.
        if (x == 0 || y == 0 || z == 0){
            return false;
        } else {
            // This condition, only, satisfies all other conditions to be a triangle.
            return x + y > z;
        }

    }
    
}
