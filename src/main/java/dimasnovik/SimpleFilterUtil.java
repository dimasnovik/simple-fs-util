package dimasnovik;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SimpleFilterUtil {
    public static void main(String[] args) {
        StatsMaker statsMaker = null;
        try {
            PreProcessor processor = new PreProcessor(args);
            FileWriter writer = processor.getFileWriter();

            boolean needStats = processor.needStats();
            statsMaker = needStats ? processor.getStatsMaker() : null;
            List<String> files = processor.getInputFiles();
            Map<String, List<String>> data = new HashMap<>();
            for (String type : List.of("integers", "floats", "strings")) {
                data.put(type, new ArrayList<>());
            }
            for (String file : files) {
                try {
                    FileFormatValidator.validateTextFile(file);
                } catch (UnsupportedFileFormatException e) {
                    System.err.println("Error while reading file: " + e.getMessage());
                    ErrorCounter.getInstance().addError();
                    continue;
                }
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.isEmpty()) {
                            continue;
                        }
                        String type = processLine(line, statsMaker);
                        data.get(type).add(line);
                    }
                } catch (IOException e) {
                    System.err.println("Error while reading file: " + e.getMessage());
                    ErrorCounter.getInstance().addError();
                }
            }
            writer.writeFiles(data);
        } catch (ParseException e) {
            System.err.println("Parsing exception: " + e.getMessage());
            ErrorCounter.getInstance().addError();
            printHelp();
        }
        if (statsMaker != null) System.out.println(statsMaker.getStats());
        System.out.println("Process finished: Errors count: " + ErrorCounter.getInstance().getErrorCount());
    }

    private static String processLine(String line, StatsMaker statsMaker) {
        try {
            long intValue = Long.parseLong(line);
            if (statsMaker != null) {
                statsMaker.addInt(intValue);
            }
            return "integers";
        } catch (NumberFormatException e) {
            try {
                double floatValue = Double.parseDouble(line);
                if (statsMaker != null) {
                    statsMaker.addFloat(floatValue);
                }
                return "floats";
            } catch (NumberFormatException e1) {
                if (statsMaker != null) {
                    statsMaker.addString(line);
                }
                return "strings";
            }
        }
    }

    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar util.jar -f -a -p sample- in1.txt in2.txt", PreProcessor.getOptions());
    }
}