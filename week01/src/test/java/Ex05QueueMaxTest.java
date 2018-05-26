import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex05QueueMaxTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void window_size_one() throws IOException {
        // given
        systemInMock.provideLines("3", "2 1 5", "1");
        // when
        new Ex05QueueMax().run();
        // than
        assertEquals("2\n1\n5\n", systemOutRule.getLog());
    }

    @Test
    public void window_size_four() throws IOException {
        // given
        systemInMock.provideLines("8", "2 7 3 1 5 2 6 2", "4");
        // when
        new Ex05QueueMax().run();
        // than
        assertEquals("7 7 5 6 6".replaceAll(" ", "\n") + "\n",
                systemOutRule.getLog());
    }

}