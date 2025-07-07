package dimasnovik;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWriter {

    private final Map<String, Path> paths;

    private final boolean a;


    public FileWriter(boolean a, String dir, String prefix) {
        this.a = a;
        this.paths = new HashMap<>();
        for (String type : List.of("integers", "floats", "strings")) {
            paths.put(type, Paths.get(dir, prefix + type + ".txt"));
        }
    }


    public void writeFiles(Map<String, List<String>> data) {
        for (String type : data.keySet()) {
            List<String> lines = data.get(type);
            Path path = paths.get(type);
            if (!lines.isEmpty()) {
                try {
                    if (!Files.exists(path.getParent())) {
                        Files.createDirectories(path.getParent());
                    }
                    if (a) {
                        Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } else {
                        Files.write(path, lines, StandardOpenOption.WRITE, StandardOpenOption.CREATE,
                                StandardOpenOption.TRUNCATE_EXISTING);
                    }
                } catch (IOException e) {
                    System.err.println("Writing files exception: " + e.getMessage());
                    ErrorCounter.getInstance().addError();
                }
            }
        }
    }
}
