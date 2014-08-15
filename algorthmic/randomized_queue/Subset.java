public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rando = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String N = StdIn.readString();
            rando.enqueue(N);
        }
        for (int i = 1; i <= k; i++)
            StdOut.println(rando.dequeue());
    }
}