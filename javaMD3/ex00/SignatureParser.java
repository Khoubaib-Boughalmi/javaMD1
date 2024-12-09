package ex00;

import java.util.HashMap;
import java.util.Map;

public class SignatureParser {
    public SignatureParser() {

        Map<String, Map<String, Object>> outerMap = new HashMap<>();
    
        // Create an inner HashMap
        Map<String, Object> innerMap = new HashMap<>();
        innerMap.put("type", "pdf");
        innerMap.put("value", "template");
        innerMap.put("count", 5);
    
        // Add the inner map to the outer map
        outerMap.put("name", innerMap);
    
        // Accessing values
        Map<String, Object> retrievedInnerMap = outerMap.get("name");
        System.out.println("Type: " + retrievedInnerMap.get("type"));
        System.out.println("Value: " + retrievedInnerMap.get("value"));
        System.out.println("Count: " + retrievedInnerMap.get("count"));   
    }
}