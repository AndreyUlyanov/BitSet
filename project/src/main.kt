import java.lang.Math.pow
import kotlin.math.*

class BitSet(private val n: Int) {

    private val list = mutableListOf<Long>()

    init {
        for (i in 0..n/64) list.add(0)
    }

    operator fun get(position: Int): Int {
        if (position >= n || position < 0) return -1  // it means that this position isn't in list
        val digit = position / 64
        val pos = position % 64
        val mask: Long = (pow(2.0, pos.toDouble())).toLong()
        val test = list[digit] or mask
        return if (test == list[digit]) 1 else 0
    }

    fun isEmpty(): Boolean {
        for (i in 0 until list.size) if (list[i] != 0.toLong()) return false
        return true
    }

    fun lastTrueIndex(): Int {
        if (list.isEmpty()) return -1
        var index = 0
        for (i in list.size - 1 downTo 0) {
            if (list[i] == 0.toLong()) {
                index += 63
                continue
            }
            val digit = list[i]
            var ans = 0
            do {
                ans++
            } while (pow(2.0, ans.toDouble()).toLong() <= digit)
            return ans + index - 1
        }
        return -1
    }

    fun size() = n

    fun count(): Int {
        var result = 0
        for (i in 0 until n) {
            if (get(i) == 1) result++
        }
        return result
    }

    fun and(another: BitSet): BitSet {
        val result = if (n >= another.n) this else another
        if (this.list.size >= another.list.size)
            for (i in 0 until another.list.size) result.list[i] = result.list[i] and another.list[i]
        else
            for (i in 0 until list.size) result.list[i] = result.list[i] and list[i]
        return result
    }

    fun andNot(another: BitSet): BitSet {
        val result = this.and(another)
        result.flip()
        return  result
    }

    fun clear(position: Int) {
        if (get(position) == 1) flip(position)
    }

    fun clear(from: Int, to: Int) {
        for (i in from until to) clear(i)
    }

    fun clear() {
        for (i in 0 until list.size) list[i] = 0
    }

    override fun equals(other: Any?): Boolean {
        if (other !is BitSet) return false
        if (n == other.n) {
            for (i in 0 until list.size)
                if (list[i] != other.list[i]) return false
        } else return false
        return true
    }

    fun flip(position: Int) {
        val digit = position / 64
        val pos = position % 64
        if (get(position) == 0) {
            val mask: Long = (pow(2.0, pos.toDouble())).toLong()
            list[digit] = list[digit] or mask
        }
        else {
            val mask: Long = (pow(2.0, pos.toDouble())).toLong()
            list[digit] = list[digit] - mask
        }
    }

    fun flip(list: List<Int>) {
        for (i in 0 until list.size) flip(list[i])
    }

    fun flip(from: Int, to: Int) {
        for (i in from until to) flip(i)
    }

    fun flip() {
        for (i in 0 until n) flip(i)
    }

    override fun hashCode(): Int {
        var result: Long = 0
        for (i in 0 until list.size) {
            result += 5 * list[i]
        }
        return result.toInt()
    }

    fun intersects(another: BitSet): Boolean {
        for (i in 0 until min(n, another.n))
            if (get(i) == another.get(i) && get(i) == 1) return true
        return false
    }

    fun nextClearBit(startIndex: Int): Int {
        for (i in startIndex until n) {
            if (get(i) == 0) return i
        }
        return n
    }

    fun nextSetBit(startIndex: Int): Int {
        for (i in startIndex until n) {
            if (get(i) == 1) return i
        }
        return -1
    }

    fun or(another: BitSet): BitSet {
        val result = if (n >= another.n) this else another
        if (this.list.size >= another.list.size)
            for (i in 0 until another.list.size) result.list[i] = result.list[i] or another.list[i]
        else
            for (i in 0 until list.size) result.list[i] = result.list[i] or list[i]
        return result
    }

    fun previousBit(startIndex: Int, lookFor: Boolean): Int {
        if (startIndex >= n) return -1
        for (i in startIndex downTo 0) {
            if ((get(i) == 1 && lookFor) || (get(i) == 0 && !lookFor)) return i
        }
        return -1
    }

    fun previousClearBit(startIndex: Int): Int {
        if (startIndex >= n) return -1
        for (i in startIndex downTo 0) {
            if (get(i) == 0) return i
        }
        return -1
    }

    fun previousSetBit(startIndex: Int): Int {
        if (startIndex >= n) return -1
        for (i in startIndex downTo 0) {
            if (get(i) == 1) return i
        }
        return -1
    }

    fun set(index: Int, value: Boolean) {
        if (!(get(index) == 1 && value) && !(get(index) == 0 && !value)) flip(index)
    }

    override fun toString(): String {
        val result = StringBuilder()
        for (digit in 0 until list.size - 1) {
            var number = list[digit]
            if (number == 0.toLong()) result.append("0".repeat(63))
            while (number > 0) {
                result.append(number % 2)
                number /= 2
            }
        }
        if (list[list.lastIndex] == 0.toLong()) result.append("0".repeat(n % 64))
        var number = list[list.lastIndex]
        var j = 0
        while (j < n % 64) {
            result.append(number % 2)
            number /= 2
            j++
        }
        return result.toString()
    }


    fun xor(another: BitSet): BitSet {
        val result = if (n >= another.n) this else another
        if (this.list.size >= another.list.size)
            for (i in 0 until another.list.size) result.list[i] = result.list[i] xor another.list[i]
        else
            for (i in 0 until list.size) result.list[i] = result.list[i] xor list[i]
        return result
    }
}