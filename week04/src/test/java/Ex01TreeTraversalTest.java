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
    public void test1() throws IOException {
        // given
        systemInMock.provideLines("5", "4 1 2", "2 3 4", "5 -1 -1", "1 -1 -1", "3 -1 -1");
        // when
        new Ex01TreeTraversal().run();
        // then
        assertEquals("1 2 3 4 5", systemOutRule.getLog());
    }

}