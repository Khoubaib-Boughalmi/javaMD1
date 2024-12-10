package ex00;

public class Program {

    public static void main(String[] args) {
        SignatureParser parser = new SignatureParser();

        parser.parseSignatureFile("/Users/kboughal/Desktop/javaModules/javaMD3/ex00/signatures.txt",
                "/Users/kboughal/Desktop/javaModules/javaMD3/ex00/result.txt");
        parser.decodeInputFile("/Users/kboughal/Desktop/javaModules/javaMD3/ex00/test.pdf",
                "/Users/kboughal/Desktop/javaModules/javaMD3/ex00/result.txt");
        parser.decodeInputFile("/Users/kboughal/Desktop/javaModules/javaMD3/ex00/flow.png",
                "/Users/kboughal/Desktop/javaModules/javaMD3/ex00/result.txt");
    }
}