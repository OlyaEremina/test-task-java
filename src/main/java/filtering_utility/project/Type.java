package filtering_utility.project;

public enum Type {
    INT("integers.txt"),
    FLOAT("floats.txt"),
    STRING("strings.txt");

    private final String fileName;

    Type(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}