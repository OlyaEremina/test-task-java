package filtering_utility.project;

import java.util.ArrayList;
import java.util.List;

public class ProgramSettings {

    public String outputDir = ".";
    public String prefix = "";
    public boolean isAppend = false;
    public boolean isShortStat = false;
    public boolean isFullStat = false;
    public List<String> inputFiles = new ArrayList<>();

    public static ProgramSettings parseArgs(String[] args) {
        ProgramSettings settings = new ProgramSettings();

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {

                case "-o" -> {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")
                            && (!args[i + 1].toLowerCase().endsWith((".txt")))) {
                        settings.outputDir = args[++i];
                    } else {
                        System.err.println(
                                "Предупреждение: опция -o требует путь для результатов. " +
                                        "Программа продолжит работу, игнорируя эту опцию.");
                    }
                }

                case "-p" -> {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")
                            && (!args[i + 1].toLowerCase().endsWith((".txt")))) {
                        settings.prefix = args[++i];
                    } else {
                        System.err.println(
                                "Предупреждение: опция -p требует значение префикса. " +
                                        "Программа продолжит работу, игнорируя эту опцию.");
                    }
                }

                case "-a" ->
                    settings.isAppend = true;

                case "-s" ->
                    settings.isShortStat = true;

                case "-f" ->
                    settings.isFullStat = true;

                default -> {
                    if (!args[i].toLowerCase().endsWith(".txt")) {
                        System.err.println(
                                "Предупреждение: неизвестная опция " + args[i] +
                                        ". Программа продолжит работу, игнорируя эту опцию.");
                    } else {
                        settings.inputFiles.add("input/" + args[i]);
                    }
                }
            }
        }

        return settings;
    }
}
