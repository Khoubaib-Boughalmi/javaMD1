package ex00;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringJoiner;

public class SignatureParser {

    private final SignatureMap signaturesMap;

    public SignatureParser() {
        this.signaturesMap = new SignatureMap();
    }

    // FileInputStream (raw bytes) -> InputStreamReader (characters) -> BufferedReader (lines of text)
    public void parseFile(String path) {
        try (FileInputStream file = new FileInputStream(path); InputStreamReader reader = new InputStreamReader(file); BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] content = this.parseLine(line);
                try {
                    StringBuilder intString = new StringBuilder();
                    for (String hex : content[1].split(" ")) {
                        int intValue = Integer.parseInt(hex, 16);
                        intString.append(intValue).append(" "); 
                    }
                    Signature signature = new Signature(FileType.valueOf(content[0]), intString.toString(), Integer.parseInt(content[2]), Integer.parseInt(content[3]));
                    signaturesMap.addSignature(signature);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }

            }
            // Map<FileType, Map<String, Object>> signaturesList = signaturesMap.getSignatures();
            // signaturesMap.printSignaturesDetailed(signaturesList);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public String[] parseLine(String line) {
        String[] content = line.split("\\|");
        return content;
    }

    public void decodeFile(String path) {
        try (FileInputStream file = new FileInputStream(path);) {
            int count = 0;
            int memoryByte;
            ArrayList<Integer> magicBytes = new ArrayList<>();
            while ((memoryByte = file.read()) != -1 && count++ < 16) {
                magicBytes.add(memoryByte);

            }
            Map<FileType, Map<String, Object>> signaturesList = signaturesMap.getSignatures();
            StringJoiner joiner = new StringJoiner(" ");
            for (Integer num : magicBytes) {
                joiner.add(String.valueOf(num));
            }
            String joinedBytes = joiner.toString();
            for (Map.Entry<FileType, Map<String, Object>> entry : signaturesList.entrySet()) {
                String magicNumber = (String)entry.getValue().get("magicNumber");
                if (joinedBytes.startsWith(magicNumber)) {
                    System.out.println("File type: " + entry.getKey());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    // Signature signature1 = new Signature(FileType.PNG, "89 50 4E 47 0D 0A 1A 0A", 8, 0);
    // Signature signature2 = new Signature(FileType.JPEG, "FF D8 FF", 3, 0);
    // signatures.addSignature(signature1);
    // signatures.addSignature(signature2);
    // Map<FileType, Map<String, Object>> signaturesList = signatures.getSignatures();
    // signatures.printSignaturesDetailed(signaturesList);
}
