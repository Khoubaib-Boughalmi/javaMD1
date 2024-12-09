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
}
