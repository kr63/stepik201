import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex03DataBaseTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void unionTablesWithTheSameRank() {
        // given
        Table destination = new Table(1);
        Table source = new Table(2);
        Ex03DataBase ex03DataBase = new Ex03DataBase();
        // when
        ex03DataBase.union(destination, source);
        Table rootDestination = destination.find();
        Table rootSource = source.find();
        // then
        assertEquals(rootDestination, destination.parent);
        assertEquals(rootSource, destination.parent);
        assertEquals(1, destination.rank);
        assertEquals(0, source.rank);
        assertEquals(3, ex03DataBase.maxRows);
    }

    @Test
    public void unionTablesWithDiffRank() {
        // given
        Table tableWithLowRank = new Table(5);
        tableWithLowRank.rank = 2;
        Table tableWithHighRank = new Table(2);
        tableWithHighRank.rank = 10;
        Ex03DataBase ex03DataBase = new Ex03DataBase();
        // when
        ex03DataBase.union(tableWithHighRank, tableWithLowRank);
        // then
        assertEquals(tableWithHighRank, tableWithHighRank.parent);
        assertEquals(tableWithHighRank, tableWithLowRank.parent);
        assertEquals(10, tableWithHighRank.rank);
        assertEquals(2, tableWithLowRank.rank);
        assertEquals(7, ex03DataBase.maxRows);
        assertEquals(7, tableWithHighRank.rowsNumber);
        assertEquals(0, tableWithLowRank.rowsNumber);
    }

    @Test
    public void testCaseOne() throws IOException {
        // given
        systemInMock.provideLines("5 5", "1 1 1 1 1", "3 5", "2 4", "1 4", "5 4", "5 3");
        // when
        new Ex03DataBase().run();
        // then
        assertEquals("2 2 3 5 5".replaceAll(" ", "\n") + "\n",
                systemOutRule.getLog());
    }

    @Test
    public void testCaseTwo() throws IOException {
        // given
        systemInMock.provideLines("6 4", "10 0 5 0 3 3", "6 6", "6 5", "5 4", "4 3");
        // when
        new Ex03DataBase().run();
        // then
        assertEquals("10 10 10 11".replaceAll(" ", "\n") + "\n",
                systemOutRule.getLog());
    }
}