package ex00;

public class Program {

    public static void main(String[] args) {
        if (args[0].length() > 1 || args[1].length() > 1) {
            System.err.println("please enter only one character");
            System.exit(-1);
        }

        if (args.length != 3) {
            System.err.println("please enter 3 arguments");
            System.exit(-1);
        }

        Logic.printImage(args[0].charAt(0), args[1].charAt(0), args[2]);
    }
}
