import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex04Test {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void test1() throws IOException {
        // given
        systemInMock.provideLines("4 6 0", "1 2", "1 3", "1 4", "2 3", "2 4", "3 4");
        // when
        System.out.println(new Ex04().run());
        // then
        assertEquals("1\n", systemOutRule.getLog());
    }

}