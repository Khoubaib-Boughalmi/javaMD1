package ex02;
public class Program {
    public static void main(String []args) {
        String dir;
        try {
            Handler.checkValidProgramArgs(args);
            dir = Handler.parseInputDirectory(args[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Tools.ls("/Users/kboughal/Desktop/javaModules/javaMD3");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}