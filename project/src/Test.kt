import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Test{

    @Test
    fun isEmpty() {
        val test = BitSet(2)
        assertTrue(test.isEmpty())
        test.flip()
        assertFalse(test.isEmpty())
        test.clear()
        assertTrue(test.isEmpty())
    }

    @Test
    fun get() {
        val test = BitSet(6)
        test.flip(listOf(0, 2, 5))
        assertEquals(1, test.get(0))
        assertEquals(0, test.get(3))
    }

    @Test
    fun lastTrueIndex() {
        val test = BitSet(5)
        test.flip(listOf(0, 2, 4))
        assertEquals(4, test.lastTrueIndex())
    }

    @Test
    fun count() {
        val test = BitSet(7)
        test.flip(listOf(0, 3, 5, 6))
        assertEquals(4, test.count())
    }

    @Test
    fun and() {
        val test = BitSet(4)
        val test1 = BitSet(4)
        test.flip(listOf(0, 3))
        test1.flip(listOf(2, 3))
        assertEquals("0001", test1.and(test).toString())
    }

    @Test
    fun andNot(){
        val test = BitSet(4)
        val test1 = BitSet(4)
        test.flip(listOf(0, 3))
        test1.flip(listOf(2, 3))
        assertEquals("1110", test1.andNot(test).toString())
    }

    @Test
    fun clear() {
        val test = BitSet(5)
        test.flip()
        test.clear(0, 2)
        test.clear(4)
        assertEquals("00110", test.toString())
    }

    @Test
    fun intersects() {
        val test = BitSet(4)
        val test1 = BitSet(6)
        val test2 = BitSet(7)
        test.set(1, true)
        test1.flip(listOf(1, 3, 5))
        test2.flip(listOf(5, 6))
        assertTrue(test.intersects(test1))
        assertFalse(test.intersects(test2))
        assertTrue(test1.intersects(test2))
    }

    @Test
    fun nextClearBit() {
        val test = BitSet(7)
        test.flip(listOf(1, 4, 5, 6))
        assertEquals(0, test.nextClearBit(0))
        assertEquals(2, test.nextClearBit(1))
        assertNotEquals(5, test.nextClearBit(3))
        assertEquals(7, test.nextClearBit(4))
    }

    @Test
    fun nextSetBit() {
        val test = BitSet(9)
        test.flip(listOf(1, 4, 5))
        assertEquals(1, test.nextSetBit(1))
        assertEquals(-1, test.nextSetBit(7))
        assertNotEquals(5, test.nextSetBit(3))
    }

    @Test
    fun or(){
        val test = BitSet(4)
        val test1 = BitSet(4)
        test.flip(listOf(0, 3))
        test1.flip(listOf(2, 3))
        assertEquals("1011", test1.or(test).toString())
    }

    @Test
    fun previousBits() {
        val test = BitSet(6)
        test.flip(listOf(1, 2, 3, 5))
        assertEquals(5, test.previousBit(5, true))
        assertEquals(-1, test.previousBit(6, false))
        assertEquals(3, test.previousSetBit(4))
        assertEquals(0, test.previousClearBit(3))
    }

    @Test
    fun xor() {
        val test = BitSet(4)
        val test1 = BitSet(4)
        test.flip(listOf(0, 3))
        test1.flip(listOf(2, 3))
        assertEquals("1010", test1.xor(test).toString())
    }
}