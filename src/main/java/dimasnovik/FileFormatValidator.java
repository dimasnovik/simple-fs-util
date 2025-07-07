package dimasnovik;

import java.util.Set;

public class FileFormatValidator {
    private static final Set<String> TEXT_EXTENSIONS = Set.of(
            "txt", "csv", "rtf", "java", "py"
    );

    public static void validateTextFile(String fileName) throws UnsupportedFileFormatException {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new UnsupportedFileFormatException("File has no extension: " + fileName);
        }
        String ext = fileName.substring(dotIndex + 1).toLowerCase();
        if (!TEXT_EXTENSIONS.contains(ext)) {
            throw new UnsupportedFileFormatException("Unsupported extension: " + "." + ext);
        }
    }
}
