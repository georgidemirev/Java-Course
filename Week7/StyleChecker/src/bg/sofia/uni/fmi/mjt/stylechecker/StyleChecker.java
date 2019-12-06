package bg.sofia.uni.fmi.mjt.stylechecker;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Checks adherence to Java style guidelines.
 */

public class StyleChecker {

    public StyleChecker() {
    }

    public static void main(String[] args) throws IOException {
        //FileWriter p = new FileWriter("test/bg/sofia/uni/fmi/mjt/stylechecker/" + "test.txt");

        //p.close();
        int i = 100;
        i = --i + 1;
        System.out.println(i);
    }

    // FIXME Wildcards are not allowed in import statements
    // FIXME Only one statement per line is allowed
    // FIXME Length of line should not exceed 100 characters
    // FIXME Opening brackets should be placed on the same line as the declaration
    // FIXME Package name should not contain upper-case letters or underscores

    /**
     * For each line from the given {@code source} performs code style checks
     * and writes to the {@code output}
     * 1. a FIXME comment line for each style violation in the input line, if any
     * 2. the input line itself.
     *
     * @param source
     * @param output
     */
    public void checkStyle(Reader source, Writer output) throws IOException {
        BufferedWriter writer = new BufferedWriter(output);
        writer.write("wtf");
        writer.close();
        // implementation
    }

}