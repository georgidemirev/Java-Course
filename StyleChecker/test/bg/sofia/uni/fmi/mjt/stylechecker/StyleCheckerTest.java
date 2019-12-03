package bg.sofia.uni.fmi.mjt.stylechecker;

import org.junit.Test;

import java.io.*;

public class StyleCheckerTest {

    @Test
    public void test() throws IOException {
        StyleChecker styleChecker = new StyleChecker();
        Writer writer = new FileWriter("test/bg/sofia/uni/fmi/mjt/stylechecker/" + "testOutput.txt");
        Reader reader = new FileReader("test/bg/sofia/uni/fmi/mjt/stylechecker/" + "test.txt");
        styleChecker.checkStyle(reader, writer);
    }
}