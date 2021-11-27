package utils

class Utils {
    fun intToBoolean(x : Int) : Boolean {
        val res = x % 2
        return when (res) {
            0 -> false
            1 -> true
            else -> false
        }
    }

    fun booleanToInt(x: Boolean): Int {
        return when (x) {
            false -> 0
            true -> 1
        }
    }
}