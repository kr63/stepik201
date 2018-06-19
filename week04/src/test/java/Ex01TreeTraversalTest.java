import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex01TreeTraversalTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void testCaseOne() throws IOException {
        // given
        systemInMock.provideLines("5", "4 1 2", "2 3 4", "5 -1 -1", "1 -1 -1", "3 -1 -1");
        // when
        new Ex01TreeTraversal().run();
        // then
        assertEquals("1 2 3 4 5\n4 2 1 3 5\n1 3 2 5 4\n", systemOutRule.getLog());

    }

    @Test
    public void testCaseTwo() throws IOException {
        // given
        systemInMock.provideLines("10", "0 7 2", "10 -1 -1",
                "20 -1 6", "30 8 9", "40 3 -1", "50 -1 -1",
                "60 1 -1", "70 5 4", "80 -1 -1", "90 -1 -1");

        // when
        new Ex01TreeTraversal().run();
        // then
        assertEquals("50 70 80 30 90 40 0 20 10 60\n" +
                "0 70 50 40 30 80 90 20 60 10\n" +
                "50 80 90 30 40 70 10 60 20 0\n" , systemOutRule.getLog());
    }

}