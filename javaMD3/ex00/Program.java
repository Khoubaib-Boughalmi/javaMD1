package ex00;

import java.util.Map;

public class Program {
    public void printSignaturesDetailed(Map<FileType, Map<String, Object>> signatures) {
        System.out.println("--- Detailed Signature Printing ---");
        signatures.forEach((fileType, details) -> {
            System.out.println("File Type: " + fileType);
            System.out.println("Signature Details:");
            
            // Safely print each known detail with type information
            printDetail(details, "type", "Type");
            printDetail(details, "magicNumber", "Magic Number");
            printDetail(details, "size", "Size");
            printDetail(details, "offset", "Offset");
            
            System.out.println("---");
        });
    }

    // Helper method for safe detail printing
    private void printDetail(Map<String, Object> details, String key, String label) {
        Object value = details.get(key);
        if (value != null) {
            System.out.printf("  %s: %s (Type: %s)%n", 
                label, 
                value, 
                value.getClass().getSimpleName()
            );
        }
    }
    public static void main(String args) {
        SignatureMap signatures = new SignatureMap();

        Signature signature1 = new Signature(FileType.PNG, "89 50 4E 47 0D 0A 1A 0A", 8, 0);
        Signature signature2 = new Signature(FileType.JPEG, "FF D8 FF", 3, 0);

        signatures.addSignature(signature1);
        signatures.addSignature(signature2);

        Map<FileType, Map<String, Object>> signaturesList = signatures.getSignatures();
        
    }
}