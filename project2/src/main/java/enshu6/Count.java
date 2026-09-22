package enshu6;
import processing.core.PApplet;

public class Count extends PApplet {
    public void setup() {
        int c1 = count1();
        System.out.println(c1);

        int c2 = count2();
        System.out.println(c2);
    }

    int count1() {
        int sum = 0;
        int count = 0;
        while (sum < 100) {
            count++;
            sum += 17;
            System.out.printf("count=%3d sum=%3d\n", count, sum);
        }
        return count;
    }

    int count2() {
        int sum = 0;
        int count = 0;
        while (sum < 100) {
            count++;
            sum += count;
            System.out.printf("count=%3d sum=%3d\n", count, sum);
        }
        return count;
    }
    

    public static void main(String args[]) {
        PApplet.main("enshu6.Count");
    }
}