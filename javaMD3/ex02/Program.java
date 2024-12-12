package ex02;
public class Program {
    public static void main(String []args) {
        try {
            Handler.checkValidProgramArgs(args);
            Handler.parseInputDirectory(args[0]);
            Tools.ls();
            Tools.pwd();
            System.out.println("-----------------------");
            Tools.cd("/Users/kboughal/Desktop/javaModules/javaMD3");
            Tools.pwd();
            Tools.ls();
            System.out.println("-----------------------");
            Tools.mv("../inputX.txt", ".");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}