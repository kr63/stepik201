import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

public class Ex02TreeHeightTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void one_node_only() {
        // given
        String input = "-1";
        // when
        new Ex02TreeHeight().run(input);
        // than
        assertEquals("1\n", systemOutRule.getLog());
    }


    @Test
    public void node_with_two_child_height_three() {
        // given
        String input = "4 -1 4 1 1";
        // when
        new Ex02TreeHeight().run(input);
        // than
        assertEquals("3\n", systemOutRule.getLog());
    }

    @Test
    public void node_with_three_child_height_three() {
        // given
        String input = "4 -1 4 1 1 4";
        // when
        new Ex02TreeHeight().run(input);
        // than
        assertEquals("3\n", systemOutRule.getLog());
    }

    @Test
    public void node_with_one_child_height_four() {
        // given
        String input = "-1 0 4 0 3";
        // when
        new Ex02TreeHeight().run(input);
        // than
        assertEquals("4\n", systemOutRule.getLog());
    }

    @Test
    public void node_with_four_child_height_four() {
        // given
        String input = "9 7 5 5 2 9 9 9 2 -1";
        // when
        new Ex02TreeHeight().run(input);
        // than
        assertEquals("4\n", systemOutRule.getLog());
    }

}