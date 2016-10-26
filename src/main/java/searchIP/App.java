package searchIP;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        long timeout = System.currentTimeMillis();

        Path dir = Paths.get("C:\\Users\\User\\IdeaProjects\\searchIP\\testFiles");
        processFilesFromFolder(dir);

        timeout = System.currentTimeMillis() - timeout;
        System.out.println(timeout + " millisec");
    }

    public static void processFilesFromFolder(Path folder) {
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(folder);
            for (Path entry : paths) {
                if (Files.isDirectory(entry)) {
                    processFilesFromFolder(entry);
                    continue;
                } else {
                    replaceIP(entry.toAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void replaceIP(Path file) {

        String search = "192.168.1.10";
        String replace = "192.168.1.100";
        Charset charset = StandardCharsets.UTF_8;
        try {
            String fileData = new String(Files.readAllBytes(file), charset);
            String replaced = fileData.replace(search, replace);
            Files.write(file, replaced.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
