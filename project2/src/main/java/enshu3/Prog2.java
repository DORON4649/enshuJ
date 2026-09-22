package enshu3;

import java.util.Random;
public class Prog2 {

	public static void main(String[] args) {
       int d = new Random().nextInt(100);
       IO.println(d);
       switch (d) {
        case 0:
            IO.println("大吉");
            break;
        case 1:
            IO.println("中吉");
            break;
        case 2:
        case 3:
            IO.println("小吉");
            break;
        default:
            IO.println("凶");
            break;
       }
    }
}
