import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex03SimpleTest {
    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void testCaseOne() throws IOException {
        // given
        systemInMock.provideLines("5 5", "1 1 1 1 1", "3 5", "2 4", "1 4", "5 4", "5 3");
        // when
        new Ex03Simple().solve();
        // then
        assertEquals("2 2 3 5 5".replaceAll(" ", "\n") + "\n",
                systemOutRule.getLog());
    }

    @Test
    public void testCaseTwo() throws IOException {
        // given
        systemInMock.provideLines("6 4", "10 0 5 0 3 3", "6 6", "6 5", "5 4", "4 3");
        // when
        new Ex03Simple().solve();
        // then
        assertEquals("10 10 10 11".replaceAll(" ", "\n") + "\n",
                systemOutRule.getLog());
    }
}