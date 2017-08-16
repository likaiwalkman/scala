public class TailRecJava {
    public static void main(String[] args) {
        int v = fact(10);
        System.out.println(v);
    }
    static int fact(int n){
        return factorial_tail(n, 1,2);
    }
    static int factorial_tail(int n, int acc1, int acc2) {
        if (n < 2) {
            return acc1;
        } else {
            return factorial_tail(n - 1, acc2, acc1 + acc2);
        }
    }

}
