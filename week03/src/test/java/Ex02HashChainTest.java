import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Ex02HashChainTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void testHashFunction() {
        // given
        HashTable hashTable = new HashTable(5);
        // when
        int hash1 = hashTable.hash("world");
        int hash2 = hashTable.hash("HellO");
        // then
        assertEquals(4, hash1);
        assertEquals(4, hash2);
    }

    @Test
    public void testAddThenCheck() {
        // given
        HashTable hashTable = new HashTable(5);
        // when
        hashTable.add("world");
        hashTable.add("HellO");
        hashTable.check(4);
        // then
        assertEquals("HellO world\n", systemOutRule.getLog());
    }

    @Test
    public void testFind() {
        // given
        HashTable hashTable = new HashTable(5);
        // when
        hashTable.add("world");
        hashTable.add("HellO");
        if (!hashTable.find("World")) System.out.println("no");
        if (hashTable.find("world")) System.out.println("yes");
        // then
        assertEquals("no\nyes", systemOutRule.getLog());
    }

    @Test
    public void testAddThenDel() {
        // given
        HashTable hashTable = new HashTable(5);
        // when
        hashTable.add("world");
        hashTable.add("HellO");
        hashTable.del("world");
        hashTable.check(4);
        // then
        assertEquals("HellO\n", systemOutRule.getLog());
    }

    @Test
    public void testCheckEmpty() {
        // given
        HashTable hashTable = new HashTable(5);
        // when
        hashTable.check(0);
        // then
        assertEquals("\n", systemOutRule.getLog());
    }

    @Test
    public void testCaseOne() throws IOException {
        // given
        Ex02HashChain chain = new Ex02HashChain();
        systemInMock.provideLines(
                "5", "12", "add world",
                "add HellO", "check 4", "find World",
                "find world", "del world", "check 4",
                "del HellO", "add luck", "add GooD",
                "check 2", "del good");
        // when
        chain.run();
        // then
        assertEquals("HellO world\nno\nyes\nHellO\nGooD luck\n", systemOutRule.getLog());
    }

    @Test
    public void testCaseTwo() throws IOException {
        // given
        Ex02HashChain chain = new Ex02HashChain();
        systemInMock.provideLines(
                "4", "8", "add test",
                "add test", "find test", "del test",
                "find test", "find Test", "add Test",
                "find Test");
        // when
        chain.run();
        // then
        assertEquals("yes\nno\nno\nyes\n", systemOutRule.getLog());
    }

    @Test
    public void testCaseThree() throws IOException {
        // given
        Ex02HashChain chain = new Ex02HashChain();
        systemInMock.provideLines("3", "2", "add a", "find d");

        // when
        chain.run();
        // then
        assertEquals("no", systemOutRule.getLog());
    }

    @Test
    public void testCaseFour() throws IOException {
        // given
        Ex02HashChain chain = new Ex02HashChain();
        systemInMock.provideLines( "3", "2", "check 0", "find help");

        // when
        chain.run();
        // then
        assertEquals("\nno", systemOutRule.getLog());
    }



}