package ex00;

import java.util.HashMap;
import java.util.Map;

public class SignatureMap {
    private Map<FileType, Map<String, Object>> signatures;

    public SignatureMap() {
        signatures = new HashMap<>();
    }

    public void addSignature(Signature signature) {
        if (signature == null) {
            throw new IllegalArgumentException("Signature cannot be null");
        }
        
        Map<String, Object> innerMap = new HashMap<>();
        innerMap.put("type", signature.getType());
        innerMap.put("magicNumber", signature.getMagicNumber());
        innerMap.put("size", signature.getSize());
        innerMap.put("offset", signature.getOffset());
        this.signatures.put(signature.getType(), innerMap);
    }

    public Map<FileType, Map<String, Object>> getSignatures() {
        return this.signatures;
    }

    public void printSignaturesDetailed(Map<FileType, Map<String, Object>> signatures) {
        System.out.println("--- Detailed Signature Printing ---");
        signatures.forEach((fileType, details) -> {
            System.out.println("File Type: " + fileType);
            System.out.println("Signature Details:");
            
            printDetail(details, "type");
            printDetail(details, "magicNumber");
            printDetail(details, "size");
            printDetail(details, "offset");
            
            System.out.println("---");
        });
    }

    private void printDetail(Map<String, Object> details, String key) {
        Object value = details.get(key);
        if (value != null) {
            System.out.printf("%s: %s%n", 
                key,
                value
            );
        }
    }
}
