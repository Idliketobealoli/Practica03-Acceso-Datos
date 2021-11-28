package utils

import dto.ProgrammerDTO
import model.Programmer
import java.util.*

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

    fun matchesDate(string: String) : String {
        val splittedString = string.split("/")
        val year = splittedString[2].toInt()
        val month = splittedString[1].toInt()
        val day = splittedString[0].toInt()
        return if (day in 1..31 && month in 1..12 && year in 1900..9999) string
        else throw Exception("Invalid date.")
    }

    fun makeSureBooleansAreCorrect(item: Programmer) {
        val depBoss = intToBoolean(item.isDepBoss)
        val pManager = intToBoolean(item.isProjectManager)
        val active = intToBoolean(item.isActive)
        when {
            depBoss && pManager -> throw Exception(
                    "A programmer can't be Department Boss and Project Manager at the same time."
            )
            depBoss && active -> throw Exception(
                    "A Department Boss can't be active at the same time."
            )
            pManager && active -> throw Exception(
                    "A Project Manager can't be active at the same time."
            )
            depBoss && pManager && active -> throw Exception(
                    "A programmer can't be Department Boss, Project Manager and be active at the same time."
            )
        }
    }

    fun makeSureBooleansAreCorrect(item: ProgrammerDTO) {
        when {
            item.isDepBoss && item.isProjectManager -> throw Exception(
                    "A programmer can't be Department Boss and Project Manager at the same time."
            )
            item.isDepBoss && item.isActive -> throw Exception(
                    "A Department Boss can't be active at the same time."
            )
            item.isProjectManager && item.isActive -> throw Exception(
                    "A Project Manager can't be active at the same time."
            )
            item.isProjectManager && item.isProjectManager && item.isActive -> throw Exception(
                    "A programmer can't be Department Boss, Project Manager and be active at the same time."
            )
        }
    }
}