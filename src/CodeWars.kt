fun main() {

    val array = arrayOf("1", "2", "3", "4", "5", "6", "7")
    println(longestConsec(array, 2))

//    Combinations below don't work either
//    val array = arrayOf("1", "2", "3", "4","5",  "6", "7", "8", "9")
//    println(longestConsec(array, 3))
//
//    val array = arrayOf("1", "2", "3", "4","5",  "6", "7", "8", "9", "10", "11")
//    println(longestConsec(array, 4))

//    val array = arrayOf("1", "2", "3", "4","5",  "6", "7", "8", "9", "10", "11", "12", "13")
//    println(longestConsec(array, 5))

}

fun longestConsec(strings: Array<String>, stringCount: Int) =
    when {
        strings.isEmpty() || stringCount > strings.size || stringCount <= 0 -> ""
        else -> ConsecutiveString(strings.toList(), stringCount)
            .searchLongestConsecutiveString(0, mutableListOf())
            .maxBy { it.length } ?: throw IllegalArgumentException()
    }

class ConsecutiveString(private val originalList: List<String>, private val stringCount: Int) {

    tailrec fun searchLongestConsecutiveString(index: Int, acc: MutableList<String>): List<String> {
        println("start")
        return when {
            index + stringCount >= originalList.size -> acc
            else -> {
                println(index + stringCount)
                // *** doesn't work ***
                searchLongestConsecutiveString(
                    index + 1
                    , acc.apply { add(originalList.subList(index, index + stringCount).joinToString("")) }
                )
                // *** doesn't work ***

//                // *** works ***
//                val consecutiveString = originalList.subList(index, index + size).joinToString("")
//                searchLongestConsecutiveString(
//                    index + 1
//                    , acc.apply { add(consecutiveString) }
//                )
//                // *** works ***
            }
        }
    }
}
