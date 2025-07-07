package dimasnovik;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

public class PreProcessor {
    private final boolean append;
    private final String statsMode;
    private final String dir;
    private final String prefix;
    private final List<String> inputFiles;

    public static Options getOptions() {
        Options options = new Options();
        options.addOption("a", false, "Append Mode");
        options.addOption("o", true, "Path to directory");
        options.addOption("p", true, "Prefix");
        options.addOption("s", false, "Stats");
        options.addOption("f", false, "Full stats");
        return options;
    }

    public PreProcessor(String[] args) throws ParseException {
        Options options = getOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String[] inputArgs = cmd.getArgs();
        if (inputArgs.length == 0) {
            System.err.println("No input files specified.");
            SimpleFilterUtil.printHelp();
            ErrorCounter.getInstance().addError();
        }
        inputFiles = new ArrayList<>();
        inputFiles.addAll(List.of(inputArgs));
        this.dir = cmd.getOptionValue("o", System.getProperty("user.dir"));
        this.prefix = cmd.getOptionValue("p", "");
        this.append = cmd.hasOption("a");
        if (cmd.hasOption("s")) {
            statsMode = "s";
        } else if (cmd.hasOption("f")) {
            statsMode = "f";
        } else {
            statsMode = "";
        }
    }

    public boolean needStats() {
        return !statsMode.equals("");
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public FileWriter getFileWriter() {
        return new FileWriter(append, dir, prefix);
    }

    public StatsMaker getStatsMaker() {
        return new StatsMaker(statsMode);
    }

}
