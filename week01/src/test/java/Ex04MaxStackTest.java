import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;


public class Ex04MaxStackTest {

    private StackWithMax stackWithMax;

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void setUp() {
        stackWithMax = new StackWithMax();
    }

    @Test
    public void test_input() throws IOException {
        // given
        systemInMock.provideLines(
                "5\n" +
                "push 2\n" +
                "push 1\n" +
                "max\n" +
                "pop\n" +
                "max");
        // when
        new Ex04MaxStack().run();
        // than
        assertEquals("2\n2\n", systemOutRule.getLog());
    }

    @Test
    public void push_decreasing_values() {
        // when
        stackWithMax.push(2);
        stackWithMax.push(1);
        System.out.println(stackWithMax.getCurrentMax());
        stackWithMax.pop();
        System.out.println(stackWithMax.getCurrentMax());
        // than
        assertEquals("2\n2\n", systemOutRule.getLog());
    }

    @Test
    public void push_increasing_values() {
        // when
        stackWithMax.push(1);
        stackWithMax.push(2);
        System.out.println(stackWithMax.getCurrentMax());
        stackWithMax.pop();
        System.out.println(stackWithMax.getCurrentMax());
        // than
        assertEquals("2\n1\n", systemOutRule.getLog());
    }

    @Test
    public void push_max_value_in_the_middle() {
        // when
        stackWithMax.push(2);
        stackWithMax.push(3);
        stackWithMax.push(9);
        stackWithMax.push(7);
        stackWithMax.push(2);
        System.out.println(stackWithMax.getCurrentMax());
        System.out.println(stackWithMax.getCurrentMax());
        System.out.println(stackWithMax.getCurrentMax());
        stackWithMax.pop();
        System.out.println(stackWithMax.getCurrentMax());
        // than
        assertEquals("9\n9\n9\n9\n", systemOutRule.getLog());
    }
}