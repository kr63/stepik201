import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

public class Ex01BracketsTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void should_return_success_one_type_brackets() {
        // given
        String input = "[]";
        // when
        new Ex01Brackets().run(input);
        // than
        assertEquals("Success\n", systemOutRule.getLog());
    }

    @Test
    public void should_return_success_all_type_brackets1() {
        // given
        String input = "([](){([])})";
        // when
        new Ex01Brackets().run(input);
        // than
        assertEquals("Success\n", systemOutRule.getLog());
    }

    @Test
    public void should_return_first_close_brackets() {
        // given
        String input = "()[]}";
        // when
        new Ex01Brackets().run(input);
        // than
        assertEquals("5\n", systemOutRule.getLog());
    }

    @Test
    public void should_return_first_close_brackets2() {
        // given
        String input = "{{[()]]";
        // when
        new Ex01Brackets().run(input);
        // than
        assertEquals("7\n", systemOutRule.getLog());
    }
    @Test
    public void test() {
        // given
        String input = "()({}";
        // when
        new Ex01Brackets().run(input);
        // than
        assertEquals("3\n", systemOutRule.getLog());
    }


    @Test
    public void should_return_last_close_brackets() {
        // given
        String input = "foo(bar[i)";
        // when
        new Ex01Brackets().run(input);
        // than
        assertEquals("10\n", systemOutRule.getLog());
    }
}