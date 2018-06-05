import com.google.common.io.CharStreams;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex02ParallelWorkTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void test1() throws IOException {
        // given
        systemInMock.provideLines("2 5", "1 2 3 4 5");
        // when
        new Ex02ParallelWork().run();
        // then
        assertEquals("0 0\n"
                + "1 0\n"
                + "0 1\n"
                + "1 2\n"
                + "0 4\n", systemOutRule.getLog());
    }

    @Test
    public void test2() throws IOException {
        // given
        systemInMock.provideLines("4 20", "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1");
        InputStream out = Ex02ParallelWorkTest.class.getResourceAsStream("ex02_test2_output.txt");
        String output = CharStreams.toString(new InputStreamReader(out));
        // when
        new Ex02ParallelWork().run();
        // then
        assertEquals(output, systemOutRule.getLog());
    }
}