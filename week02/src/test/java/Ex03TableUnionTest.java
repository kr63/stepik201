import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex03TableUnionTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void test1() throws IOException {
        // given
        systemInMock.provideLines("5 5", "1 1 1 1 1", "3 5", "2 4", "1 4", "5 4", "5 3");
        // when
        new Ex03TableUnion().run();
        // then
        assertEquals("2 2 3 5 5".replaceAll(" ", "\n") + "\n",
                systemOutRule.getLog());
    }

    @Test
    public void test2() throws IOException {
        // given
        systemInMock.provideLines("6 4", "10 0 5 0 3 3", "6 6", "6 5", "5 4", "4 3");
        // when
        new Ex03TableUnion().run();
        // then
        assertEquals("10 10 10 11".replaceAll(" ", "\n") + "\n",
                systemOutRule.getLog());
    }


    @Test
    public void test_find() {
        // given
        int[] tableSizes = new int[] {1, 1, 1, 1, 1};
        Table table = new Table(1, tableSizes);
        table.makeSet(5);
        table.parent = new int[] {0, 1, 2, 3, 4};
        // when
        int i = table.find(4);
        int j = table.find(2);
        int k = table.find(0);
        // then
        assertEquals(4, i);
        assertEquals(2, j);
        assertEquals(0, k);
    }

    @Test
    public void test_simple_union() {
        // given
        int[] tableSizes = new int[] {1, 1, 1, 1, 1, 1};
        Table table = new Table(1, tableSizes);
        table.makeSet(6);
        table.parent = new int[] {0, 1, 2, 3, 4, 5};
        // when
        table.union(1, 3);
        // then
        assertArrayEquals(new int[] {0, 1, 2, 1, 4, 5}, table.parent);
        assertArrayEquals(new int[] {0, 1, 0, 0, 0, 0}, table.rank);
        assertArrayEquals(new int[] {1, 2, 1, 0, 1, 1}, table.tableSizes);
        assertEquals(2, table.maxSize);
    }

    @Test
    public void test_union_two_node_to_one() {
        // given
        int[] tableSizes = new int[] {1, 1, 1, 1, 1, 1};
        Table table = new Table(1, tableSizes);
        table.makeSet(6);
        table.parent = new int[] {0, 1, 2, 3, 4, 5};
        // when
        table.union(1, 3);
        table.union(4, 1);
        // then
        assertArrayEquals(new int[] {0, 1, 2, 1, 1, 5}, table.parent);
        assertArrayEquals(new int[] {0, 1, 0, 0, 0, 0}, table.rank);
        assertArrayEquals(new int[] {1, 3, 1, 0, 0, 1}, table.tableSizes);
        assertEquals(3, table.maxSize);
    }

    @Test
    public void test_union_one_node_to_one() {
        // given
        int[] tableSizes = new int[] {1, 1, 1, 1, 1, 1};
        Table table = new Table(1, tableSizes);
        table.makeSet(6);
        table.parent = new int[] {0, 1, 2, 3, 4, 5};
        // when
        table.union(1, 3);
        table.union(4, 1);
        table.union(2, 0);
        // then
        assertArrayEquals(new int[] {2, 1, 2, 1, 1, 5}, table.parent);
        assertArrayEquals(new int[] {0, 1, 1, 0, 0, 0}, table.rank);
        assertArrayEquals(new int[] {0, 3, 2, 0, 0, 1}, table.tableSizes);
        assertEquals(3, table.maxSize);
    }

    @Test
    public void test_union_two_node_to_one2() {
        // given
        int[] tableSizes = new int[] {1, 1, 1, 1, 1, 1};
        Table table = new Table(1, tableSizes);
        table.makeSet(6);
        table.parent = new int[] {0, 1, 2, 3, 4, 5};
        // when
        table.union(1, 3);
        table.union(4, 1);
        table.union(2, 0);
        table.union(1, 2);
        // then
        assertArrayEquals(new int[] {2, 1, 1, 1, 1, 5}, table.parent);
        assertArrayEquals(new int[] {0, 2, 1, 0, 0, 0}, table.rank);
        assertArrayEquals(new int[] {0, 5, 0, 0, 0, 1}, table.tableSizes);
        assertEquals(5, table.maxSize);
    }

    @Test
    public void test_union_two_node_to_one3() {
        // given
        int[] tableSizes = new int[] {1, 1, 1, 1, 1, 1};
        Table table = new Table(1, tableSizes);
        table.makeSet(6);
        table.parent = new int[] {0, 1, 2, 3, 4, 5};
        // when
        table.union(1, 3);
        table.union(4, 1);
        table.union(2, 0);
        table.union(1, 2);
        table.union(1, 5);
        // then
        assertArrayEquals(new int[] {2, 1, 1, 1, 1, 1}, table.parent);
        assertArrayEquals(new int[] {0, 2, 1, 0, 0, 0}, table.rank);
        assertArrayEquals(new int[] {0, 6, 0, 0, 0, 0}, table.tableSizes);
        assertEquals(6, table.maxSize);
    }

}