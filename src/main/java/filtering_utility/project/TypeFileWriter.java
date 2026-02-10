package filtering_utility.project;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class TypeFileWriter {

    private final Map<Type, BufferedWriter> writers = new HashMap<>();

    public void write(Type type, Path outputFile, String prefix, Boolean isAppend, String line) throws IOException {

        BufferedWriter bw = writers.get(type);

        if (bw == null) {

            bw = Files.newBufferedWriter(
                    outputFile,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    isAppend ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING);
            writers.put(type, bw);
        }

        bw.write(line);
        bw.newLine();
    }

    public void closeAll() {
        for (BufferedWriter bw : writers.values()) {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
