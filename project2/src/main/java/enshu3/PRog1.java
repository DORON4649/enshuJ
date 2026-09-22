package enshu3;

public class PRog1 {
public static void main(String args[]) {
    int a = 5
    ;
    if (a > 7){
        IO.println("aは7より大きい");
    } else {
        IO.println("aは7以下");
    }
    for (int i=0; i<a; i++) {
        IO.print(" "+i);
    }
    IO.println();
    for (int i=0; i<a; i++) {
        if (i%2==0) {
            IO.print(i+"は偶数 ");
        }
    }
}
}
