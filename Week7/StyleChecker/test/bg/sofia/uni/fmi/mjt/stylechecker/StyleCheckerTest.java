package bg.sofia.uni.fmi.mjt.stylechecker;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class StyleCheckerTest {

    @Test
    public void TestIfOnlyOneStatements() throws IOException {

        Reader input = new StringReader("import java.util;a;");
        Writer output = new StringWriter();

        StyleChecker checker = new StyleChecker();
        checker.checkStyle(input, output);

        Assert.assertEquals("// FIXME Only one statement per line is allowed\n"
                + "import java.util;a;\n", output.toString());
    }

    @Test
    public void testWildcardsInImports() throws IOException {

        Reader input = new StringReader("import java.util.*;");
        Writer output = new StringWriter();

        StyleChecker checker = new StyleChecker();
        checker.checkStyle(input, output);
        String actual = output.toString();

        Assert.assertEquals("// FIXME Wildcards are not allowed in import statements\n" +
               "import java.util.*;", actual.strip());
    }

    @Test
    public void testBrackets() throws IOException {

        Reader input = new StringReader("import java.util.;\n" +
                "{");
        Writer output = new StringWriter();

        StyleChecker checker = new StyleChecker();
        checker.checkStyle(input, output);
        String actual = output.toString();

        Assert.assertEquals("import java.util.;\n" +
                "// FIXME Opening brackets should be " +
                "placed on the same line as the declaration\n" +
                "{", actual.strip());
    }

    @Test
    public void testPackageUppercaseAnd_() throws IOException {

        Reader input = new StringReader("package bg.uni_sofia.fmi.mjt;");
        Writer output = new StringWriter();

        StyleChecker checker = new StyleChecker();
        checker.checkStyle(input, output);
        String actual = output.toString();

        Assert.assertEquals("// FIXME Package name should not contain " +
                "upper-case letters or underscores\n" +
                "package bg.uni_sofia.fmi.mjt;", actual.strip());
    }

    @Test
    public void testLength() throws IOException {

        Reader input = new StringReader("java.util.asggdggggggggggggg" +
                "ggggggggggggggggggggggggggadgggggggggggggggggggggggggg" +
                "gggggggggggggggggggggggggggggggggg;");
        Writer output = new StringWriter();

        StyleChecker checker = new StyleChecker();
        checker.checkStyle(input, output);
        String actual = output.toString();

        Assert.assertEquals("// FIXME Length of line should not exceed 100 characters\n" +
                "java.util.asggdgggggggggggggggggggggggggggggggggggggggadg" +
                "ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg;", actual.strip());
    }
}