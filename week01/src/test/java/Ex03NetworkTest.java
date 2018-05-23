import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex03NetworkTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void no_packet_should_provide_no_output() throws IOException {
        // given
        systemInMock.provideLines("1 0");
        // when
        new Ex03Network().run(new BufferedReader(new InputStreamReader(System.in)));
        // than
        assertEquals("", systemOutRule.getLog());
    }

    @Test
    public void one_packet_should_provide_one_output() throws IOException {
        // given
        systemInMock.provideLines("1 1", "0 0");
        // when
        new Ex03Network().run(new BufferedReader(new InputStreamReader(System.in)));
        // than
        assertEquals("0\n", systemOutRule.getLog());
    }

    @Test
    public void two_packets_in_queue_with_capacity_one() throws IOException {
        // given
        systemInMock.provideLines("1 2", "0 1", "1 1");
        // when
        new Ex03Network().run(new BufferedReader(new InputStreamReader(System.in)));
        // than
        assertEquals("0\n1\n", systemOutRule.getLog());
    }

    @Test
    public void two_packets_queue_capacity_one_same_arrive_time() throws IOException {
        // given
        systemInMock.provideLines("1 2", "10 1", "10 1");
        // when
        new Ex03Network().run(new BufferedReader(new InputStreamReader(System.in)));
        // than
        assertEquals("10\n-1\n", systemOutRule.getLog());
    }

    @Test
    public void test_output_without_queue() throws IOException {
        // given
        systemInMock.provideLines("10 4", "2 2", "3 4", "4 10", "5 4");
        // when
        new Ex03Network().run(new BufferedReader(new InputStreamReader(System.in)));
        // than
        assertEquals("2\n4\n8\n18\n", systemOutRule.getLog());
    }

    @Test
    public void packets_with_duration_zero() throws IOException {
        // given
        systemInMock.provideLines("2 4", "0 0", "0 0", "0 0", "1 0");
        // when
        new Ex03Network().run(new BufferedReader(new InputStreamReader(System.in)));
        // than
        assertEquals("0\n0\n0\n1\n", systemOutRule.getLog());
    }

/*    @Test
    public void long_test1() throws IOException {

        // given
        InputStream in = Ex03Network.class.getResourceAsStream("ex02_long_test1_input.txt");
        InputStream out = Ex03Network.class.getResourceAsStream("ex02_long_test1_output.txt");
        String output = CharStreams.toString(new InputStreamReader(out));
        // when
        new Ex03Network().run(new BufferedReader(new InputStreamReader(in)));
        // than
        assertEquals(output, systemOutRule.getLog());
    }*/
}