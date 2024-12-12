package ex02;
public class Program {
    public static void main(String []args) {
        try {
            Handler.checkValidProgramArgs(args);
            Handler.parseInputDirectory(args[0]);
            Tools.ls();
            Tools.pwd();
            System.out.println("-----------------------");
            Tools.cd("/Users/kboughal/Desktop/javaModules/javaMD3/inputA.txt");
            Tools.pwd();
            Tools.ls();
            System.out.println("-----------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}