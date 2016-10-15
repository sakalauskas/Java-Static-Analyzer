import org.fusesource.jansi.AnsiConsole;
import util.Analyzer;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Runner {


    public static void main(String[] args) throws Exception {
        AnsiConsole.systemInstall();

        Path p = Paths.get("/Users/laurynassakalauskas/IdeaProjects");

        run(p);

        AnsiConsole.systemUninstall();
    }

    private static void run(Path p) {
        try {
            Files.walkFileTree(p, new Analyzer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
