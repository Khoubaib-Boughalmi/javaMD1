package ex00;

public class Program {

    public static void main(String[] args) {
        SignatureParser parser = new SignatureParser();
        String resultFile = "/Users/kboughal/Desktop/javaModules/javaMD3/ex00/result.txt";


        // REPL UNTIL RECEIVE 42
        parser.parseSignatureFile("/Users/kboughal/Desktop/javaModules/javaMD3/ex00/signatures.txt",
                resultFile);
        parser.decodeInputFile("/Users/kboughal/Desktop/javaModules/javaMD3/ex00/test.pdf",
                resultFile);
        parser.decodeInputFile("/Users/kboughal/Desktop/javaModules/javaMD3/ex00/flow.png",
                resultFile);
        parser.decodeInputFile("/Users/kboughal/Desktop/javaModules/javaMD3/ex00/signatures.txt",
                resultFile);
    }
}