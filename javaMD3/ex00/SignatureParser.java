package ex00;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringJoiner;

public class SignatureParser {

    private final SignatureMap signaturesMap;

    public SignatureParser() {
        this.signaturesMap = new SignatureMap();
    }

    private String readLine(InputStreamReader reader) {
        StringBuilder line = new StringBuilder("");
        int c;
        try {
            while ((c = reader.read()) != -1) {
                if ((char) c == '\n')
                    break;
                line.append((char) c);
            }
        } catch (

        IOException e) {
            e.printStackTrace();
        }
        return line.toString();
    }

    private void writeLine(OutputStreamWriter writer, String line) {
        try {
            writer.append(line + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createResultFile(OutputStreamWriter writer) {
        try {
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void parseSignatureFile(String inputPath, String outputPath) {
        try (FileInputStream inputFile = new FileInputStream(inputPath);
                InputStreamReader reader = new InputStreamReader(inputFile);
                FileOutputStream outputfile = new FileOutputStream(outputPath);
                OutputStreamWriter writer = new OutputStreamWriter(outputfile);) {

            String line;

            try {
                createResultFile(writer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            while (!(line = readLine(reader)).isEmpty()) {
                String[] content = this.parseLine(line);
                try {
                    StringBuilder intString = new StringBuilder();
                    for (String hex : content[1].split(" ")) {
                        int intValue = Integer.parseInt(hex, 16);
                        intString.append(intValue).append(" ");
                    }
                    Signature signature = new Signature(FileType.valueOf(content[0]), intString.toString(),
                            Integer.parseInt(content[2]));
                    signaturesMap.addSignature(signature);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public String[] parseLine(String line) {
        String[] content = line.split("\\|");
        return content;
    }

    public void decodeInputFile(String inputPath, String outputPath) {
        try (FileInputStream file = new FileInputStream(inputPath);
                FileOutputStream outputfile = new FileOutputStream(outputPath, true);
                OutputStreamWriter writer = new OutputStreamWriter(outputfile);) {
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
                String magicNumber = (String) entry.getValue().get("magicNumber");
                if (joinedBytes.startsWith(magicNumber)) {
                    System.out.println("File type: " + entry.getKey());
                    writeLine(writer, entry.getKey().toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
}
