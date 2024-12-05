/**
 *
 * @author kboughal
 */

public class Program {

    public static void sortCipheredInput(int [][]originalInputOcc) {
        for (int i = 0; i < 26; ++i) {
            for (int j = i + 1; j < 26; ++j) {
                if (originalInputOcc[j][1] > originalInputOcc[i][1]) {
                    int tmp[] = originalInputOcc[i];
                    originalInputOcc[i] =  originalInputOcc[j];
                    originalInputOcc[j] = tmp;
                }
            }    
        }
    }

    public static void main(String... args){
        if (args.length != 1) {
            System.err.println("Wrong number of input");
            System.exit(-1);
        }
        int[][] originalInputOcc = new int[26][2];

        for (int i = 0; i < 26; i++) {
            originalInputOcc[i][0] = 'a' + i;
            originalInputOcc[i][1] = 0;
        }
        char[] charList = args[0].toCharArray();
        for (char c : charList) {
            int index = (int)c - 'A';
            originalInputOcc[index][1] += 1;
        }

        sortCipheredInput(originalInputOcc);
        int cipherColumns = 0;
        for (int i = 0; i < 26; i++) {
            if (originalInputOcc[i][1] > 1) {
                cipherColumns += 1;
            }
        }
        for (int row = 10; row >= 0; row--) {
            for (int column = 0; column < cipherColumns; column++) {
                int chartHeight = (originalInputOcc[column][1] * 10) / originalInputOcc[0][1];
                if (chartHeight == row) {
                    System.out.printf("%3d", originalInputOcc[column][1]);
                } else if (chartHeight > row) {
                    System.out.print("  #");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < cipherColumns; i++) {
            System.out.printf("%3c", (char)(originalInputOcc[i][0]-32));
        }
        System.out.println();
    }
}
