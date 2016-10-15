package smells;

import util.Collector;
import util.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class ClassLineCounter {

    private static final int MAX_CLASS_LENGTH = Config.MAX_CLASS_LENGTH;

    /**
     * Calculate number of lines in the class
     *
     * @param path
     * @throws IOException
     */
    public void visit(Path path) throws IOException {
        InputStream is = new FileInputStream(path.toFile());
        int count = 1;
        for (int aChar = 0; aChar != -1;aChar = is.read())
            count += aChar == '\n' ? 1 : 0;

        if (count > MAX_CLASS_LENGTH) {
            Collector.getInstance().addWarning(path.toString(), "Class has more than " + MAX_CLASS_LENGTH + " lines");
        }
    }

}
