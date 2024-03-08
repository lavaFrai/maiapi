package ru.lavafrai.mai.api.models.group

import java.security.InvalidParameterException


class GroupNameAnalyzer(val name: String) {
    val faculty: Int
    val course: Int
    val type: GroupType?

    init {
        val find = ".(\\d+)(\\S?+)-(\\d+)([\\S^-]+)-(\\d+)".toRegex().find(name) ?: throw InvalidParameterException("Cant parse string like group name")

        faculty = find.groups[1]!!.value.toInt()
        course = find.groups[3]!!.value[0] - '0'

        val typeText = find.groups[4]!!.value
        type = when {
            typeText.startsWith("СВ") -> GroupType.SPECIAL_HIGH
            typeText.startsWith("БВ") -> GroupType.BASE_HIGH
            typeText.startsWith('С') -> GroupType.SPECIAL

            typeText.startsWith('Б') -> GroupType.BACHELOR
            typeText.startsWith('М') -> GroupType.MAGISTRACY
            typeText.startsWith('А') -> GroupType.POSTGRADUATE

            else -> null
        }
    }
}

fun Group.analyzeName(): GroupNameAnalyzer {
    return GroupNameAnalyzer(this.name)
}