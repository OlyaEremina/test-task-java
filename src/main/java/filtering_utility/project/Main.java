package filtering_utility.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        TypeFileWriter fileWriter = new TypeFileWriter();
        NumberStatistics intStatistics = new NumberStatistics();
        NumberStatistics floatStatistics = new NumberStatistics();
        StringStatistics stringStatistics = new StringStatistics();

        try {

            if (args.length == 0) {
                System.err
                        .println("Ошибка: работа программы невозможна без входных файлов. Укажите хотя бы один файл.");
                return;
            }
            ProgramSettings settings = ProgramSettings.parseArgs(args);

            if (settings.inputFiles.isEmpty()) {
                System.err
                        .println("Ошибка: работа программы невозможна без входных файлов. Укажите хотя бы один файл.");
                return;
            }

            for (String eachInputFile : settings.inputFiles) {
                Path inputFile = Paths.get(eachInputFile);
                BufferedReader br = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8);
                String line;

                while ((line = br.readLine()) != null) {
                    Type dataType = TypeData.findoutType(line);

                    switch (dataType) {
                        case INT -> intStatistics.add(Long.parseLong(line));
                        case FLOAT -> floatStatistics.add(Float.parseFloat(line));
                        case STRING -> stringStatistics.add(line);
                    }

                    Path outputDirPath = Paths.get(settings.outputDir);
                    try {
                        Files.createDirectories(outputDirPath);
                    } catch (FileSystemException e) {
                        System.err.println("У вас нет прав на запись в " + outputDirPath);
                        System.err.println("по причине: " + e.getMessage());
                        return;
                    } catch (IOException e) {
                        System.err.println("Не удалось создать каталог: " + e.getMessage());
                        return;
                    }

                    Path outputFile = outputDirPath.resolve(settings.prefix + dataType.getFileName());

                    fileWriter.write(dataType, outputFile, settings.prefix, settings.isAppend, line);
                }
                br.close();
            }
            StatisticsPrint.printStatistics(intStatistics, floatStatistics, stringStatistics, settings);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            fileWriter.closeAll();
        }
    }
}
