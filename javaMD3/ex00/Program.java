package ex00;

public class Program {
    
    public static void main(String[] args) {
        SignatureParser parser = new SignatureParser();

        parser.parseFile("/home/koby/Desktop/javaPath/javaMD3/ex00/signatures.txt");
        parser.decodeFile("/home/koby/Desktop/javaPath/javaMD3/ex00/test.py");
        parser.decodeFile("/home/koby/Desktop/javaPath/javaMD3/ex00/test2.iso");
        parser.decodeFile("/home/koby/Desktop/javaPath/javaMD3/ex00/test3.jpg");
    }
}