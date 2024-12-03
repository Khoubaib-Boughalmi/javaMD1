
public class Program {
    public static void main(String... args){
        if (args.length != 1) {
            System.err.println("Wrong number of input");
            System.exit(-1);
        }
        int[][] inputOcc = new int[26][2]; // Preferred Java style

        // Initialize
        for (int i = 0; i < 26; i++) {
            inputOcc[i][0] = 'a' + i; // ASCII value
            inputOcc[i][1] = 0;       // Count starts at 0
        }
        for (int i = 0; i < 26; i++) {
            System.out.println((char)inputOcc[i][0]);
        }
        char[] charList = args[0].toCharArray();
        System.out.println(charList);
    }
}
