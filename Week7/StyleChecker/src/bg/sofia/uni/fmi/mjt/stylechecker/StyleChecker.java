package bg.sofia.uni.fmi.mjt.stylechecker;

import java.io.*;

/**
 * Checks adherence to Java style guidelines.
 */

public class StyleChecker {

    // make Singleton !

    private static final int MAX_LENGTH_OF_LINE = 100;
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
        BufferedReader reader = new BufferedReader(source);

        String line = reader.readLine();
        while (line != null) {
            if (line.length() > 0) {
                checkAllRules(line, output);
                output.write(line + "\n");
            }
            line = reader.readLine();
        }
    }

    private void checkAllRules(String line, Writer output) throws IOException {
        checkIfOnlyOneStatements(line, output);
        checkWildcardsInImports(line, output);
        checkBrackets(line, output);
        checkPackageUppercaseAnd_(line, output);
        checkLength(line, output);

    }

    private void checkIfOnlyOneStatements(String line, Writer output) throws IOException {
        String[] separated = line.split(";");
        if (separated.length > 1) {
            output.write("// FIXME Only one statement per line is allowed\n");
        }
    }

    private void checkWildcardsInImports(String line, Writer output) throws IOException {
        if (line.contains("import") && line.contains("*")) {
            output.write("// FIXME Wildcards are not allowed in import statements\n");
        }
    }

    private void checkBrackets(String line, Writer output) throws IOException {
        if (line.charAt(0) == '{') {
            output.write("// FIXME Opening brackets should be " +
                    "placed on the same line as the declaration\n");
        }
    }

    private void checkPackageUppercaseAnd_(String line, Writer output) throws IOException {
        if (line.contains("_")) {
            output.write("// FIXME Package name should not " +
                    "contain upper-case letters or underscores\n");
            return;
        }
        if (line.contains("package")) {
            String startingWith = line.substring(0, 6);
            if (startingWith.equals("package")) {
                for (int i = 7; i < line.length(); i++) {
                    char p = line.charAt(i);
                    if (Character.isUpperCase(p)) {
                        output.write("// FIXME Package name should not " +
                                "contain upper-case letters or underscores\n");
                    }
                }
            }
        }
    }

    private void checkLength(String line, Writer output) throws IOException {
        int i = 0;
        int size = 0;
        while (i < line.length()) {
            if (line.charAt(i) != ' ') {
                size = i + 1;
            }
            i++;
        }
        if (size > MAX_LENGTH_OF_LINE) {
            if (!line.contains("import")) {
                output.write("// FIXME Length of line" +
                        " should not exceed 100 characters\n");
            }
        }
    }
}