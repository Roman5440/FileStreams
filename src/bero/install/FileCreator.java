package bero.install;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringJoiner;


public class FileCreator {
    public static final String GAMES = "Games";
    private StringBuilder stringBuilder = new StringBuilder();

    public void createFiles() {
        String src = createDir(GAMES, "src");
        String res = createDir(GAMES, "res");
        String saveGames = createDir(GAMES, "savegames");
        String temp = createDir(GAMES, "temp");
        String main = createDir(src, "main");
        String test = createDir(src, "test");

        String mainJava = createFile(main, "Main.java");
        String utilsJava = createFile(main, "Utils.java");


        String drawables = createDir(res, "drawables");
        String vectors = createDir(res, "vectors");
        String icons = createDir(res, "icons");

        String tempTxt = createFile(temp, "temp.txt");
        flush(tempTxt);

    }

    private void flush(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createDir(String... path) {
        StringJoiner stringJoiner = new StringJoiner(File.separator);
        Arrays.asList(path).forEach(stringJoiner::add);
        File file = new File(stringJoiner.toString());
        if (file.mkdir()) {
            stringBuilder.append("New directory created: ").append(file.getPath()).append("\n");
        }
        return file.getPath();
    }

    private String createFile(String basePath, String fileName) {
        File file = new File(basePath + File.separator + fileName);
        try {
            if (file.createNewFile()) {
                stringBuilder.append("New file created: ").append(file.getPath()).append("\n");
            }
        } catch (IOException e) {
            stringBuilder.append("Unable to create file: ").append(file.getPath()).append(" Exception throws: ").append(e.getMessage()).append("\n");
        }
        return file.getPath();
    }
}
