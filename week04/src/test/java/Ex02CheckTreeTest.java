import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex02CheckTreeTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void testCaseSimple() throws IOException {
        // given
        systemInMock.provideLines("0");
        // when
        new Ex02CheckTree().run();
        // then
        assertEquals("CORRECT\n", systemOutRule.getLog());
    }

    @Test
    public void testCaseOne() throws IOException {
        // given
        systemInMock.provideLines("3", "2 1 2", "1 -1 -1", "3 -1 -1");
        // when
        new Ex02CheckTree().run();
        // then
        assertEquals("CORRECT\n", systemOutRule.getLog());
    }

    @Test
    public void testCaseTwo() throws IOException {
        // given
        systemInMock.provideLines("3", "1 1 2", "2 -1 -1", "3 -1 -1");
        // when
        new Ex02CheckTree().run();
        // then
        assertEquals("INCORRECT\n", systemOutRule.getLog());
    }

    @Test
    public void testCaseThree() throws IOException {
        // given
        systemInMock.provideLines("5", "1 -1 1", "2 -1 2", "3 -1 3", "4 -1 4", "5 -1 -1");
        // when
        new Ex02CheckTree().run();
        // then
        assertEquals("CORRECT\n", systemOutRule.getLog());
    }

    @Test
    public void testCaseFour() throws IOException {
        // given
        systemInMock.provideLines("7", "4 1 2", "2 3 4", "6 5 6", "1 -1 -1", "3 -1 -1", "5 -1 -1", "7 -1 -1");
        // when
        new Ex02CheckTree().run();
        // then
        assertEquals("CORRECT\n", systemOutRule.getLog());
    }

    @Test
    public void testCaseFive() throws IOException {
        // given
        systemInMock.provideLines("4", "4 1 -1", "2 2 3", "1 -1 -1", "5 -1 -1");
        // when
        new Ex02CheckTree().run();
        // then
        assertEquals("INCORRECT\n", systemOutRule.getLog());
    }

}