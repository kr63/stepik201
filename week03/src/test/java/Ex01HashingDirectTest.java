import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex01HashingDirectTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void testCaseOne() throws IOException {
        // given
        systemInMock.provideLines("12",
                "add 911 police",
                "add 76213 Mom",
                "add 17239 Bob",
                "find 76213",
                "find 910",
                "find 911",
                "del 910",
                "del 911",
                "find 911",
                "find 76213",
                "add 76213 daddy",
                "find 76213");
        // when
        new Ex01HashingDirect().run();
        // then
        assertEquals("Mom\nnot found\n" +
                        "police\n" + "not found\n" +
                        "Mom\n" + "daddy\n", systemOutRule.getLog());
    }
}