package ex00;

enum FileType {
    JPEG,
    PNG,
    PDF,
    GIF,
    ZIP,
    MP3,
    EXE,
    ELF,
    RAR,
    WAV
}


public class Signature {
    private FileType type;
    private String magicNumber;
    private int size;
    private int offset;

    public Signature (FileType type, String magicNumber, int size, int offset) {
        this.type = type;
        this.magicNumber = magicNumber;
        this.size = size;
        this.offset = offset;
    }

    public FileType getType() {
        return type;
    }

    public String getMagicNumber() {
        return magicNumber;
    }

    public int getSize() {
        return size;
    }

    public int getOffset() {
        return offset;
    }
}

